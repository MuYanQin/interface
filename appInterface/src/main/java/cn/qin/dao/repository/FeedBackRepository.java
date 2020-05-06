package cn.qin.dao.repository;

import cn.qin.base.repository.AbstractBaseRepository;
import cn.qin.dao.ApplyJobDao;
import cn.qin.dao.FeedBackDao;
import cn.qin.entity.ApplyJob;
import cn.qin.entity.FeedBack;
import cn.qin.vo.applyJob.ApplyJobVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FeedBackRepository extends AbstractBaseRepository<FeedBackDao, FeedBack> {


}
