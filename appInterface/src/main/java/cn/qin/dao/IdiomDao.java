package cn.qin.dao;

import cn.qin.base.dao.BaseDao;
import cn.qin.entity.Idiom;
import cn.qin.vo.ciYuvo.CiYuVo;
import cn.qin.vo.idiomVo.IdiomListVo;
import cn.qin.vo.idiomVo.IdiomSearchVo;
import cn.qin.vo.idiomVo.IdiomVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IdiomDao extends BaseDao<Idiom> {

    IdiomVo findDetailById(@Param("idiomId") String idiomId,@Param("usrId") String usrId);

    IdiomVo findDetailByIdiom(@Param("idiom") String idiom,@Param("usrId") String usrId);


    List<IdiomSearchVo> findIdiomBySearchText(@Param("searchText") String searchText);

    List<IdiomSearchVo> findRandomForSize(@Param("size") int size);

    List<IdiomListVo> findAllIdiomList(@Param("pageSize") int pageSize);

    List<IdiomSearchVo> findIdiomListByTag(@Param("idiomVo") IdiomVo idiomVo);

    IdiomVo findIdiomDaily(@Param("userId") String userId);

    List<IdiomSearchVo> selectIdiomByType(@Param("idiomSearchVo") IdiomSearchVo idiomSearchVo);

    List<CiYuVo> selectCiYuByType(@Param("ciYuVo") CiYuVo ciYuVo);

}
