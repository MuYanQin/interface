package cn.qin.entity;

import cn.qin.base.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class User  extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "select REPLACE(uuid(),'-','')")
    private String userId;
    private String account;//账号
    private String name;//姓名
    private String phone;//手机号
    private String nickName;//昵称
    private String pwd;//密码
    private String bundleId;//包名
    private String email;//邮箱
    private String address;//地址
    private String birthDay;//生日
    private String gender;//性别
    private String education;//学历
    private String salaryRang;//薪资范围
    private String major;//专业



    @JsonIgnore
    public String getPwd() {
        return pwd;
    }
    @JsonProperty
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
