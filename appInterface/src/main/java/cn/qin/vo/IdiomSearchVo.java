package cn.qin.vo;

import cn.qin.base.vo.BaseVo;
import cn.qin.entity.Pome;
import lombok.Data;

@Data
public class IdiomSearchVo extends BaseVo<Pome> {
    private  String idiomId;
    private  String idiom;
    private  String spell;
}
