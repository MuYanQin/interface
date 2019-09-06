package cn.qin.service;

import cn.qin.dao.repository.IdiomRepository;
import cn.qin.entity.Idiom;
import cn.qin.util.SqlUtil;
import cn.qin.util.StringUtils;
import cn.qin.vo.idiomVo.IdiomListVo;
import cn.qin.vo.idiomVo.IdiomSearchVo;
import cn.qin.vo.pomeVo.PomeVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
public class IdiomService {
    @Autowired
    private IdiomRepository idiomRepository;


    /**
     * @Title:搜索成语
     * @param searchText 搜索文字
     */
    public List<IdiomSearchVo> findIdiomBySearchText(String searchText){

        if (StringUtils.isTrimBlank(searchText)){
            throw new RuntimeException("搜索条件不能为空");
        }
        searchText = SqlUtil.likePattern(searchText);
        List<IdiomSearchVo> idiomSearchVos  = idiomRepository.findIdiomBySearchText(searchText);
        return idiomSearchVos;
    }

    /**
     * @Title:随机获取成语
     * @param size 获取条数 默认15条
     */
    public List<IdiomSearchVo> findRandomForSize(String size){
        int siz =15;
        if (StringUtils.isNotTrimBlank(size)){
            siz = Integer.parseInt(size);
        }
        List<IdiomSearchVo> idiomSearchVos  = idiomRepository.findRandomForSize(siz);
        return idiomSearchVos;
    }

    /**
     * @Title:获取详情
     * @param idiomId ID
     */
    public Idiom findDetailById(String idiomId){
        return  idiomRepository.selectByPrimaryKey(idiomId);
    }

    /**
     * @Title:根据首字母获取列表
     * @param size 首字母 获取全部(a-z的每条 前size条  0全查)
     */
    public List<IdiomListVo> findAllIdiomList(String size){

        List<IdiomListVo> idiomListVos = idiomRepository.findAllIdiomList(size);
        return idiomListVos;
    }

    //findIdiomListByInitial

    /**
     * @Title:每日一首
     * @param
     */
    public Idiom findIdiomDaily(){
        return  idiomRepository.findIdiomDaily();
    }
}
