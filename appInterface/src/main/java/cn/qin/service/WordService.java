package cn.qin.service;

import cn.qin.dao.repository.SpellRepository;
import cn.qin.dao.repository.WordRepository;
import cn.qin.entity.Word;
import cn.qin.enums.DeleteFlags;
import cn.qin.util.*;
import cn.qin.vo.spellVo.RadicalsVo;
import cn.qin.vo.spellVo.SpellVo;
import cn.qin.vo.wordVo.WordVo;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class WordService {
    @Autowired
    private SpellRepository spellRepository;

    @Autowired
    private WordRepository wordRepository;
    //聚合数据
    //wo 4ceace1b57595e7e10d2bdf6d3d8459c
    //hua 3d77403b636cd67b98d4fba306817d4b
    //鸡鸡 f47037e4c2f39b937a77c7b5766ea4a0

    public Word findWordById(String wordId){



        return null;
    }

    public   void findAndInsertWordData(){
        Example example = SqlUtil.newExample(Word.class);
        example.createCriteria().andIsNull("pinyin");
        List<Word> wordList = wordRepository.selectListByExample(example);
        if (ArrayUtils.isNotNullAndLengthNotZero(wordList)){
            if (wordList.size()>100){
                wordList  = wordList.subList(0,100);
            }
            for (Word word : wordList) {
                findWordData(word);
            }
        }
    }

    private void findWordData(Word word){
        //鸡鸡 76399063a860b360
        //我 a8d949a2591c8d0f
        //花 c064ed1f4ff90141
        String text = "https://api.jisuapi.com/zidian/word?appkey=c064ed1f4ff90141&word=" + word.getWord();
        String  respon =  HttpClientUtil.doGet(text);
        JSONObject jsonObject = JSONObject.parseObject(respon);
        if (StringUtils.isTrimBlank(respon)){
            word.setDelFlag(5);
            wordRepository.updateByPrimaryKeySelectiveData(word);
            return;
        }
        if (jsonObject.getString("status").equals("0")){


            JSONObject result = jsonObject.getJSONObject("result");
            JSONArray array = result.getJSONArray("explain");
            if (array.size()==0){
                word.setBishun(result.getString("bishun"));
                word.setPy(result.getString("pinyin"));
                word.setDelFlag(DeleteFlags.NOT_EXIST.getFlag());
                wordRepository.updateByPrimaryKeySelectiveData(word);

            }
            for (int i = 0; i < array.size(); i++) {
                JSONObject object =  (JSONObject)array.getJSONObject(i);
                if (i ==0){
                    word.setPinyin(object.getString("pinyin"));
                    if (object.getString("content").contains("<p")){
                        word.setContent(findText(object.getString("content")));
                    }else {
                        word.setContent(object.getString("content"));
                    }
                    word.setBishun(result.getString("bishun"));
                    word.setPy(object.getString("pinyin"));

                    wordRepository.updateByPrimaryKeySelectiveData(word);
                }else {
                    Word wordAdd = new Word();
                    BeanUtils.copyProperties(word,wordAdd);
                    wordAdd.setWordId(UUIDUtils.getUUID());
                    wordAdd.setPy(object.getString("pinyin"));
                    wordAdd.setPinyin(object.getString("pinyin"));
                    if (object.getString("content").contains("<p")){
                        wordAdd.setContent(findText(object.getString("content")));
                    }else {
                        wordAdd.setContent(object.getString("content"));
                    }
                    wordAdd.setBishun(result.getString("bishun"));
                    wordRepository.insertData(wordAdd);
                }
            }

        }else if (jsonObject.getString("status").equals("203")){
            word.setDelFlag(1);
            wordRepository.updateByPrimaryKeySelectiveData(word);

        }
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
    public Map findSpellList(){
        List<SpellVo> spellVos  = wordRepository.findSpellList();
        Map<String,Object> map = new HashMap<>();
        if (ArrayUtils.isNotNullAndLengthNotZero(spellVos)){
            for (SpellVo spellVo:spellVos) {
                map.put(spellVo.getPinyinKey(),spellVo.getSpellVos());
            }
        }
        return map;
    }

    /**
     * @Title:获取部首列表
     * @param
     */
    public LinkedHashMap<String,Object> findRadicalsList(){
        List<RadicalsVo> radicalsVos  = wordRepository.findRadicalsList();
        LinkedHashMap<String,Object> linkedHashMap = new LinkedHashMap<>();
        if (ArrayUtils.isNotNullAndLengthNotZero(radicalsVos)){
            for (RadicalsVo radicalsVo:radicalsVos) {
                linkedHashMap.put(radicalsVo.getBihua()+"",radicalsVo.getRadicalsVos());
            }
        }
        return linkedHashMap;
    }
    /**
     * @Title:根据部首获取汉字列表
     * @param
     */
    public LinkedHashMap<String,Object> findWordListByBuShou(String bushou){
        List<WordVo> wordVos  = wordRepository.findWordListByBuShou(bushou);
        LinkedHashMap<String,Object> linkedHashMap = new LinkedHashMap<>();
        if (ArrayUtils.isNotNullAndLengthNotZero(wordVos)){
            for (WordVo wordVo:wordVos) {
                linkedHashMap.put(wordVo.getBihua()+"",wordVo.getWordVos());
            }
        }
        return linkedHashMap;
    }
}
