package cn.qin.service;

import cn.qin.base.response.RestResponse;
import cn.qin.base.response.RestResponseGenerator;
import cn.qin.base.vo.PageQuery;
import cn.qin.constancts.SystemConstants;
import cn.qin.dao.repository.CiYuRepository;
import cn.qin.dao.repository.SpellRepository;
import cn.qin.dao.repository.WordRepository;
import cn.qin.entity.CiYu;
import cn.qin.entity.Word;
import cn.qin.enums.DeleteFlags;
import cn.qin.util.*;
import cn.qin.vo.ciYuvo.CiYuVo;
import cn.qin.vo.idiomVo.IdiomVo;
import cn.qin.vo.spellVo.RadicalsVo;
import cn.qin.vo.spellVo.SpellVo;
import cn.qin.vo.wordVo.WordInfoVo;
import cn.qin.vo.wordVo.WordVo;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Response;
import java.util.*;

@Slf4j
@Service
public class WordService {
    @Autowired
    private SpellRepository spellRepository;

    @Autowired
    private WordRepository wordRepository;
    @Autowired
    private CiYuRepository ciYuRepository;
    //聚合数据
    //wo 4ceace1b57595e7e10d2bdf6d3d8459c
    //hua 3d77403b636cd67b98d4fba306817d4b
    //鸡鸡 f47037e4c2f39b937a77c7b5766ea4a0

    public RestResponse<Word> findWordById(String wordId){
        Word word = wordRepository.selectByPrimaryKey(wordId);
        return RestResponseGenerator.genSuccessResponse(word);
    }
    private  String findText(String text){
        text = text.replaceAll("</p>","\n");
        text = text.replaceAll("<br />","\n");
        text = text.replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll(
                "<[^>]*>", "");
        text = text.replaceAll("[(/>)<]", "").trim();

        return  text;
    }


    /**
     * @Title:获取拼音列表
     * @param
     */
    public RestResponse<Map> findSpellList(){
        List<SpellVo> spellVos  = wordRepository.findSpellList();
        Map<String,Object> map = new HashMap<>();
        if (ArrayUtils.isNotNullAndLengthNotZero(spellVos)){
            for (SpellVo spellVo:spellVos) {
                map.put(spellVo.getPinyinKey(),spellVo.getSpellVos());
            }
        }
        return RestResponseGenerator.genSuccessResponse(map);
    }

    /**
     * @Title:获取部首列表
     * @param
     */
    public RestResponse<LinkedHashMap<String,Object>> findRadicalsList(){
        List<RadicalsVo> radicalsVos  = wordRepository.findRadicalsList();
        LinkedHashMap<String,Object> linkedHashMap = new LinkedHashMap<>();
        if (ArrayUtils.isNotNullAndLengthNotZero(radicalsVos)){
            for (RadicalsVo radicalsVo:radicalsVos) {
                linkedHashMap.put(radicalsVo.getBihua()+"",radicalsVo.getRadicalsVos());
            }
        }
        return RestResponseGenerator.genSuccessResponse(linkedHashMap);
    }
    /**
     * @Title:根据部首获取汉字列表
     * @param
     */
    public RestResponse<LinkedHashMap<String,Object>>  findWordListByBuShou(String bushou){
        List<WordVo> wordVos  = wordRepository.findWordListByBuShou(bushou);
        return buildWordList(wordVos);
    }

    /**
     * @Title:根据拼音获取汉字列表
     * @param
     */
    public RestResponse<LinkedHashMap<String,Object>> findWordListBySpell(String spell){
        List<WordVo> wordVos  = wordRepository.findWordListBySpell(spell);
        return buildWordList(wordVos);
    }

    private RestResponse<LinkedHashMap<String,Object>> buildWordList(List<WordVo> wordVos){
        LinkedHashMap<String,Object> linkedHashMap = new LinkedHashMap<>();
        if (ArrayUtils.isNotNullAndLengthNotZero(wordVos)){
            for (WordVo wordVo:wordVos) {
                linkedHashMap.put(wordVo.getBihua()+"",wordVo.getWordVos());
            }
        }
        return RestResponseGenerator.genSuccessResponse(linkedHashMap);
    }
    /**
     * @Title:根据文字获取相关文字信息
     * @param
     */
    public RestResponse<Map> findWordInfoByWord(String word){
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
        String userId = request.getHeader(SystemConstants.DEUSERID);
        Map map = new HashMap();
        List<WordInfoVo> wordInfoVoList = wordRepository.findWordInfoByWord(word,userId);

        for (WordInfoVo wordInfoVo:wordInfoVoList) {
            List<IdiomVo> list = new ArrayList<>();
            if (ArrayUtils.isNotNullAndLengthNotZero(wordInfoVo.getIdiomVos())){
                for (IdiomVo idiomVo:wordInfoVo.getIdiomVos()) {
                    int index = idiomVo.getIdiom().replace("，","").indexOf(word);
                    String[] strings = idiomVo.getSpell().split(" ");
                    if (strings[index].equals(wordInfoVo.getPinyin())){
                        list.add(idiomVo);
                    }
                }
            }
            wordInfoVo.setIdiomList(list);

            if (ArrayUtils.isNotNullAndLengthNotZero(wordInfoVo.getCiYuVos())){
                List<CiYuVo> ciYuVoList = new ArrayList<>();

                for (CiYuVo ciYuVo:wordInfoVo.getCiYuVos()) {
                    int index = ciYuVo.getCi().replace("，","").indexOf(word);
                    String[] strings = ciYuVo.getSpell().split(" ");
                    if (strings[index].equals(wordInfoVo.getPinyin())){
                        ciYuVoList.add(ciYuVo);
                    }
                }
                wordInfoVo.setCiyuList(ciYuVoList);
            }
        }

        map.put("wordInfoVoList",wordInfoVoList);
        map.put("collectionId",wordInfoVoList.get(0).getCollectionId());
        return RestResponseGenerator.genSuccessResponse(map);
    }


}
