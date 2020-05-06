package cn.qin.vo.partJob;

import cn.qin.base.vo.BaseVo;
import cn.qin.entity.PartJob;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class PartJobVo extends BaseVo<PartJob> {
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "select REPLACE(uuid(),'-','')")
    @Id
    private String partJobId;
    private String companyName;//公司名称
    private String jobName;//工作名称
    private String companyIcon;//公司图标
    private String sType;// 类型号
    private String type;//分类
    private String hot;//是否是推荐 1推荐

    private String partJobDetId;
    private String workTime;//工作时间段 小时
    private String jobTime;//工作时间段 天
    private String jobAddr;//工作地址
    private String settleType;// 结算类型
    private String salary;//薪资
    private String personNum;//招聘人数

    private String size;


    //兼职信息的id链接 直接返回给后台即可
    private String partJobIdJoins;

    //每个公司几个兼职
    private String total;


}
