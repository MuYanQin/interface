package cn.qin.service;

import cn.qin.dao.repository.IdiomRepository;
import cn.qin.entity.Idiom;
import cn.qin.util.SqlUtil;
import cn.qin.util.StringUtils;
import cn.qin.vo.IdiomListVo;
import cn.qin.vo.IdiomSearchVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
     * @param initial 首字母 为空 则获取全部(a-z的每条 前100条) 不为空则按initial获取
     */
    public List<IdiomListVo> findIdiomListByInitial(String initial){

        List<IdiomListVo> idiomListVos = new ArrayList<>();
        if (StringUtils.isTrimBlank(initial)){
            idiomListVos = idiomRepository.findIdiomListByInitial();
        }else {
            initial = initial.toLowerCase();
            IdiomListVo idiomListVo = new IdiomListVo();
            idiomListVo.setInitial(initial);
            idiomListVo.setIdioms(idiomRepository.fildIdiomSearchList(initial));
            idiomListVos.add(idiomListVo);
        }
        return idiomListVos;
    }

}
