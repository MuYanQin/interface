package cn.qin.vo;

import cn.qin.base.vo.BaseVo;
import cn.qin.entity.Pome;
import lombok.Data;

@Data
public class PomeVo extends BaseVo<Pome> {

    private String pomeId;
    private String name;
    private String type;
    private String detailId;
    private String content;
    private String explanation;
    private String appreciation;
    private String authorId;
    private String authorName;
}
