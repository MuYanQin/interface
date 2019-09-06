package cn.qin.dao.repository;

import cn.qin.base.repository.AbstractBaseRepository;
import cn.qin.dao.PomeDao;
import cn.qin.entity.Pome;
import cn.qin.vo.pomeVo.PomeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PomeRepository extends AbstractBaseRepository<PomeDao, Pome> {
    @Autowired
    private PomeDao pomeDao;

    public PomeVo findPomeDetailById(String pomeId){
        return pomeDao.findPomeDetailById(pomeId);
    }

}
