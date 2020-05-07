package cn.qin.service;

import cn.qin.base.response.RestResponse;
import cn.qin.base.response.RestResponseGenerator;
import cn.qin.constancts.SystemConstants;
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
        String userId = request.getHeader(SystemConstants.USERID);
        User user = userRepository.selectByPrimaryKey(userId);
        return RestResponseGenerator.genSuccessResponse(user);

    }

    /**
     * @Title:登录接口
     * @param user
     */
    public RestResponse<User> login(User user){
        User user1 = findUserInfoByAccounnt(user.getAccount());
        if (user1 == null){
            return RestResponseGenerator.genFailResponse("账号不存在！");
        }
        if (!user1.getPwd().equals(user.getPwd())){
            return RestResponseGenerator.genFailResponse("密码错误！");
        }

        return RestResponseGenerator.genSuccessResponse(user1);
    }

    /**
     * @Title:修改信息接口
     * @param user
     */
    public RestResponse<User> updateInfo(User user){
        if (StringUtils.isTrimBlank(user.getUserId()) || StringUtils.isTrimBlank(user.getBundleId())){
            return RestResponseGenerator.genFailResponse("用户ID不能为空");
        }
        Example example = SqlUtil.newExample(User.class);
        example.createCriteria().andEqualTo("userId",user.getUserId()).andEqualTo("bundleId",user.getBundleId());
        User user1 = userRepository.selectOneByExample(example);
        if (user1 == null){
            return RestResponseGenerator.genFailResponse("找不到该用户");
        }
        user.setPwd(user1.getPwd());
        user.setAccount(user1.getAccount());
        userRepository.updateByPrimaryKeyData(user);
        return RestResponseGenerator.genSuccessResponse(user);
    }


    /**
     * @Title:重置密码
     * @param user
     */
    public RestResponse<User> reset(User user){
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
        String bundleId = request.getHeader("bundleId");

        Example example = SqlUtil.newExample(User.class);
        example.createCriteria().andEqualTo("account",user.getAccount()).andEqualTo("bundleId",bundleId)
        .andEqualTo("email",user.getEmail()).andEqualTo("nickName",user.getNickName());
        User user1 = userRepository.selectOneByExample(example);
        if (user1 == null){
            return RestResponseGenerator.genFailResponse("账号不存在！");
        }
        if (!user1.getPwd().equals(user.getPwd())){
            return RestResponseGenerator.genFailResponse("密码错误！");
        }

        user1.setPwd(user.getPwd());
        userRepository.updateByPrimaryKeyData(user1);
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
        User user1 = findUserInfoByAccounnt(user.getAccount());

        if (user1 != null){
            return RestResponseGenerator.genFailResponse("手机号已存在！");
        }
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
        String bundleId = request.getHeader("bundleId");
        user.setBundleId(bundleId);
        user.setPhone(user.getAccount());
        userRepository.insertData(user);

        return RestResponseGenerator.genSuccessResponse(user);
    }

    private User findUserInfoByAccounnt(String account){
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
        String bundleId = request.getHeader("bundleId");

        Example example = SqlUtil.newExample(User.class);
        example.createCriteria().andEqualTo("account",account).andEqualTo("bundleId",bundleId);
        User user = userRepository.selectOneByExample(example);
        return user;
    }
}
