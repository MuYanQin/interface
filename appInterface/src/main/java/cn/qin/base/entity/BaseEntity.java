package cn.qin.base.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

@Data
public class BaseEntity {
    @JsonIgnore
    private String delFlag;
    @JsonIgnore
    private Date createDate;
    @JsonIgnore
    protected Date updateTime;

}
