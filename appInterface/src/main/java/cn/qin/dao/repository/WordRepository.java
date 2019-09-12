package cn.qin.dao.repository;

import cn.qin.base.repository.AbstractBaseRepository;
import cn.qin.dao.WordDao;
import cn.qin.entity.Word;
import cn.qin.vo.spellVo.RadicalsVo;
import cn.qin.vo.spellVo.SpellVo;
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
}
