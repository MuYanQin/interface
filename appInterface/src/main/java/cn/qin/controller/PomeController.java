package cn.qin.controller;

import cn.qin.base.response.RestResponse;
import cn.qin.base.response.RestResponseGenerator;
import cn.qin.service.PomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pome")
public class PomeController {
    @Autowired
    private PomeService pomeService;
    /**
     * @Title:
     * @Description: 根据id获取古诗
     */
    @RequestMapping(value = "findPomeById",method = RequestMethod.GET)
    public ResponseEntity<RestResponse> findPomeById(@RequestParam("pomeId") String pomeId){
        return new ResponseEntity<RestResponse>(RestResponseGenerator.genSuccessResponse(pomeService.findPomeById(pomeId)), HttpStatus.OK);
    }

    /**
     * @Title:
     * @Description: 根据id获取古诗
     */
    @RequestMapping(value = "findPomeByPage",method = RequestMethod.GET)
    public ResponseEntity<RestResponse> findPomeByPage(@RequestParam("pageIndex") String pageIndex){
        return new ResponseEntity<RestResponse>(RestResponseGenerator.genSuccessResponse(pomeService.findPomeByPage(pageIndex)), HttpStatus.OK);
    }

}
