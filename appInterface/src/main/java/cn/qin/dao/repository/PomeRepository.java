package cn.qin.dao.repository;

import cn.qin.base.repository.AbstractBaseRepository;
import cn.qin.dao.PomeDao;
import cn.qin.entity.Pome;
import cn.qin.vo.pomeVo.PomeSearchVo;
import cn.qin.vo.pomeVo.PomeVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PomeRepository extends AbstractBaseRepository<PomeDao, Pome> {
    @Autowired
    private PomeDao pomeDao;

    public PomeVo findPomeDetailById(String pomeId){
        return pomeDao.findPomeDetailById(pomeId);
    }

    public List<PomeSearchVo> findPomeBySearchText(String searchText,String text){
        return  pomeDao.findPomeBySearchText(searchText,text);
    }

    public List<PomeSearchVo> findRandomPomeForSize(@Param("size") int size){
        return pomeDao.findRandomPomeForSize(size);
    }

    public PomeVo findPomeDaily(){
        return pomeDao.findPomeDaily();
    }
}
