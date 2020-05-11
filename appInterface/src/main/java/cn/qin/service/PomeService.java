package cn.qin.service;

import cn.qin.base.response.RestResponse;
import cn.qin.base.response.RestResponseGenerator;
import cn.qin.constancts.SystemConstants;
import cn.qin.dao.repository.AuthorRepository;
import cn.qin.dao.repository.PomeRepository;
import cn.qin.dao.repository.RhesisRepository;
import cn.qin.dao.repository.WordRepository;
import cn.qin.entity.Author;
import cn.qin.entity.Rhesis;
import cn.qin.enums.PomeSearchEnums;
import cn.qin.util.SqlUtil;
import cn.qin.util.StringUtils;
import cn.qin.vo.authorVo.AuthorVo;
import cn.qin.vo.pomeVo.PomeAboutVo;
import cn.qin.vo.pomeVo.PomeSearchVo;
import cn.qin.vo.pomeVo.PomeSerachList;
import cn.qin.vo.pomeVo.PomeVo;
import cn.qin.vo.rhesisVo.RhesisVo;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
     * @Description  搜索信息包括作者、诗词、名句--> 每个获取5条
     * @param searchText 搜做的内容 添加通配符
     * @return
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
     * @Description  搜索信息包括作者、诗词、名句 单独获取全部的相关信息
     * @param pomeAboutVo searchType（pome、author、rhesis） 搜索类型 text搜索的内容
     * @return
     */
    public RestResponse<Map> findInfoAboutSearchText(PomeAboutVo pomeAboutVo){

        Map map = new HashMap();
        PageInfo pageInfo = null;

        pomeAboutVo.setSearchText(SqlUtil.likePattern(pomeAboutVo.getText()));

        if (PomeSearchEnums.AUTHOR.getType().equals(pomeAboutVo.getSearchType())){
            pageInfo = pomeRepository.selectListVoByPage("searchabountAuthor", pomeAboutVo, pomeAboutVo.getPageQuery());
        }else  if (PomeSearchEnums.POME.getType().equals(pomeAboutVo.getSearchType())){
            pageInfo = pomeRepository.selectListVoByPage("searchabountPome", pomeAboutVo, pomeAboutVo.getPageQuery());
        }else {
            pageInfo = pomeRepository.selectListVoByPage("searchabountrhesis", pomeAboutVo, pomeAboutVo.getPageQuery());
        }

        map.put("list",pageInfo.getList());
        map.put("totalCount",pageInfo.getTotal());
        map.put("totalPage",pageInfo.getPages());
        return RestResponseGenerator.genSuccessResponse(map);
    }


    /**
     * @Title:根据pomeid获取古诗详情
     * @Description:
     */
    public RestResponse<PomeVo> findPomeDetailById(String pomeId) {
        PomeVo pomeVo =  pomeRepository.findPomeDetailById(pomeId);
        return RestResponseGenerator.genSuccessResponse(pomeVo);
    }


    /**
     * @Description:获取某个作者的诗文列表
     * @param pomeVo authorId或者authorName
     */
    public RestResponse<Map> findPomeListPageByAuthor(PomeVo pomeVo) {
        if (StringUtils.isNotTrimBlank(pomeVo.getAuthorName())) {
            pomeVo.setAuthorName(SqlUtil.likePattern(pomeVo.getAuthorName()));
        } else {
            pomeVo.setAuthorName(null);
        }

        if (StringUtils.isNotTrimBlank(pomeVo.getAuthorId())) {
            pomeVo.setAuthorId(pomeVo.getAuthorId());
        } else {
            pomeVo.setAuthorId(null);
        }

        Map map = new HashMap();
        PageInfo pageInfo = pomeRepository.selectListVoByPage("findPomeListPageByAuthor", pomeVo, pomeVo.getPageQuery());

        map.put("list",pageInfo.getList());
        map.put("totalCount",pageInfo.getTotal());
        map.put("totalPage",pageInfo.getPages());
        return RestResponseGenerator.genSuccessResponse(map);
    }

    /**
     * @Title:按类型随机获取一定条数诗文
     * @param size 条数 type类型
     */
    public RestResponse<List<PomeSearchVo>>  findRandomPomeForSize(String size,String type) {
        int siz = 15;
        if (StringUtils.isNotTrimBlank(size)) {
            siz = Integer.parseInt(size);
        }
        List<PomeSearchVo> pomeSearchVos = pomeRepository.findRandomPomeForSize(siz,type);
        return RestResponseGenerator.genSuccessResponse(pomeSearchVos);
    }

    /**
     * @Title:分页获取诗文列表
     * @param
     */
    public RestResponse<Map> findPomeListByPage(PomeVo pomeVo) {
        if (StringUtils.isNotTrimBlank(pomeVo.getType())) {
            pomeVo.setType(pomeVo.getType());
        } else {
            pomeVo.setType(null);
        }

        Map map = new HashMap();
        PageInfo pageInfo = pomeRepository.selectListVoByPage("findPomeListByPage", pomeVo, pomeVo.getPageQuery());

        map.put("list",pageInfo.getList());
        map.put("totalCount",pageInfo.getTotal());
        map.put("totalPage",pageInfo.getPages());
        return RestResponseGenerator.genSuccessResponse(map);
    }

    /**
     * @Title:分页获取名句列表
     * @param rhesisVo rand 空 分页获取 。 不为空  则不分页随机获取15条
     */
    public RestResponse<Map> findRhesisListByPage(RhesisVo rhesisVo) {

        Map map = new HashMap();
        PageInfo pageInfo = new PageInfo();
        if (StringUtils.isTrimBlank(rhesisVo.getRand())){
            pageInfo = pomeRepository.selectListVoByPage("findRhesisListByPage", rhesisVo, rhesisVo.getPageQuery());
        }else {
            pageInfo = pomeRepository.selectListVoByPage("findRhesisListByPageRand", rhesisVo, rhesisVo.getPageQuery());
        }


        map.put("list",pageInfo.getList());
        map.put("totalCount",pageInfo.getTotal());
        map.put("totalPage",pageInfo.getPages());
        return RestResponseGenerator.genSuccessResponse(map);
    }


    /**
     * @Title:每日一首唐诗词
     * @param
     */
    public RestResponse<PomeVo>  findPomeDaily() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
        String userId = request.getHeader(SystemConstants.USERID);
        PomeVo pomeVo = pomeRepository.findPomeDaily(userId);
        return RestResponseGenerator.genSuccessResponse(pomeVo) ;
    }

    /**
     * @Title:获取有作品的诗人列表
     * @param
     */
    public RestResponse<List<AuthorVo>> selectAuthorHasPome() {
        List<AuthorVo> authorVos = pomeRepository.selectAuthorHasPome();
        return RestResponseGenerator.genSuccessResponse(authorVos);
    }

    /**
     * @Title:获取有作品的诗人根据姓名首字母分组
     * @param
     */
    public RestResponse<Map> findAuthorBySort(){
        List<AuthorVo> authorVos = authorRepository.findAuthorBySort();
        Map<String, List<AuthorVo>> map = authorVos.stream()
                .collect(Collectors.groupingBy(AuthorVo::getTag));
        return RestResponseGenerator.genSuccessResponse(map);
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
