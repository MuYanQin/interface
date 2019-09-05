package cn.qin.service;

import cn.qin.dao.repository.IdiomRepository;
import cn.qin.util.SqlUtil;
import cn.qin.util.StringUtils;
import cn.qin.vo.IdiomSearchVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class IdiomService {
    @Autowired
    private IdiomRepository idiomRepository;

    //搜索成语
    public List<IdiomSearchVo> findIdiomBySearchText(String searchText){

        if (StringUtils.isTrimBlank(searchText)){
            throw new RuntimeException("搜索条件不能为空");
        }
        searchText = SqlUtil.likePattern(searchText);
        List<IdiomSearchVo> idiomSearchVos  = idiomRepository.findIdiomBySearchText(searchText);
        return idiomSearchVos;
    }
}
