package cn.qin.service;

import cn.qin.dao.repository.SpellRepository;
import cn.qin.dao.repository.WordRepository;
import cn.qin.entity.Author;
import cn.qin.entity.Pome;
import cn.qin.entity.Spell;
import cn.qin.entity.Word;
import cn.qin.util.ArrayUtils;
import cn.qin.util.HttpClientUtil;
import cn.qin.util.SqlUtil;
import cn.qin.util.UUIDUtils;
import cn.qin.vo.PomeVo;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

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
        example.createCriteria().andEqualTo("selectKey","1");
        List<Spell> spellList = spellRepository.selectListByExample(example);
        if (ArrayUtils.isNotNullAndLengthNotZero(spellList)){
            List<Spell> spells = spellList.subList(0,100);
            for (Spell spell : spells) {
                findAndInsertData(spell.getPinyin(),spell);

            }
        }
        return null;
    }

    public   void findAndInsertWordData(){

        Example example = SqlUtil.newExample(Word.class);
        //example.createCriteria().andIsNull("pinyin");
        example.createCriteria().andEqualTo("word","好");
        List<Word> wordList = wordRepository.selectListByExample(example);
        if (ArrayUtils.isNotNullAndLengthNotZero(wordList)){
//            List<Word> words = wordList.subList(0,100);
            for (Word word : wordList) {
                findWordData(word);
            }
        }

    }

    private void findWordData(Word word){
        String text = "https://api.jisuapi.com/zidian/word?appkey=76399063a860b360&word=" + word.getWord();
        String  respon =  HttpClientUtil.doGet(text);
        JSONObject jsonObject = JSONObject.parseObject(respon);
        if (jsonObject.getString("status").equals("0")){


            JSONObject result = jsonObject.getJSONObject("result");
            JSONArray array = result.getJSONArray("explain");

            for (int i = 0; i < array.size(); i++) {
                JSONObject object =  (JSONObject)array.getJSONObject(i);
                if (i ==0){
                    word.setPinyin(object.getString("pinyin"));
                    word.setContent(object.getString("content"));
                    word.setBishun(result.getString("bishun"));
                    wordRepository.updateByPrimaryKeySelective(word);
                }else {
                    Word wordAdd = new Word();
                    BeanUtils.copyProperties(word,wordAdd);
                    wordAdd.setWordId(UUIDUtils.getUUID());
                    wordAdd.setPinyin(object.getString("pinyin"));
                    wordAdd.setContent(object.getString("content"));
                    wordAdd.setBishun(result.getString("bishun"));
                    wordRepository.insert(wordAdd);
                }
            }

        }
    }

    private Pome findAndInsertData(String spell,Spell spellE){
        Pome pome = new Pome();
        //聚合数据
        //wo 4ceace1b57595e7e10d2bdf6d3d8459c
        //hua  3d77403b636cd67b98d4fba306817d4b
        String string = "http://v.juhe.cn/xhzd/querypy?";
        String param = "dtype=&page=&pagesize=50&isjijie=&isxiangjie=&key=3d77403b636cd67b98d4fba306817d4b&word=" + spell;
        String text = string + param;
        String  respon =  HttpClientUtil.doGet(text);
        JSONObject jsonObject = JSONObject.parseObject(respon);
        if (jsonObject.getString("error_code").equals("0")){


            JSONObject result = jsonObject.getJSONObject("result");
            JSONArray array = result.getJSONArray("list");

            for (int i = 0; i < array.size(); i++) {
                JSONObject object =  (JSONObject)array.getJSONObject(i);
                Word word = new Word();
                word.setWordId(UUIDUtils.getUUID());
                word.setWord(object.getString("zi"));
                word.setInitial(spellE.getPinyinKey());
                word.setPy(spellE.getPinyin());
                word.setBushou(object.getString("bushou"));
                word.setBihua(object.getString("bihua"));
                word.setSpell(object.getString("pinyin"));
                wordRepository.insert(word);
            }
            if (array.size()==50){
                spellE.setType("1");
            }
            spellE.setSelectKey("2");
            spellE.setPaixu(spellE.getSpellId());
            spellRepository.updateByPrimaryKeySelective(spellE);
        }
        return pome;
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
