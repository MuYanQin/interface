package cn.qin.dao.repository;

import cn.qin.base.repository.AbstractBaseRepository;
import cn.qin.dao.ApplyJobDao;
import cn.qin.dao.PartJobDetDao;
import cn.qin.entity.ApplyJob;
import cn.qin.entity.PartJobDet;
import org.springframework.stereotype.Repository;

@Repository
public class ApplyJobRepository extends AbstractBaseRepository<ApplyJobDao, ApplyJob> {
}
