package cn.qin.base.config;

import cn.qin.constancts.SystemConstants;
import cn.qin.util.AESCipher;
import cn.qin.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author qiaomengnan
 * @ClassName: ParameterFilter
 * @Description: 注入拓展request
 * @date 2018/1/7
 */

public class ParameterFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        BodyReaderHttpServletRequestWrapper requestWrapper = new BodyReaderHttpServletRequestWrapper(req);
        if ("1".equals(SystemConstants.ENABLE_ENCRYPT)){
            String userId = req.getHeader(SystemConstants.USERID);
            if (StringUtils.isNotTrimBlank(userId)){
                try {
                    userId = AESCipher.aesDecryptString(userId);
                    requestWrapper.addHeader(SystemConstants.USERID, userId);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

        filterChain.doFilter(requestWrapper,servletResponse);

    }

    @Override
    public void destroy() {

    }
}
