package cn.qin.service;

import cn.qin.base.response.RestResponse;
import cn.qin.base.response.RestResponseGenerator;
import cn.qin.dao.repository.UserRepository;
import cn.qin.entity.User;
import cn.qin.util.AESCipher;
import cn.qin.util.SqlUtil;
import cn.qin.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    /**
     * @Title:获取用户的详情
     * @param
     */
    public RestResponse<User> findUserInfo() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
        String userId = request.getHeader("userId");
        User user = userRepository.selectByPrimaryKey(userId);
        try {
            user.setUserId(AESCipher.aesEncryptString(user.getUserId()));
        }catch (Exception e){
            e.printStackTrace();
        }
        return RestResponseGenerator.genSuccessResponse(user);

    }

    /**
     * @Title:登录接口
     * @param user
     */
    public RestResponse<User> login(User user){
        User user1 = findUserInfo(user.getAccount());
        if (user1 == null){
            return RestResponseGenerator.genFailResponse("账号不存在！");
        }
        if (!user1.getPwd().equals(user.getPwd())){
            return RestResponseGenerator.genFailResponse("密码错误！");
        }
        try {
            user1.setUserId(AESCipher.aesEncryptString(user1.getUserId()));
        }catch (Exception e){
            e.printStackTrace();
        }
        return RestResponseGenerator.genSuccessResponse(user1);
    }

    /**
     * @Title:注册接口
     * @param user
     */
    public RestResponse<User> register(User user){

        if (StringUtils.isTrimBlank(user.getAccount())){
            return RestResponseGenerator.genFailResponse("账号不能为空！");
        }
        if (StringUtils.isTrimBlank(user.getNickName())){
            return RestResponseGenerator.genFailResponse("昵称不能为空！");
        }
        if (StringUtils.isTrimBlank(user.getPwd())){
            return RestResponseGenerator.genFailResponse("密码不能为空！");
        }
        User user1 = findUserInfo(user.getAccount());

        if (user1 != null){
            return RestResponseGenerator.genFailResponse("手机号已存在！");
        }
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
        String bundleId = request.getHeader("bundleId");
        user.setBundleId(bundleId);
        user.setPhone(user.getAccount());
        userRepository.insertData(user);
        try {
            user.setUserId(AESCipher.aesEncryptString(user.getUserId()));
        }catch (Exception e){
            e.printStackTrace();
        }
        return RestResponseGenerator.genSuccessResponse(user);
    }

    private User findUserInfo(String account){
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
        String bundleId = request.getHeader("bundleId");

        Example example = SqlUtil.newExample(User.class);
        example.createCriteria().andEqualTo("account",account).andEqualTo("bundleId",bundleId);
        User user = userRepository.selectOneByExample(example);
        return user;
    }
}
