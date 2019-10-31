package cn.qin.vo.ciYuvo;

import cn.qin.base.vo.BaseVo;
import cn.qin.entity.CiYu;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class CiYuVo extends BaseVo<CiYu> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "select REPLACE(uuid(),'-','')")
    private String id;
    private String ci;
    private String expl;
    private String spell;


    private String ciYuType;
    @JsonIgnore
    public String getCiYuType() {
        return ciYuType;
    }
    @JsonProperty
    public void setCiYuType(String ciYuType) {
        this.ciYuType = ciYuType;
    }

}
