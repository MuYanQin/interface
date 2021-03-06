package cn.qin.controller;

import cn.qin.base.response.RestResponse;
import cn.qin.base.response.RestResponseGenerator;
import cn.qin.entity.User;
import cn.qin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;
    /**
     * @Title:获取用户的详情
     * @param
     */
    @RequestMapping(value = "findUserInfo",method = RequestMethod.GET)
    public ResponseEntity<RestResponse> findUserInfo(){
        return new ResponseEntity<RestResponse>(userService.findUserInfo(), HttpStatus.OK);
    }

    /**
     * @Title:重置密码
     * @param user
     */
    @RequestMapping(value = "reset",method = RequestMethod.POST)
    public ResponseEntity<RestResponse> reset(@RequestBody User user){
        return new ResponseEntity<RestResponse>(userService.reset(user), HttpStatus.OK);
    }

    /**
     * @Title:修改用户信息
     * @param user
     */
    @RequestMapping(value = "updateInfo",method = RequestMethod.POST)
    public ResponseEntity<RestResponse> updateInfo(@RequestBody User user){
        return new ResponseEntity<RestResponse>(userService.updateInfo(user), HttpStatus.OK);
    }


    /**
     * @Title:注册接口
     * @param user
     */
    @RequestMapping(value = "register",method = RequestMethod.POST)
    public ResponseEntity<RestResponse> register(@RequestBody User user){
        return new ResponseEntity<RestResponse>(userService.register(user), HttpStatus.OK);
    }

    /**
     * @Title:登录接口
     * @param user
     */
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public ResponseEntity<RestResponse> login(@RequestBody User user){
        return new ResponseEntity<RestResponse>(userService.login(user), HttpStatus.OK);
    }
}
