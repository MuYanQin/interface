package cn.qin.service;

import cn.qin.dao.repository.SpellRepository;
import cn.qin.dao.repository.WordRepository;
import cn.qin.entity.Pome;
import cn.qin.entity.Spell;
import cn.qin.entity.Word;
import cn.qin.util.*;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class WordService {
    @Autowired
    private SpellRepository spellRepository;

    @Autowired
    private WordRepository wordRepository;

    public Word findWordById(String wordId){

        Example example = SqlUtil.newExample(Spell.class);
        example.createCriteria().andEqualTo("type","1").andIsNull("totalPage");
        List<Spell> spellList = spellRepository.selectListByExample(example);
        if (ArrayUtils.isNotNullAndLengthNotZero(spellList)){
            if (spellList.size()>100){
                spellList  = spellList.subList(0,100);
            }
            for (Spell spell:spellList) {
                findAndInsertData(spell.getPinyin(),spell);
            }
        }


        return null;
    }

    public   void findAndInsertWordData(){
        List<String> list = Arrays.asList("嵒","盔","东","玖","谍","义","榺","侍","阎","闩","蹊","外","汇","邰","舔","醸","搔","泰","妍","洪");
        Example example = SqlUtil.newExample(Word.class);
        example.createCriteria().andIsNull("pinyin").andIsNull("delFlag");
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
            word.setDelFlag("5");
            wordRepository.updateByPrimaryKeySelective(word);
            return;
        }
        if (jsonObject.getString("status").equals("0")){


            JSONObject result = jsonObject.getJSONObject("result");
            JSONArray array = result.getJSONArray("explain");

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

                    wordRepository.updateByPrimaryKeySelective(word);
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
                    wordRepository.insert(wordAdd);
                }
            }

        }else if (jsonObject.getString("status").equals("203")){
            word.setDelFlag("1");
            wordRepository.updateByPrimaryKeySelective(word);

        }
    }

    private void findAndInsertData(String spell,Spell spellE){
        Pome pome = new Pome();
        //聚合数据
        //wo 4ceace1b57595e7e10d2bdf6d3d8459c
        //hua 3d77403b636cd67b98d4fba306817d4b
        //鸡鸡 f47037e4c2f39b937a77c7b5766ea4a0
        String string = "http://v.juhe.cn/xhzd/querypy?";
        String param = "dtype=&page=1&pagesize=50&isjijie=&isxiangjie=&key=f47037e4c2f39b937a77c7b5766ea4a0&word=" + spell;
        String text = string + param;
        String  respon =  HttpClientUtil.doGet(text);
        JSONObject jsonObject = JSONObject.parseObject(respon);
        if (jsonObject.getString("error_code").equals("0")){


            JSONObject result = jsonObject.getJSONObject("result");
            JSONArray array = result.getJSONArray("list");
            spellE.setTotalPage(result.getString("totalpage"));
            spellRepository.updateByPrimaryKeySelective(spellE);
//            for (int i = 0; i < array.size(); i++) {
//                JSONObject object =  (JSONObject)array.getJSONObject(i);
//
//                Example example = SqlUtil.newExample(Word.class);
//                example.createCriteria().andEqualTo("word",object.getString("zi"));
//                List<Word> wordList = wordRepository.selectListByExample(example);
//                if (ArrayUtils.isNullOrLengthZero(wordList)){
//                    Word word = new Word();
//                    word.setWordId(UUIDUtils.getUUID());
//                    word.setWord(object.getString("zi"));
////                    word.setInitial(spellE.getPinyinKey());
//                    word.setBushou(object.getString("bushou"));
//                    word.setBihua(object.getString("bihua"));
//                    word.setSpell(object.getString("pinyin"));
//                    wordRepository.insert(word);
//                }
//            }
//            if (array.size()==50){
//                spellE.setType("8");
//            }
//            spellE.setPaixu(spellE.getSpellId());
//            spellRepository.updateByPrimaryKeySelective(spellE);
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
}
