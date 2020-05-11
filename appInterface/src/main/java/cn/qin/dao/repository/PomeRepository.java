package cn.qin.dao.repository;

import cn.qin.base.repository.AbstractBaseRepository;
import cn.qin.dao.PomeDao;
import cn.qin.entity.Pome;
import cn.qin.vo.authorVo.AuthorVo;
import cn.qin.vo.pomeVo.PomeSearchVo;
import cn.qin.vo.pomeVo.PomeSerachList;
import cn.qin.vo.pomeVo.PomeVo;
import cn.qin.vo.rhesisVo.RhesisVo;
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

    /**
     * @DESC  搜索信息包括作者、诗词、名句
     * @param searchText 搜做的内容 添加通配符
     * @param text 搜索的内容
     * @return
     */
    public PomeSerachList findPomeBySearchText(String searchText, String text){
        return  pomeDao.findPomeBySearchText(searchText,text);
    }

    public List<PomeSearchVo> findRandomPomeForSize(int size,String type){
        return pomeDao.findRandomPomeForSize(size,type);
    }

    public List<RhesisVo> findRhesisListByPage(RhesisVo rhesisVo){
        return pomeDao.findRhesisListByPage(rhesisVo);
    }

    public List<RhesisVo> findRhesisListByPageRand( RhesisVo rhesisVo){
        return pomeDao.findRhesisListByPageRand(rhesisVo);
    }

    public PomeVo findPomeDaily(String userId){
        return pomeDao.findPomeDaily(userId);
    }

    public List<AuthorVo> selectAuthorHasPome(){
        return  pomeDao.selectAuthorHasPome();
    }
}
