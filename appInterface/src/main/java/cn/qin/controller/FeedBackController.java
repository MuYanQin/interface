package cn.qin.controller;

import cn.qin.base.response.RestResponse;
import cn.qin.entity.FeedBack;
import cn.qin.entity.User;
import cn.qin.service.FeedBackService;
import cn.qin.vo.feedBack.FeedBackVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("feedBack")
public class FeedBackController {
    @Autowired
    private FeedBackService feedBackService;

    @RequestMapping(value = "commiteIdea",method = RequestMethod.POST)
    public ResponseEntity<RestResponse> commiteIdea(@RequestBody FeedBackVo feedBackVo){
        return new ResponseEntity<RestResponse>(feedBackService.commiteIdea(feedBackVo), HttpStatus.OK);
    }

}
