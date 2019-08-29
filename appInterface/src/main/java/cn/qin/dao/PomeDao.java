package cn.qin.dao;

import cn.qin.base.dao.BaseDao;
import cn.qin.entity.Pome;
import cn.qin.vo.PomeVo;
import org.springframework.data.repository.query.Param;

public interface PomeDao extends BaseDao<Pome> {

    PomeVo findPomeById(@Param("pomeId")String pomeId);
}
