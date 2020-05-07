package cn.qin.dao.repository;

import cn.qin.base.repository.AbstractBaseRepository;
import cn.qin.dao.WordDao;
import cn.qin.entity.Word;
import cn.qin.vo.ciYuvo.CiYuVo;
import cn.qin.vo.idiomVo.IdiomVo;
import cn.qin.vo.spellVo.RadicalsVo;
import cn.qin.vo.spellVo.SpellVo;
import cn.qin.vo.wordVo.WordInfoVo;
import cn.qin.vo.wordVo.WordVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WordRepository extends AbstractBaseRepository<WordDao, Word> {

    @Autowired
    private WordDao wordDao;
    public List<SpellVo> findSpellList(){
        return wordDao.findSpellList();
    }

    public List<RadicalsVo> findRadicalsList(){
        return wordDao.findRadicalsList();
    }

    public List<WordVo> findWordListByBuShou(String bushou){
        return wordDao.findWordListByBuShou(bushou);
    }

    public List<WordVo> findWordListBySpell(String spell){
        return wordDao.findWordListBySpell(spell);
    }

    public List<WordVo> findWordListByWord(String word){
        return wordDao.findWordListByWord(word);
    }

    public List<CiYuVo> selectciyiByWord(String word , String pinyin){
        return wordDao.selectciyiByWord(word,pinyin);
    }

    public List<IdiomVo> selectIdiomByWord(String word, String pinyin){
        return wordDao.selectIdiomByWord(word,pinyin);
    }

    public List<WordInfoVo> findWordInfoByWord(String word,String userId){
        return wordDao.findWordInfoByWord(word,userId);
    }
}
