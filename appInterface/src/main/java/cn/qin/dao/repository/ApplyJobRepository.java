package cn.qin.dao.repository;

import cn.qin.base.repository.AbstractBaseRepository;
import cn.qin.dao.ApplyJobDao;
import cn.qin.dao.PartJobDetDao;
import cn.qin.entity.ApplyJob;
import cn.qin.entity.PartJobDet;
import cn.qin.vo.applyJob.ApplyJobVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ApplyJobRepository extends AbstractBaseRepository<ApplyJobDao, ApplyJob> {

    @Autowired
    private ApplyJobDao applyJobDao;

    List<ApplyJobVo> findApplyJobList(ApplyJobVo applyJobVo){
        return applyJobDao.findApplyJobList(applyJobVo);
    }
}
