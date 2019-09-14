package cn.qin.service;

import cn.qin.dao.repository.UserRepository;
import cn.qin.entity.User;
import cn.qin.util.AESCipher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    /**
     * @Title:获取用户的详情
     * @param userId 用户ID
     */
    public User findUserInfoById(String userId) {
        try{
            userId = AESCipher.aesEncryptString(userId);
        }catch (Exception e){
        }

        return  repository.selectByPrimaryKey(userId);
    }
}
