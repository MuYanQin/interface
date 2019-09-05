package cn.qin.dao.repository;

import cn.qin.base.dao.BaseDao;
import cn.qin.base.repository.AbstractBaseRepository;
import cn.qin.dao.IdiomDao;
import cn.qin.entity.Idiom;
import cn.qin.vo.IdiomListVo;
import cn.qin.vo.IdiomSearchVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class IdiomRepository extends AbstractBaseRepository<IdiomDao, Idiom> {

    @Autowired
    private IdiomDao idiomDao;
    public List<IdiomSearchVo> findIdiomBySearchText(String searchText){
        return idiomDao.findIdiomBySearchText(searchText);
    }

    public List<IdiomSearchVo> findRandomForSize(int size){
     return  idiomDao.findRandomForSize(size);
    }

    public List<IdiomListVo> findIdiomListByInitial(){
        return idiomDao.findIdiomListByInitial();
    }

    public List<IdiomSearchVo> fildIdiomSearchList(String tag){
        return idiomDao.fildIdiomSearchList(tag);
    }
}
