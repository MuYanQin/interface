package cn.qin.service;

import cn.qin.base.response.RestResponse;
import cn.qin.base.response.RestResponseGenerator;
import cn.qin.constancts.SystemConstants;
import cn.qin.dao.repository.CollectionDataRepository;
import cn.qin.dao.repository.IdiomRepository;
import cn.qin.util.SqlUtil;
import cn.qin.util.StringUtils;
import cn.qin.vo.ciYuvo.CiYuVo;
import cn.qin.vo.idiomVo.IdiomListVo;
import cn.qin.vo.idiomVo.IdiomSearchVo;
import cn.qin.vo.idiomVo.IdiomVo;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@Service
public class IdiomService {
    @Autowired
    private IdiomRepository idiomRepository;

    @Autowired
    private CollectionDataRepository collectionDataRepository;

    /**
     * @Description:搜索成语（匹配成语首字母和成语文字）
     * @param searchText 搜索内容
     */
    public RestResponse<List<IdiomSearchVo>> findIdiomBySearchText(String searchText) {

        if (StringUtils.isTrimBlank(searchText)) {
            return RestResponseGenerator.genFailResponse("搜索条件不能为空!");
        }
        searchText = SqlUtil.likePattern(searchText);
        List<IdiomSearchVo> idiomSearchVos = idiomRepository.findIdiomBySearchText(searchText);

        return RestResponseGenerator.genSuccessResponse(idiomSearchVos);
    }

    /**
     * @param size 获取条数 默认15条
     * @Title:随机获取成语
     */
    public RestResponse<List<IdiomSearchVo>> findRandomForSize(String size) {
        int siz = 15;
        if (StringUtils.isNotTrimBlank(size)) {
            siz = Integer.parseInt(size);
        }
        List<IdiomSearchVo> idiomSearchVos = idiomRepository.findRandomForSize(siz);
        return RestResponseGenerator.genSuccessResponse(idiomSearchVos);
    }

    /**
     * @param idiomId ID
     * @Title:获取详情
     */
    public RestResponse<IdiomVo> findDetailById(String idiomId) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        String userId = request.getHeader(SystemConstants.USERID);
        IdiomVo idiomVo = idiomRepository.findDetailById(idiomId, userId);
        return RestResponseGenerator.genSuccessResponse(idiomVo);
    }

    /**
     * @Title:根据文字获取详情
     */
    public RestResponse<IdiomVo> findDetailByIdiom(String idiom) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        String userId = request.getHeader(SystemConstants.USERID);
        IdiomVo idiomVo = idiomRepository.findDetailByIdiom(idiom, userId);
        if (idiomVo==null){
            return RestResponseGenerator.genFailResponse("暂未收录此成语");
        }
        return RestResponseGenerator.genSuccessResponse(idiomVo);
    }


    /**
     * @Title:获取全部成语列表按照首字母分类
     * @param idiomVo  pageSize判断 全部获取内容太多 影响速度故默认100条
     */
    public RestResponse<List<IdiomListVo>> findAllIdiomList(IdiomVo idiomVo) {
        if (StringUtils.isTrimBlank(idiomVo.getPageSize())) {
            idiomVo.setPageSize("100");
        }
        List<IdiomListVo> idiomListVos = idiomRepository.findAllIdiomList(idiomVo);
        return RestResponseGenerator.genSuccessResponse(idiomListVos);
    }

    /**
     * @param idiomVo
     * @Title:根据Tag获取列表
     */
    public RestResponse<Map> findIdiomListByTag(IdiomVo idiomVo) {
        if (StringUtils.isTrimBlank(idiomVo.getTag())) {
            return RestResponseGenerator.genFailResponse("条件不能为空");
        }
        Map map = new HashMap();
        PageInfo pageInfo = idiomRepository.selectListVoByPage("findIdiomListByTag", idiomVo, idiomVo.getPageQuery());
        map.put("list", pageInfo.getList());
        map.put("totalCount", pageInfo.getTotal());
        map.put("totalPage", pageInfo.getPages());
        return RestResponseGenerator.genSuccessResponse(map);
    }

    /**
     * @param
     * @Title:每日一首
     */
    public RestResponse<IdiomVo> findIdiomDaily() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
        String userId = request.getHeader(SystemConstants.USERID);
        IdiomVo idiomVo = idiomRepository.findIdiomDaily(userId);
        return RestResponseGenerator.genSuccessResponse(idiomVo);
    }

    /**
     * @param
     * @Title:根据类型获取成语 如 AABB。ABBC等
     */
    public RestResponse<Map> selectIdiomByType(IdiomSearchVo idiomSearchVo) {
        if (StringUtils.isTrimBlank(idiomSearchVo.getIdiomType())){
            return RestResponseGenerator.genFailResponse("类型不能为空");
        }
        Map map = new HashMap();

        PageInfo pageInfo = idiomRepository.selectListVoByPage("selectIdiomByType",idiomSearchVo,idiomSearchVo.getPageQuery());
        map.put("list", pageInfo.getList());
        map.put("totalCount", pageInfo.getTotal());
        map.put("totalPage", pageInfo.getPages());
        return RestResponseGenerator.genSuccessResponse(map);
    }

    /**
     * @Title:根据类型获取词语 如ABB AAB
     * @param ciYuVo
     */
    public RestResponse<Map> selectCiYuByType(CiYuVo ciYuVo){
        if (StringUtils.isTrimBlank(ciYuVo.getCiYuType())){
            return RestResponseGenerator.genFailResponse("类型不能为空");
        }
        Map map = new HashMap();
        PageInfo pageInfo = idiomRepository.selectListVoByPage("selectCiYuByType",ciYuVo,ciYuVo.getPageQuery());
        map.put("list", pageInfo.getList());
        map.put("totalCount", pageInfo.getTotal());
        map.put("totalPage", pageInfo.getPages());
        return RestResponseGenerator.genSuccessResponse(map);
    }
}
