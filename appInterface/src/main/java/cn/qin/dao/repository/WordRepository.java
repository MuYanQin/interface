package cn.qin.dao.repository;

import cn.qin.base.repository.AbstractBaseRepository;
import cn.qin.dao.WordDao;
import cn.qin.entity.Word;
import cn.qin.vo.spellVo.RadicalsVo;
import cn.qin.vo.spellVo.SpellVo;
import cn.qin.vo.wordVo.WordInfoVo;
import cn.qin.vo.wordVo.WordVo;
import org.apache.ibatis.annotations.Param;
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
    public List<WordInfoVo> findWordInfoByWord(String word,String userId){
        return wordDao.findWordInfoByWord(word,userId);
    }
}
