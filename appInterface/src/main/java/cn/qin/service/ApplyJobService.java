package cn.qin.service;

import cn.qin.base.response.RestResponse;
import cn.qin.base.response.RestResponseGenerator;
import cn.qin.constancts.SystemConstants;
import cn.qin.dao.repository.ApplyJobRepository;
import cn.qin.entity.ApplyJob;
import cn.qin.util.SqlUtil;
import cn.qin.vo.applyJob.ApplyJobVo;
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
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
        String userId = request.getHeader(SystemConstants.USERID);
        Example example = SqlUtil.newExample(ApplyJob.class);
        example.createCriteria().andEqualTo("userId",userId).andEqualTo("partJobDetId",applyJob.getPartJobDetId());
        ApplyJob newApply = applyJobRepository.selectOneByExample(example);
        if (newApply != null){
            applyJobRepository.updateByPrimaryKeyData(newApply);
            return RestResponseGenerator.genSuccessResponse();
        }

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
