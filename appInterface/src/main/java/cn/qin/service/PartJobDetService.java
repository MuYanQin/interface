package cn.qin.service;

import cn.qin.base.response.RestResponse;
import cn.qin.base.response.RestResponseGenerator;
import cn.qin.dao.repository.PartJobDetRepository;
import cn.qin.entity.PartJobDet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PartJobDetService {
    @Autowired
    private PartJobDetRepository partJobDetRepository;
    public RestResponse<PartJobDet> findPartJobDetById(String partJobDetId){
        PartJobDet partJobDet = partJobDetRepository.selectByPrimaryKey(partJobDetId);
        return RestResponseGenerator.genSuccessResponse(partJobDet);
    }
}
