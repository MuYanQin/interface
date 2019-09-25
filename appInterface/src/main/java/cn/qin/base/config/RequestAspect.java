package cn.qin.base.config;

import cn.qin.base.response.RestResponseGenerator;
import cn.qin.constancts.SystemConstants;
import cn.qin.entity.User;
import cn.qin.service.UserService;
import cn.qin.util.AESCipher;
import cn.qin.util.StringUtils;
import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.Base64;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by huzongcheng on 2017/4/6.
 */
@Aspect
@Component
public class RequestAspect {

    @Autowired
    private UserService userService;

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestAspect.class);

    // 定义切点Pointcut
    @Pointcut("execution(public * cn.qin.controller.*.*(..))")
    /**
     *  execution()是最常用的切点函数
     *  1、execution(): 表达式主体。
     *  2、第一个*号：表示返回类型，*号表示所有的类型。
     *  3、包名：表示需要拦截的包名，后面的两个句点表示当前包和当前包的所有子包，com.sample.service.impl包、子孙包下所有类的方法。
     *  4、第二个*号：表示类名，*号表示所有的类。
     *  5、*(..):最后这个星号表示方法名，*号表示所有的方法，后面括弧里面表示方法的参数，两个句点表示任何参数。
     */

    public void excuteService() {
    }

    @Around("excuteService()")
    //获取请求的url
    public Object doAround(ProceedingJoinPoint joinPoint) throws Exception {
        try {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            String requestUri = request.getRequestURI().toString();//方法返回客户端发出请求时的完整URL。
            String deviceType = request.getHeader("deviceType");

            if (StringUtils.isExits(deviceType)){
                Map map = getFieldsName(joinPoint,request);
                String signature = request.getHeader("signature");
                String base64 =  Base64.getEncoder().encodeToString(JSON.toJSONString(map).getBytes("UTF-8"));
                String encodeString = DigestUtils.md5DigestAsHex(DigestUtils.md5DigestAsHex(base64.getBytes()).getBytes());
                if (!signature.equals(encodeString)){
                    return new ResponseEntity<>(RestResponseGenerator.genFailResponse("鉴权失败！"), HttpStatus.OK);
                }
            }
            return (ResponseEntity) joinPoint.proceed();
        } catch (Exception e){
            e.printStackTrace();
            throw e;
        } catch (Throwable e) {
            e.printStackTrace();
            throw new Exception(e);
        }
    }

    private static Map<String, Object> getFieldsName(ProceedingJoinPoint joinPoint,HttpServletRequest request) {
        Map<String, Object> paramMap = new HashMap<>(32);
        if("POST".equals(request.getMethod())){
            String result = "";
            try {
                request.setCharacterEncoding("UTF-8");
                ServletInputStream in = request.getInputStream();
                BufferedReader bf = new BufferedReader(new InputStreamReader(in, "UTF-8"));
                StringBuffer buffer = new StringBuffer();
                String line = "";
                while ((line = bf.readLine()) != null) {
                    buffer.append(line);
                }
                if (null != buffer && buffer.length() > 0) {
                    result = URLDecoder.decode(buffer.toString(), "UTF-8");// 转成String并解码。
                }
                paramMap = (Map) JSON.parse(result);
            } catch (Exception e) {
                e.printStackTrace();
                result = "-1";
            }
        }else if("GET".equals(request.getMethod())){
            // 参数值
            Object[] args = joinPoint.getArgs();
            ParameterNameDiscoverer pnd = new DefaultParameterNameDiscoverer();
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            String[] parameterNames = pnd.getParameterNames(method);
            for (int i = 0; i < parameterNames.length; i++) {
                paramMap.put(parameterNames[i], args[i]);
            }
        }
        //包名
        String bundleId = request.getHeader("bundleId");
        paramMap.put("bundleId",bundleId);
        //时间戳
        String timestamp = request.getHeader("timestamp");
        paramMap.put("timestamp",timestamp);
        //终端标志
        String deviceType = request.getHeader("deviceType");
        paramMap.put("deviceType",deviceType);
        //用户id
        String userId = request.getHeader("userId");
        paramMap.put("userId",userId);
        Map<String, Object> result = new LinkedHashMap<>();
        paramMap.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEachOrdered(x -> result.put(x.getKey(), x.getValue()));
        return result;
    }
}
