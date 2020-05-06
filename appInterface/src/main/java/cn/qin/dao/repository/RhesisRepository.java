package cn.qin.dao.repository;

import cn.qin.base.repository.AbstractBaseRepository;
import cn.qin.dao.RhesisDao;
import cn.qin.dao.WordDao;
import cn.qin.entity.Rhesis;
import cn.qin.entity.Word;
import cn.qin.vo.spellVo.RadicalsVo;
import cn.qin.vo.spellVo.SpellVo;
import cn.qin.vo.wordVo.WordInfoVo;
import cn.qin.vo.wordVo.WordVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RhesisRepository extends AbstractBaseRepository<RhesisDao, Rhesis> {

    @Autowired
    private RhesisDao rhesisDao;

}
