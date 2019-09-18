package cn.qin.service;

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
    public User findUserInfo() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
        String userId = request.getHeader("userId");
        User user = userRepository.selectByPrimaryKey(userId);
        try {
            user.setUserId(AESCipher.aesEncryptString(user.getUserId()));
        }catch (Exception e){
            e.printStackTrace();
        }
        return  user;
    }

    /**
     * @Title:登录接口
     * @param user
     */
    public User login(User user){
        Example example = SqlUtil.newExample(User.class);
        example.createCriteria().andEqualTo("account",user.getAccount());
        User user1 = userRepository.selectOneByExample(example);
        try {
            user1.setUserId(AESCipher.aesEncryptString(user1.getUserId()));
        }catch (Exception e){
            e.printStackTrace();
        }
        if (user1 == null){
            throw new RuntimeException("账号不存在！");
        }
        if (!user1.getPwd().equals(user.getPwd())){
            throw new RuntimeException("密码错误！");
        }
        return user1;
    }

    /**
     * @Title:注册接口
     * @param user
     */
    public User register(User user){
        if (StringUtils.isTrimBlank(user.getAccount())){
            throw new RuntimeException("账号不能为空！");
        }
        if (StringUtils.isTrimBlank(user.getNickName())){
            throw new RuntimeException("昵称不能为空！");
        }
        if (StringUtils.isTrimBlank(user.getPwd())){
            throw new RuntimeException("密码不能为空！");
        }
        Example example = SqlUtil.newExample(User.class);
        example.createCriteria().andEqualTo("account",user.getAccount());
        User user1 = userRepository.selectOneByExample(example);
        if (user1 != null){
            throw new RuntimeException("账号已存在！");
        }
        userRepository.insertData(user);
        try {
            user.setUserId(AESCipher.aesEncryptString(user.getUserId()));
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }
}
