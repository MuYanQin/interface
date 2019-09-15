package cn.qin.base.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

@Data
public class BaseEntity {
    @JsonIgnore
    private Integer delFlag;
    @JsonIgnore
    private Date createTime;
    @JsonIgnore
    protected Date updateTime;

}
