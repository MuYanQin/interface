package cn.qin.base.entity;

import cn.qin.util.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

@Data
public class BaseEntity {
    @JsonIgnore
    private Integer delFlag;
    //@JsonIgnore
    //,timezone = "Asia/Shanghai"
    @JsonFormat(pattern = DateUtils.formatStr_yyyyMMddHHmmss)
    private Date createTime;
    @JsonIgnore
    protected Date updateTime;

}
