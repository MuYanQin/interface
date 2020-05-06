package cn.qin.vo.partJobDet;

import cn.qin.base.vo.BaseVo;
import cn.qin.entity.PartJobDet;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class PartJobDetVo extends BaseVo<PartJobDet> {

    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "select REPLACE(uuid(),'-','')")
    @Id
    private String partJobDetId;
    private String partJobId;
    private String workTime;//工作时间段 小时
    private String jobTime;//工作时间段 天
    private String jobAddr;//工作地址
    private String settleType;// 结算类型
    private String salary;//薪资
    private String applyEndTime;//申请结束时间
    private String tags;//标签
    private String personNum;//招聘人数
    private String content;//
}
