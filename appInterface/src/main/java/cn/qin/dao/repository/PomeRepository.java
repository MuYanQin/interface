package cn.qin.dao.repository;

import cn.qin.base.repository.AbstractBaseRepository;
import cn.qin.dao.PomeDao;
import cn.qin.vo.PomeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PomeRepository extends AbstractBaseRepository {
    @Autowired
    private PomeDao pomeDao;

    public PomeVo findPomeById(String pomeId){
        return pomeDao.findPomeById(pomeId);
    }

}
