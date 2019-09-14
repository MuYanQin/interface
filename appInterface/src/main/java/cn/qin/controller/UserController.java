package cn.qin.controller;

import cn.qin.base.response.ResponseEnums;
import cn.qin.base.response.RestResponse;
import cn.qin.base.response.RestResponseGenerator;
import cn.qin.entity.User;
import cn.qin.service.UserService;
import cn.qin.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;
    /**
     * @Title:获取用户的详情
     * @param userId 用户ID
     */
    @RequestMapping(value = "findUserInfoById",method = RequestMethod.GET)
    public ResponseEntity<RestResponse> findUserInfoById(@RequestParam("userId") String userId){
        return new ResponseEntity<RestResponse>(RestResponseGenerator.genSuccessResponse(userService.findUserInfoById(userId)), HttpStatus.OK);
    }
}
