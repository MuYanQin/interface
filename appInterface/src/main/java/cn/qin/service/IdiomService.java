package cn.qin.service;

import cn.qin.dao.repository.IdiomRepository;
import cn.qin.entity.Idiom;
import cn.qin.util.SqlUtil;
import cn.qin.util.StringUtils;
import cn.qin.vo.idiomVo.IdiomListVo;
import cn.qin.vo.idiomVo.IdiomSearchVo;
import cn.qin.vo.idiomVo.IdiomVo;
import cn.qin.vo.pomeVo.PomeVo;
import com.github.pagehelper.PageInfo;
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
     * @Title:获取列表 全部获取内容太多 影响速度故默认100条
     * @param idiomVo
     */
    public List<IdiomListVo> findAllIdiomList(IdiomVo idiomVo){
        if (StringUtils.isTrimBlank(idiomVo.getPageSize())){
            idiomVo.setPageSize("100");
        }
        List<IdiomListVo> idiomListVos = idiomRepository.findAllIdiomList(idiomVo);
        return idiomListVos;
    }

    /**
     * @Title:根据Tag获取列表
     * @param idiomVo
     */
    public Map findIdiomListByTag(IdiomVo idiomVo){
        if (StringUtils.isTrimBlank(idiomVo.getTag())){
            throw  new RuntimeException("条件不能为空");
        }
        Map map = new HashMap();
        Example example = SqlUtil.newExample(Idiom.class);
        example.createCriteria().andEqualTo("tag",idiomVo.getTag());
        PageInfo pageInfo = idiomRepository.selectListVoByPage("findIdiomListByTag",idiomVo,idiomVo.getPageQuery());
        map.put("list",pageInfo.getList());
        map.put("totalCount",pageInfo.getTotal());
        map.put("totalPage",pageInfo.getPages());
        return map;
    }
    /**
     * @Title:每日一首
     * @param
     */
    public Idiom findIdiomDaily(){
        return  idiomRepository.findIdiomDaily();
    }
}
