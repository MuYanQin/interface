package cn.qin.vo.spellVo;

import cn.qin.base.vo.BaseVo;
import cn.qin.entity.Spell;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class SpellVo extends BaseVo<Spell> {
    private String spellId;
    private String pinyin;
    private String pinyinKey;
    @JsonIgnore
    private List<SpellVo> spellVos;
}
