package cn.qin.controller;

import cn.qin.service.FeedBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("feedBack")
public class FeedBackController {
    @Autowired
    private FeedBackService feedBackService;


}
