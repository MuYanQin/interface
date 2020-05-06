package cn.qin.controller;

import cn.qin.service.ApplyJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("applyJob")
public class ApplyJobController {
    @Autowired
    private ApplyJobService applyJobService;

}
