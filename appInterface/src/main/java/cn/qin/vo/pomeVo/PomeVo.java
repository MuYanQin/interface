package cn.qin.vo.pomeVo;

import cn.qin.base.vo.BaseVo;
import cn.qin.entity.Pome;
import cn.qin.vo.rhesisVo.RhesisVo;
import lombok.Data;

import java.util.List;

@Data
public class PomeVo extends BaseVo<Pome> {

    private String pomeId;
    private String name;
    private String type;
    private String content;
    private String explanation;
    private String appreciation;
    private String translation;//翻译
    private String authorId;
    private String authorName;

    private String collectionId;

    private List<RhesisVo> rhesisVoList;
}
