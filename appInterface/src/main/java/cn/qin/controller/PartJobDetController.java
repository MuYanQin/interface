package cn.qin.controller;

import cn.qin.base.response.RestResponse;
import cn.qin.service.PartJobDetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("partJobDet")
public class PartJobDetController {
    @Autowired
    private PartJobDetService partJobDetService;
    @RequestMapping(value = "findPartJobDetById" ,method = RequestMethod.GET)
    public ResponseEntity<RestResponse> findPartJobDetById(@RequestParam("partJobDetId") String partJobDetId){
        return new ResponseEntity<RestResponse>(partJobDetService.findPartJobDetById(partJobDetId), HttpStatus.OK);
    }
}
