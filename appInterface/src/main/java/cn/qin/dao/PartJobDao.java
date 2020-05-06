package cn.qin.dao;

import cn.qin.base.dao.BaseDao;
import cn.qin.entity.PartJob;
import cn.qin.vo.partJob.PartJobVo;
import cn.qin.vo.partJobDet.PartJobDetVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PartJobDao extends BaseDao<PartJob> {

    List<PartJobVo> selectPartJobVoByPage(@Param("partJobVo") PartJobVo partJobVo);

    List<PartJobVo> selectPartJobVoByRand(@Param("partJobVo") PartJobVo partJobVo);

    List<PartJobVo> findCompanyList();

    List<PartJobVo> findPartJobByCompany(@Param("partJobIds") List<String> partJobIds);
}
