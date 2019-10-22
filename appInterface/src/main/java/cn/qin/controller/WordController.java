package cn.qin.controller;

import cn.qin.base.response.ResponseEnums;
import cn.qin.base.response.RestResponse;
import cn.qin.base.response.RestResponseGenerator;
import cn.qin.service.PomeService;
import cn.qin.service.WordService;
import cn.qin.vo.ciYuvo.CiYuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;

@CrossOrigin
@RestController
@RequestMapping("word")
public class WordController {
    @Autowired
    private WordService wordService;
    /**
     * @Title:
     * @Description:
     */
    @RequestMapping(value = "findWordById",method = RequestMethod.GET)
    public ResponseEntity<RestResponse> findWordById(@RequestParam("wordId") String wordId){
        return new ResponseEntity<RestResponse>(wordService.findWordById(wordId), HttpStatus.OK);
    }
    /**
     * @Title:获取拼音列表
     * @param
     */
    @RequestMapping(value = "findSpellList",method = RequestMethod.GET)
    public ResponseEntity<RestResponse> findSpellList(){
        return new ResponseEntity<RestResponse>(wordService.findSpellList(), HttpStatus.OK);
    }
    /**
     * @Title:获取部首列表
     * @param
     */
    @RequestMapping(value = "findRadicalsList",method = RequestMethod.GET)
    public ResponseEntity<RestResponse> findRadicalsList(){
        return new ResponseEntity<RestResponse>(wordService.findRadicalsList(), HttpStatus.OK);
    }

    /**
     * @Title:根据部首获取汉字列表
     * @param
     */
    @RequestMapping(value = "findWordListByBuShou",method = RequestMethod.GET)
    public ResponseEntity<RestResponse> findWordListByBuShou(@RequestParam("bushou") String bushou){
        return new ResponseEntity<RestResponse>(wordService.findWordListByBuShou(bushou), HttpStatus.OK);
    }

    /**
     * @Title:根据拼音获取汉字列表
     * @param
     */
    @RequestMapping(value = "findWordListBySpell",method = RequestMethod.GET)
    public ResponseEntity<RestResponse> findWordListBySpell(@RequestParam("spell") String spell){
        return new ResponseEntity<RestResponse>(wordService.findWordListBySpell(spell), HttpStatus.OK);
    }

    /**
     * @Title:根据文字获取相关文字信息
     * @param
     */
    @RequestMapping(value = "findWordInfoByWord",method = RequestMethod.GET)
    public ResponseEntity<RestResponse> findWordInfoByWord(@RequestParam("word") String word){
        return new ResponseEntity<RestResponse>(wordService.findWordInfoByWord(word), HttpStatus.OK);
    }
}
