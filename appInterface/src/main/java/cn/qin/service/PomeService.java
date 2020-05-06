package cn.qin.service;

import cn.qin.base.response.RestResponse;
import cn.qin.base.response.RestResponseGenerator;
import cn.qin.dao.repository.AuthorRepository;
import cn.qin.dao.repository.PomeRepository;
import cn.qin.dao.repository.RhesisRepository;
import cn.qin.entity.Author;
import cn.qin.entity.CiYu;
import cn.qin.entity.Pome;
import cn.qin.entity.Rhesis;
import cn.qin.util.ArrayUtils;
import cn.qin.util.HttpClientUtil;
import cn.qin.util.SqlUtil;
import cn.qin.util.StringUtils;
import cn.qin.vo.authorVo.AuthorVo;
import cn.qin.vo.pomeVo.PomeSearchVo;
import cn.qin.vo.pomeVo.PomeSerachList;
import cn.qin.vo.pomeVo.PomeVo;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class PomeService {
    @Autowired
    private PomeRepository pomeRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private RhesisRepository rhesisRepository;


    /**
     * @param searchText 参数
     * @Title:搜索诗词
     */
    public RestResponse<PomeSerachList>  findPomeBySearchText(String searchText) {
        if (StringUtils.isTrimBlank(searchText)) {
            return RestResponseGenerator.genFailResponse("搜索内容不能为空");
        }
        String text = searchText;
        searchText = SqlUtil.likePattern(searchText);
        PomeSerachList pomeSerachList =  pomeRepository.findPomeBySearchText(searchText, text);

        return RestResponseGenerator.genSuccessResponse(pomeSerachList);
    }

    /**
     * @param pomeId 诗词的ID
     * @Title:获取诗词的详情
     */
    public RestResponse<PomeVo>  findPomeDetailById(String pomeId) {
        PomeVo pomeVo =  pomeRepository.findPomeDetailById(pomeId);
        return RestResponseGenerator.genSuccessResponse(pomeVo);
    }

    /**
     * @Title:分页获取诗词
     * @param pomeVo 参数 authorName 作者
     */
    public RestResponse<Map> findPomeListByPage(PomeVo pomeVo) {
        if (StringUtils.isNotTrimBlank(pomeVo.getAuthorName())) {
            pomeVo.setAuthorName(SqlUtil.likePattern(pomeVo.getAuthorName()));
        } else {
            pomeVo.setAuthorName(null);
        }
        Map map = new HashMap();
        PageInfo pageInfo = pomeRepository.selectListVoByPage("findPomeListByPage", pomeVo, pomeVo.getPageQuery());

        map.put("list",pageInfo.getList());
        map.put("totalCount",pageInfo.getTotal());
        map.put("totalPage",pageInfo.getPages());
        return RestResponseGenerator.genSuccessResponse(map);
    }

    /**
     * @param size 获取条数 默认15条
     * @Title:随机获取诗文
     */
    public RestResponse<List<PomeSearchVo>>  findRandomPomeForSize(String size) {
        int siz = 15;
        if (StringUtils.isNotTrimBlank(size)) {
            siz = Integer.parseInt(size);
        }
        List<PomeSearchVo> pomeSearchVos = pomeRepository.findRandomPomeForSize(siz);
        return RestResponseGenerator.genSuccessResponse(pomeSearchVos);
    }
    /**
     * @Title:每日一首
     * @param
     */
    public RestResponse<PomeVo>  findPomeDaily() {
        PomeVo pomeVo = pomeRepository.findPomeDaily();
        return RestResponseGenerator.genSuccessResponse(pomeVo) ;
    }

    /**
     * @Title:获取有作品的诗人
     * @param
     */
    public RestResponse<List<AuthorVo>> selectAuthorHasPome() {
        List<AuthorVo> authorVos = pomeRepository.selectAuthorHasPome();
        return RestResponseGenerator.genSuccessResponse(authorVos);
    }

    /**
     * @Title:获取诗人介绍
     * @param
     */
    public RestResponse<Author>  findAuthorById(String authorId){
        Author author = authorRepository.selectByPrimaryKey(authorId);
        return RestResponseGenerator.genSuccessResponse(author);
    }
}
