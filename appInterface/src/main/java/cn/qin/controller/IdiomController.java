package cn.qin.controller;

import cn.qin.base.response.RestResponse;
import cn.qin.base.response.RestResponseGenerator;
import cn.qin.entity.Idiom;
import cn.qin.service.IdiomService;
import cn.qin.vo.idiomVo.IdiomVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return new ResponseEntity<RestResponse>(idiomService.findIdiomBySearchText(searchText), HttpStatus.OK);
    }

    /**
     * @Title:随机获取成语
     */
    @RequestMapping(value = "findRandomForSize",method = RequestMethod.GET)
    public ResponseEntity<RestResponse> findRandomForSize(String size){
        return new ResponseEntity<RestResponse>(idiomService.findRandomForSize(size), HttpStatus.OK);
    }
    /**
     * @Title:获取详情
     * @param idiomId ID
     */
    @RequestMapping(value = "findDetailById",method = RequestMethod.GET)
    public ResponseEntity<RestResponse> findDetailById(@RequestParam("idiomId") String idiomId){
        return new ResponseEntity<RestResponse>(idiomService.findDetailById(idiomId), HttpStatus.OK);
    }

    /**
     * @Title:获取全部列表
     * @param idiomVo
     */
    @RequestMapping(value = "findAllIdiomList",method = RequestMethod.POST)
    public ResponseEntity<RestResponse> findAllIdiomList(@RequestBody IdiomVo idiomVo){
        return new ResponseEntity<RestResponse>(idiomService.findAllIdiomList(idiomVo), HttpStatus.OK);
    }


    /**
     * @Title:根据Tag获取列表
     * @param idiomVo
     */
    @RequestMapping(value = "findIdiomListByTag",method = RequestMethod.POST)
    public ResponseEntity<RestResponse> findIdiomListByTag(@RequestBody IdiomVo idiomVo){
        return new ResponseEntity<RestResponse>(idiomService.findIdiomListByTag(idiomVo), HttpStatus.OK);
    }

    /**
     * @Title:每日一首
     * @param
     */
    @RequestMapping(value = "findIdiomDaily",method = RequestMethod.GET)
    public ResponseEntity<RestResponse> findIdiomDaily(){
        return new ResponseEntity<RestResponse>(idiomService.findIdiomDaily(), HttpStatus.OK);
    }
}
