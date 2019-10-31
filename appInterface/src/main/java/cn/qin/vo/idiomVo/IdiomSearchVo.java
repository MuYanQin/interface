package cn.qin.vo.idiomVo;

import cn.qin.base.vo.BaseVo;
import cn.qin.entity.Idiom;
import cn.qin.entity.Pome;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class IdiomSearchVo extends BaseVo<Idiom> {
    private  String idiomId;
    private  String idiom;
    private  String spell;

    private  String idiomType;
    @JsonIgnore
    public String getIdiomType() {
        return idiomType;
    }
    @JsonProperty
    public void setIdiomType(String idiomType) {
        this.idiomType = idiomType;
    }

}
