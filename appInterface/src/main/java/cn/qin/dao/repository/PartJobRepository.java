package cn.qin.dao.repository;

import cn.qin.base.repository.AbstractBaseRepository;
import cn.qin.dao.PartJobDao;
import cn.qin.entity.PartJob;
import cn.qin.vo.partJob.PartJobVo;
import cn.qin.vo.partJobDet.PartJobDetVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PartJobRepository extends AbstractBaseRepository<PartJobDao, PartJob> {

    @Autowired
    private  PartJobDao partJobDao;

    public List<PartJobVo> selectPartJobVoByPage(PartJobVo partJobVo){
        return  partJobDao.selectPartJobVoByPage(partJobVo);
    }

    public List<PartJobVo> selectPartJobVoByRand(PartJobVo partJobVo){
        return  partJobDao.selectPartJobVoByRand(partJobVo);
    }

    public List<PartJobVo> findCompanyList(){
        return  partJobDao.findCompanyList();
    }

    public List<PartJobVo> findPartJobByCompany(List<String> partJobIds){
        return  partJobDao.findPartJobByCompany(partJobIds);
    }

}
