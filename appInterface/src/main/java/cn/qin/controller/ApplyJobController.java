package cn.qin.controller;

import cn.qin.base.response.RestResponse;
import cn.qin.entity.ApplyJob;
import cn.qin.service.ApplyJobService;
import cn.qin.vo.applyJob.ApplyJobVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("applyJob")
public class ApplyJobController {
    @Autowired
    private ApplyJobService applyJobService;

    @RequestMapping(value = "findApplyJobList" ,method = RequestMethod.GET)
    public ResponseEntity<RestResponse> findApplyJobList(ApplyJobVo applyJob){
        return new ResponseEntity<>(applyJobService.findApplyJobList(applyJob), HttpStatus.OK);
    }


    @RequestMapping(value = "deleteParyJob" ,method = RequestMethod.POST)
    public ResponseEntity<RestResponse> deleteParyJob(@RequestBody ApplyJob applyJob){
        return new ResponseEntity<>(applyJobService.deleteParyJob(applyJob), HttpStatus.OK);
    }


    @RequestMapping(value = "applyParyJob" ,method = RequestMethod.POST)
    public ResponseEntity<RestResponse> applyParyJob(@RequestBody ApplyJob applyJob){
        return new ResponseEntity<>(applyJobService.applyParyJob(applyJob), HttpStatus.OK);
    }

}
