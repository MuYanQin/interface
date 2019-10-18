package cn.qin.dao.repository;

import cn.qin.base.repository.AbstractBaseRepository;
import cn.qin.dao.CiYuDao;
import cn.qin.dao.WordDao;
import cn.qin.entity.CiYu;
import cn.qin.entity.Word;
import cn.qin.vo.spellVo.RadicalsVo;
import cn.qin.vo.spellVo.SpellVo;
import cn.qin.vo.wordVo.WordInfoVo;
import cn.qin.vo.wordVo.WordVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CiYuRepository extends AbstractBaseRepository<CiYuDao, CiYu> {

    @Autowired
    private CiYuDao ciYuDao;
}
