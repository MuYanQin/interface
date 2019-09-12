package cn.qin.dao;

import cn.qin.base.dao.BaseDao;
import cn.qin.entity.Word;
import cn.qin.vo.spellVo.RadicalsVo;
import cn.qin.vo.spellVo.SpellVo;
import cn.qin.vo.wordVo.WordVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WordDao extends BaseDao<Word> {

    List<SpellVo> findSpellList();

    List<RadicalsVo> findRadicalsList();

    List<WordVo> findWordListByBuShou(@Param("bushou")String bushou);

}
