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
     * @param userId 用户ID
     */
    @RequestMapping(value = "findUserInfo",method = RequestMethod.GET)
    public ResponseEntity<RestResponse> findUserInfo(){
        return new ResponseEntity<RestResponse>(RestResponseGenerator.genSuccessResponse(userService.findUserInfo()), HttpStatus.OK);
    }

    /**
     * @Title:注册接口
     * @param user
     */
    @RequestMapping(value = "register",method = RequestMethod.POST)
    public ResponseEntity<RestResponse> register(@RequestBody User user){
        return new ResponseEntity<RestResponse>(RestResponseGenerator.genSuccessResponse(userService.register(user)), HttpStatus.OK);
    }

    /**
     * @Title:登录接口
     * @param user
     */
    @RequestMapping(value = "login",method = RequestMethod.GET)
    public ResponseEntity<RestResponse> login(@RequestBody User user){
        return new ResponseEntity<RestResponse>(RestResponseGenerator.genSuccessResponse(userService.login(user)), HttpStatus.OK);
    }
}
