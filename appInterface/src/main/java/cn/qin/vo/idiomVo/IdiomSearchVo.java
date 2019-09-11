package cn.qin.vo.idiomVo;

import cn.qin.base.vo.BaseVo;
import cn.qin.entity.Idiom;
import cn.qin.entity.Pome;
import lombok.Data;

@Data
public class IdiomSearchVo extends BaseVo<Idiom> {
    private  String idiomId;
    private  String idiom;
    private  String spell;
}
