package cn.qin.entity;

import cn.qin.base.entity.BaseEntity;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class PartJob extends BaseEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "select REPLACE(uuid(),'-','')")
    @Id
    private String partJobId;
    private String companyName;//公司名称
    private String jobName;//工作名称
    private String companyIcon;//公司图标
    private String sType;// 类型号
    private String type;//分类
    private String hot;//是否是推荐 1推荐
}
