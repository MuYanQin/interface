package cn.qin.dao;

import cn.qin.base.dao.BaseDao;
import cn.qin.entity.ApplyJob;
import cn.qin.entity.PartJobDet;
import cn.qin.vo.applyJob.ApplyJobVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ApplyJobDao extends BaseDao<ApplyJob> {

    List<ApplyJobVo> findApplyJobList(@Param("applyJobVo") ApplyJobVo applyJobVo);
}
