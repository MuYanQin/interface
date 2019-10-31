package cn.qin.dao.repository;

import cn.qin.base.repository.AbstractBaseRepository;
import cn.qin.dao.IdiomDao;
import cn.qin.entity.Idiom;
import cn.qin.vo.ciYuvo.CiYuVo;
import cn.qin.vo.idiomVo.IdiomListVo;
import cn.qin.vo.idiomVo.IdiomSearchVo;
import cn.qin.vo.idiomVo.IdiomVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class IdiomRepository extends AbstractBaseRepository<IdiomDao, Idiom> {

    @Autowired
    private IdiomDao idiomDao;
    /**
     * @Title:根据ID获取详情
     */
    public IdiomVo findDetailById(String idiomId,String usrId){
     return idiomDao.findDetailById(idiomId,usrId);
    }

    /**
     * @Title:根据文字获取详情
     */
    public IdiomVo findDetailByIdiom(String idiom,String usrId){
        return idiomDao.findDetailByIdiom(idiom,usrId);
    }


    /**
     * @Title:搜索成语
     */
    public List<IdiomSearchVo> findIdiomBySearchText(String searchText){
        return idiomDao.findIdiomBySearchText(searchText);
    }
    /**
     * @Title:随机获取成语
     */
    public List<IdiomSearchVo> findRandomForSize(int size){
     return  idiomDao.findRandomForSize(size);
    }

    /**
     * @Title:获取列表
     * @param idiomVo
     */
    public List<IdiomListVo> findAllIdiomList(IdiomVo idiomVo){
        return idiomDao.findAllIdiomList(new Integer(idiomVo.getPageSize()));
    }

    /**
     * @Title:每日一首
     * @param
     */
    public IdiomVo findIdiomDaily(){
        return idiomDao.findIdiomDaily();
    };

    /**
     * @Title:根据类型获取成语
     * @param
     */
    public List<IdiomSearchVo> selectIdiomByType(IdiomSearchVo idiomSearchVo){
        return idiomDao.selectIdiomByType(idiomSearchVo);
    }

    /**
     * @Title:根据类型获取词语
     * @param
     */
    public List<CiYuVo> selectCiYuByType(CiYuVo ciYuVo){
        return idiomDao.selectCiYuByType(ciYuVo);
    }
}
