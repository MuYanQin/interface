package cn.qin.service;

import cn.qin.base.response.RestResponse;
import cn.qin.base.response.RestResponseGenerator;
import cn.qin.dao.repository.PartJobRepository;
import cn.qin.entity.PartJob;
import cn.qin.util.StringUtils;
import cn.qin.vo.partJob.PartJobVo;
import cn.qin.vo.partJobDet.PartJobDetVo;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class PartJobService {
    @Autowired
    private PartJobRepository partJobRepository;
    public RestResponse<Map> findPartJobList(PartJobVo partJobVo){
        Map map = new HashMap();

        PageInfo<List<PartJobVo>> pageInfo = partJobRepository.selectListVoByPage("selectPartJobVoByPage",partJobVo,partJobVo.getPageQuery());
        map.put("list", pageInfo.getList());
        map.put("totalCount", pageInfo.getTotal());
        map.put("totalPage", pageInfo.getPages());
        return RestResponseGenerator.genSuccessResponse(map);
    }

    public RestResponse<List<PartJobVo>> findPartJobByRandSize(PartJobVo partJobVo){
        Map map = new HashMap();

        List<PartJobVo>  partJobVoList = partJobRepository.selectPartJobVoByRand(partJobVo);
        return RestResponseGenerator.genSuccessResponse(partJobVoList);
    }

    public RestResponse<List<PartJobVo>> findCompanyList(){
        return RestResponseGenerator.genSuccessResponse(partJobRepository.findCompanyList());
    }

    public  RestResponse<List<PartJobVo>> findPartJobByCompany(String partJobIdJoins){
        if (StringUtils.isTrimBlank(partJobIdJoins)){
            return RestResponseGenerator.genFailResponse("参数不能为空");
        }
        String[] ids = partJobIdJoins.split(",");
        List<String> list = Arrays.asList(ids);  //此集合无法操作添加元素
        List<String> list1 = new ArrayList<String>(list);
        List<PartJobVo> partJobDetVos =  partJobRepository.findPartJobByCompany(list1);
        return RestResponseGenerator.genSuccessResponse(partJobDetVos);

    }

}
