package cn.qin.service;

import cn.qin.dao.repository.CollectionDataRepository;
import cn.qin.entity.CollectionData;
import cn.qin.enums.CollectionDataEnums;
import cn.qin.util.StringUtils;
import cn.qin.vo.collectionData.CollectionDataVo;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class CollectionDataService {

    @Autowired
    private CollectionDataRepository collectionDataRepository;
    /**
     * @Title:添加收藏
     * @param
     */
    public void saveCollection(CollectionData collectionData){
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
        String userId = request.getHeader("userId");
        if (StringUtils.isTrimBlank(collectionData.getObjectId())){
            throw new RuntimeException("参数有误！");
        }
        collectionData.setUserId(userId);
        collectionDataRepository.insertData(collectionData);
    }
    /**
     * @Title:获取收藏
     * @param
     */
    public Map findCollectionDataList(CollectionDataVo collectionDataVo){
        if (StringUtils.isTrimBlank(collectionDataVo.getType())){
            throw new RuntimeException("参数有误！");
        }
        Map map = new HashMap();

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
        String userId = request.getHeader("userId");
        collectionDataVo.setUserId(userId);
        String mothedStr = "";
        if (CollectionDataEnums.WORD.getFlag().equals(collectionDataVo.getType())){
            mothedStr = "seletWordList";
        }else if (CollectionDataEnums.IDIOM.getFlag().equals(collectionDataVo.getType())){
            mothedStr = "seletIdiomList";

        }else if (CollectionDataEnums.REHSIS.getFlag().equals(collectionDataVo.getType())){
            mothedStr = "seletRhesisList";

        }else if (CollectionDataEnums.POME.getFlag().equals(collectionDataVo.getType())){
            mothedStr = "seletPomeList";
        }

        PageInfo pageInfo = collectionDataRepository.selectListVoByPage(mothedStr,collectionDataVo,collectionDataVo.getPageQuery());

        map.put("list",pageInfo.getList());
        map.put("totalCount",pageInfo.getTotal());
        map.put("totalPage",pageInfo.getPages());
        return  map;
    }

    /**
     * @Title:取消收藏
     * @param
     */
    public void deleteCollectionData(String collectionId){
        CollectionData collectionData = new CollectionData();
        collectionData.setCollectionId(collectionId);
        collectionDataRepository.deleteData(collectionData);
    }

}
