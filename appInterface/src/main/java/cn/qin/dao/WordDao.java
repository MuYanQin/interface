package cn.qin.dao;

import cn.qin.base.dao.BaseDao;
import cn.qin.entity.Word;
import cn.qin.vo.spellVo.RadicalsVo;
import cn.qin.vo.spellVo.SpellVo;

import java.util.List;

public interface WordDao extends BaseDao<Word> {

    List<SpellVo> findSpellList();

    List<RadicalsVo> findRadicalsList();
}
