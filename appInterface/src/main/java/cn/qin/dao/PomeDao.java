package cn.qin.dao;

import cn.qin.base.dao.BaseDao;
import cn.qin.entity.Pome;
import cn.qin.vo.pomeVo.PomeSearchVo;
import cn.qin.vo.pomeVo.PomeVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PomeDao extends BaseDao<Pome> {

    PomeVo findPomeDetailById(@Param("pomeId")String pomeId);

    List<PomeVo> findPomeListByPage(@Param("pomeVo") PomeVo pomeVo);

    List<PomeSearchVo> findPomeBySearchText(@Param("searchText") String searchText,@Param("text") String text);


    List<PomeSearchVo> findRandomPomeForSize(@Param("size") int size);
}
