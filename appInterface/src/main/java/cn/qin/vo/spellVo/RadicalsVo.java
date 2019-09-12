package cn.qin.vo.spellVo;

import cn.qin.base.vo.BaseVo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class RadicalsVo extends BaseVo<SpellVo> {
    private String spellId;
    private String bushou;
    private Integer bihua;
    @JsonIgnore
    private List<RadicalsVo> radicalsVos;
}
