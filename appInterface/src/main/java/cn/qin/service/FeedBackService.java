package cn.qin.service;

import cn.qin.base.response.RestResponse;
import cn.qin.base.response.RestResponseGenerator;
import cn.qin.dao.repository.FeedBackRepository;
import cn.qin.entity.User;
import cn.qin.vo.feedBack.FeedBackVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FeedBackService {
    @Autowired
    private FeedBackRepository feedBackRepository;

    public RestResponse commiteIdea(FeedBackVo feedBackVo){
        feedBackRepository.insertData(feedBackVo.getEntity());
        return RestResponseGenerator.genSuccessResponse();
    }
}
