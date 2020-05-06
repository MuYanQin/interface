package cn.qin.controller;

import cn.qin.base.response.RestResponse;
import cn.qin.service.PartJobService;
import cn.qin.vo.partJob.PartJobVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("partJob")
public class PartJobController {
    @Autowired
    private PartJobService partJobService;

    /**
     * @Title:获取兼职列表
     * @Description: 分页查询获取兼职列表
     * @param partJobVo 传递pageIndex \pageSize 即可
     * @return
     * @throws
     * @author qinmuqiao
     * @date 2020/5/1
     */
    @RequestMapping(value = "findPartJobList" ,method = RequestMethod.GET)
    public ResponseEntity<RestResponse> findPartJobList(PartJobVo partJobVo){
        return new ResponseEntity<RestResponse>(partJobService.findPartJobList(partJobVo), HttpStatus.OK);
    }

    /**
     * @Title:随机获取兼职列表
     * @Description: size 必传 表示随机几个出来
     * @param partJobVo 传递size 即可
     * @return
     * @throws
     * @author qinmuqiao
     * @date 2020/5/1
     */
    @RequestMapping(value = "findPartJobByRandSize" ,method = RequestMethod.GET)
    public ResponseEntity<RestResponse> findPartJobByRandSize(PartJobVo partJobVo){
        return new ResponseEntity<RestResponse>(partJobService.findPartJobByRandSize(partJobVo), HttpStatus.OK);
    }

    /**
     * @Title:获取企业列表
     * @Description:
     * @param
     * @return
     * @throws
     * @author qinmuqiao
     * @date 2020/5/1
     */
    @RequestMapping(value = "findCompanyList" ,method = RequestMethod.GET)
    public ResponseEntity<RestResponse> findCompanyList(){
        return new ResponseEntity<RestResponse>(partJobService.findCompanyList(), HttpStatus.OK);
    }

    /**
     * @Title:获取企业下面的兼职列表
     * @Description: partJobIdJoins 即可
     * @param
     * @return
     * @throws
     * @author qinmuqiao
     * @date 2020/5/1
     */
    @RequestMapping(value = "findPartJobByCompany" ,method = RequestMethod.GET)
    public ResponseEntity<RestResponse> findPartJobByCompany(String partJobIdJoins){
        return new ResponseEntity<RestResponse>(partJobService.findPartJobByCompany(partJobIdJoins), HttpStatus.OK);
    }
}
