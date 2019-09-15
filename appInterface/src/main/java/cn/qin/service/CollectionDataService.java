package cn.qin.service;

import cn.qin.dao.repository.CollectionDataRepository;
import cn.qin.entity.CollectionData;
import cn.qin.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Service
public class CollectionDataService {

    @Autowired
    private CollectionDataRepository collectionDataRepository;
    /**
     * @Title:添加收藏
     * @param
     */
    public void saveCollectionById(CollectionData collectionData){
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
        //TODO： 验证header信息
        String userId = request.getHeader("userId");
        if (StringUtils.isTrimBlank(userId)){
            throw new RuntimeException("参数有误！");
        }
        collectionData.setUserId(userId);
        collectionDataRepository.insertData(collectionData);
    }
}
