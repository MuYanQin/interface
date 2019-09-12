package cn.qin.controller;

import cn.qin.base.response.ResponseEnums;
import cn.qin.base.response.RestResponse;
import cn.qin.base.response.RestResponseGenerator;
import cn.qin.service.PomeService;
import cn.qin.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;

@RestController
@RequestMapping("word")
public class WordController {
    @Autowired
    private WordService wordService;
    /**
     * @Title:
     * @Description: 根据id获取古诗
     */
    @RequestMapping(value = "findWordById",method = RequestMethod.GET)
    public ResponseEntity<RestResponse> findWordById(@RequestParam("wordId") String wordId){
        return new ResponseEntity<RestResponse>(RestResponseGenerator.genSuccessResponse(wordService.findWordById(wordId)), HttpStatus.OK);
    }

    @RequestMapping(value = "findAndInsertWordData",method = RequestMethod.GET)
    public ResponseEntity<RestResponse> findAndInsertWordData(){
        wordService.findAndInsertWordData();
        return new ResponseEntity<RestResponse>(RestResponseGenerator.genSuccessResponse(ResponseEnums.SUCCESS.getMark()), HttpStatus.OK);
    }

    /**
     * @Title:获取拼音列表
     * @param
     */
    @RequestMapping(value = "findSpellList",method = RequestMethod.GET)
    public ResponseEntity<RestResponse> findSpellList(){
        return new ResponseEntity<RestResponse>(RestResponseGenerator.genSuccessResponse(wordService.findSpellList()), HttpStatus.OK);
    }
    /**
     * @Title:获取部首列表
     * @param
     */
    @RequestMapping(value = "findRadicalsList",method = RequestMethod.GET)
    public ResponseEntity<RestResponse> findRadicalsList(){
        return new ResponseEntity<RestResponse>(RestResponseGenerator.genSuccessResponse(wordService.findRadicalsList()), HttpStatus.OK);
    }

    /**
     * @Title:根据部首获取汉字列表
     * @param
     */
    @RequestMapping(value = "findWordListByBuShou",method = RequestMethod.GET)
    public ResponseEntity<RestResponse> findWordListByBuShou(@RequestParam("bushou") String bushou){
        return new ResponseEntity<RestResponse>(RestResponseGenerator.genSuccessResponse(wordService.findWordListByBuShou(bushou)), HttpStatus.OK);
    }
}
