package cn.qin.dao;

import cn.qin.base.dao.BaseDao;
import cn.qin.entity.Idiom;
import cn.qin.vo.idiomVo.IdiomListVo;
import cn.qin.vo.idiomVo.IdiomSearchVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IdiomDao extends BaseDao<Idiom> {

    List<IdiomSearchVo> findIdiomBySearchText(@Param("searchText") String searchText);

    List<IdiomSearchVo> findRandomForSize(@Param("size") int size);

    List<IdiomListVo> findAllIdiomList(@Param("size") String size);

    List<IdiomSearchVo> fildIdiomSearchList(@Param("tag") String tag);
    Idiom findIdiomDaily();
}
