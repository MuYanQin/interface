package cn.qin.controller;

import cn.qin.base.response.RestResponse;
import cn.qin.base.response.RestResponseGenerator;
import cn.qin.service.PomeService;
import cn.qin.vo.pomeVo.PomeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pome")
public class PomeController {
    @Autowired
    private PomeService pomeService;
    /**
     * @Title:
     * @Description: 根据id获取古诗
     */
    @RequestMapping(value = "findPomeDetailById",method = RequestMethod.GET)
    public ResponseEntity<RestResponse> findPomeById(@RequestParam("pomeId") String pomeId){
        return new ResponseEntity<RestResponse>(RestResponseGenerator.genSuccessResponse(pomeService.findPomeDetailById(pomeId)), HttpStatus.OK);
    }

    /**
     * @Title:
     * @Description: 根据id获取古诗
     */
    @RequestMapping(value = "findPomeListByPage",method = RequestMethod.POST)
    public ResponseEntity<RestResponse> findPomeByPage(@RequestBody PomeVo pomeVo){
        return new ResponseEntity<RestResponse>(RestResponseGenerator.genSuccessResponse(pomeService.findPomeListByPage(pomeVo)), HttpStatus.OK);
    }

}
