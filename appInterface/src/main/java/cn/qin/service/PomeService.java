package cn.qin.service;

import cn.qin.dao.repository.AuthorRepository;
import cn.qin.dao.repository.PomeRepository;
import cn.qin.entity.Author;
import cn.qin.entity.Pome;
import cn.qin.util.HttpClientUtil;
import cn.qin.util.SqlUtil;
import cn.qin.util.StringUtils;
import cn.qin.util.UUIDUtils;
import cn.qin.vo.pomeVo.PomeVo;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.jdbc.SQL;
import org.hibernate.annotations.SQLUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Slf4j
@Service
public class PomeService {
    @Autowired
    private PomeRepository pomeRepository;

    @Autowired
    private AuthorRepository authorRepository;


    /**
     * @Title:获取诗词的详情
     * @param pomeId 诗词的ID
     */
    public PomeVo findPomeDetailById(String pomeId){
        return  pomeRepository.findPomeDetailById(pomeId);
    }
    public List<Pome> findPomeListByPage(PomeVo pomeVo){
        if (StringUtils.isNotTrimBlank(pomeVo.getAuthorName())){
            pomeVo.setAuthorName(SqlUtil.likePattern(pomeVo.getAuthorName()));
        }else {
            pomeVo.setAuthorName(null);
        }
        PageInfo pageInfo = pomeRepository.selectListVoByPage("findPomeListByPage",pomeVo,pomeVo.getPageIndex());
        return pageInfo.getList();
    }
    private  String findText(String text){
        text = text.replaceAll("</p>","\n");
        text = text.replaceAll("<br />","\n");
        text = text.replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll(
                "<[^>]*>", "");
        text = text.replaceAll("[(/>)<]", "").trim();

        return  text;
    }
}
