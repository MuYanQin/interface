package cn.qin.dao;

import cn.qin.base.dao.BaseDao;
import cn.qin.entity.Word;
import cn.qin.vo.ciYuvo.CiYuVo;
import cn.qin.vo.idiomVo.IdiomVo;
import cn.qin.vo.spellVo.RadicalsVo;
import cn.qin.vo.spellVo.SpellVo;
import cn.qin.vo.wordVo.WordInfoVo;
import cn.qin.vo.wordVo.WordVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WordDao extends BaseDao<Word> {

    List<SpellVo> findSpellList();

    List<RadicalsVo> findRadicalsList();

    List<WordVo> findWordListByBuShou(@Param("bushou")String bushou);

    List<WordVo> findWordListBySpell(@Param("spell")String spell);

    List<WordVo> findWordListByWord(@Param("word")String word);

    List<CiYuVo> selectciyiByWord(@Param("word")String word ,@Param("pinyin")String pinyin);

    List<IdiomVo> selectIdiomByWord(@Param("word")String word,@Param("pinyin")String pinyin);

    List<WordInfoVo> findWordInfoByWord(@Param("word")String word, @Param("userId")String userId);
}
