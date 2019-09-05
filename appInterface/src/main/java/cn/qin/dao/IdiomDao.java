package cn.qin.dao;

import cn.qin.base.dao.BaseDao;
import cn.qin.entity.Idiom;
import cn.qin.vo.IdiomListVo;
import cn.qin.vo.IdiomSearchVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IdiomDao extends BaseDao<Idiom> {

    List<IdiomSearchVo> findIdiomBySearchText(@Param("searchText") String searchText);

    List<IdiomSearchVo> findRandomForSize(@Param("size") int size);

    List<IdiomListVo> findIdiomListByInitial();

    List<IdiomSearchVo> fildIdiomSearchList(@Param("tag") String tag);

}
