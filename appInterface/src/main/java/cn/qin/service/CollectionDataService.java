package cn.qin.service;

import cn.qin.dao.repository.CollectionDataRepository;
import cn.qin.entity.CollectionData;
import cn.qin.util.SqlUtil;
import cn.qin.util.StringUtils;
import cn.qin.vo.collectionData.CollectionDataVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
        if (StringUtils.isTrimBlank(userId)){
            throw new RuntimeException("参数有误！");
        }
        collectionData.setUserId(userId);
        collectionDataRepository.insertData(collectionData);
    }
    /**
     * @Title:获取收藏
     * @param
     */
    public List<CollectionData> findCollectionDataList(String type){
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
        String userId = request.getHeader("userId");
        Example example = SqlUtil.newExample(CollectionData.class);
        example.createCriteria().andEqualTo("userId",userId).andEqualTo("type",type);
        return  collectionDataRepository.selectListByExample(example);
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
