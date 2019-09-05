package cn.qin.controller;

import cn.qin.base.response.RestResponse;
import cn.qin.base.response.RestResponseGenerator;
import cn.qin.entity.Idiom;
import cn.qin.service.IdiomService;
import cn.qin.vo.IdiomListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("idiom")
public class IdiomController {
    @Autowired
    private IdiomService idiomService;
    /**
     * @Title:搜索成语
     */
    @RequestMapping(value = "findIdiomBySearchText",method = RequestMethod.GET)
    public ResponseEntity<RestResponse> findIdiomBySearchText(@RequestParam("searchText") String searchText){
        return new ResponseEntity<RestResponse>(RestResponseGenerator.genSuccessResponse(idiomService.findIdiomBySearchText(searchText)), HttpStatus.OK);
    }

    /**
     * @Title:随机获取成语
     */
    @RequestMapping(value = "findRandomForSize",method = RequestMethod.GET)
    public ResponseEntity<RestResponse> findRandomForSize(String size){
        return new ResponseEntity<RestResponse>(RestResponseGenerator.genSuccessResponse(idiomService.findRandomForSize(size)), HttpStatus.OK);
    }
    /**
     * @Title:获取详情
     * @param idiomId ID
     */
    @RequestMapping(value = "findDetailById",method = RequestMethod.GET)
    public ResponseEntity<RestResponse> findDetailById(@RequestParam("idiomId") String idiomId){
        return new ResponseEntity<RestResponse>(RestResponseGenerator.genSuccessResponse(idiomService.findDetailById(idiomId)), HttpStatus.OK);
    }

    /**
     * @Title:根据首字母获取列表
     * @param initial 首字母 为空 则获取全部(a-z的每条 前100条) 不为空则按initial获取
     */
    @RequestMapping(value = "findIdiomListByInitial",method = RequestMethod.GET)
    public ResponseEntity<RestResponse> findIdiomListByInitial(@RequestParam("initial") String initial){
        return new ResponseEntity<RestResponse>(RestResponseGenerator.genSuccessResponse(idiomService.findIdiomListByInitial(initial)), HttpStatus.OK);
    }
}
