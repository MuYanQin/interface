package cn.qin.controller;

import cn.qin.base.response.RestResponse;
import cn.qin.base.response.RestResponseGenerator;
import cn.qin.service.IdiomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("idiom")
public class IdiomController {
    @Autowired
    private IdiomService idiomService;
    /**
     * @Title:
     * @Description: 根据id获取古诗
     */
    @RequestMapping(value = "findIdiomBySearchText",method = RequestMethod.GET)
    public ResponseEntity<RestResponse> findIdiomBySearchText(@RequestParam("searchText") String searchText){
        return new ResponseEntity<RestResponse>(RestResponseGenerator.genSuccessResponse(idiomService.findIdiomBySearchText(searchText)), HttpStatus.OK);
    }
}
