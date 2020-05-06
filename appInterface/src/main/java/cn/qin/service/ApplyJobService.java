package cn.qin.service;

import cn.qin.base.response.RestResponse;
import cn.qin.base.response.RestResponseGenerator;
import cn.qin.dao.repository.ApplyJobRepository;
import cn.qin.entity.ApplyJob;
import cn.qin.util.SqlUtil;
import cn.qin.vo.applyJob.ApplyJobVo;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class ApplyJobService {
    @Autowired
    private ApplyJobRepository applyJobRepository;

    public RestResponse<Map> findApplyJobList(ApplyJobVo applyJobVo){
        Map map = new HashMap();

        PageInfo pageInfo = applyJobRepository.selectListVoByPage("findApplyJobList",applyJobVo,applyJobVo.getPageQuery());
        map.put("list", pageInfo.getList());
        map.put("totalCount", pageInfo.getTotal());
        map.put("totalPage", pageInfo.getPages());
        return RestResponseGenerator.genSuccessResponse(map);
    }

    public RestResponse<String> applyParyJob(ApplyJob applyJob){
        applyJobRepository.insertData(applyJob);
        return RestResponseGenerator.genSuccessResponse();
    }

    public RestResponse<String> deleteParyJob(ApplyJob applyJob){
        Example example = SqlUtil.newExample(ApplyJob.class);
        example.createCriteria().andEqualTo("applyJobId",applyJob.getApplyJobId());
        applyJobRepository.deleteExampleData(example,new ApplyJob());
        return RestResponseGenerator.genSuccessResponse();
    }


}
