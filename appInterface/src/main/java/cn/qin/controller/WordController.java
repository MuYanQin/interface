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

}
