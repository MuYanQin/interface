package cn.qin.controller;

import cn.qin.base.response.RestResponse;
import cn.qin.service.IdiomService;
import cn.qin.vo.ciYuvo.CiYuVo;
import cn.qin.vo.idiomVo.IdiomSearchVo;
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
     * @Description:搜索成语（匹配成语首字母和成语文字）
     * @param searchText 搜索内容
     */
    @RequestMapping(value = "findIdiomBySearchText",method = RequestMethod.GET)
    public ResponseEntity<RestResponse> findIdiomBySearchText(@RequestParam("searchText") String searchText){
        return new ResponseEntity<RestResponse>(idiomService.findIdiomBySearchText(searchText), HttpStatus.OK);
    }

    /**
     * @Title:随机获取多少条成语
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
     * @Title:获取详情
     * @param idiom 文字
     */
    @RequestMapping(value = "findDetailByIdiom",method = RequestMethod.GET)
    public ResponseEntity<RestResponse> findDetailByIdiom(@RequestParam("idiom") String idiom){
        return new ResponseEntity<RestResponse>(idiomService.findDetailByIdiom(idiom), HttpStatus.OK);
    }


    /**
     * @Title:获取全部成语列表按照首字母分类
     * @param idiomVo
     */
    @RequestMapping(value = "findAllIdiomList",method = RequestMethod.POST)
    public ResponseEntity<RestResponse> findAllIdiomList(@RequestBody IdiomVo idiomVo){
        return new ResponseEntity<RestResponse>(idiomService.findAllIdiomList(idiomVo), HttpStatus.OK);
    }


    /**
     * @Title:根据Tag分页获取列表
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

    /**
     * @param
     * @Title:根据类型获分页取成语 如 AABB。ABBC等
     */
    @RequestMapping(value = "selectIdiomByType",method = RequestMethod.POST)
    public ResponseEntity<RestResponse> selectIdiomByType(@RequestBody IdiomSearchVo idiomSearchVo){
        return new ResponseEntity<RestResponse>(idiomService.selectIdiomByType(idiomSearchVo), HttpStatus.OK);
    }



    /**
     * @Title:根据类型分页获取词语 如ABB AAB
     * @param ciYuVo
     */
    @RequestMapping(value = "selectCiYuByType",method = RequestMethod.POST)
    public ResponseEntity<RestResponse> selectCiYuByType(@RequestBody CiYuVo ciYuVo){
        return new ResponseEntity<RestResponse>(idiomService.selectCiYuByType(ciYuVo), HttpStatus.OK);
    }
}
