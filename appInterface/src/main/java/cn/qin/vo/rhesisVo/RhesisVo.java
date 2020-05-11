package cn.qin.vo.rhesisVo;

import cn.qin.base.vo.BaseVo;
import cn.qin.entity.Rhesis;
import lombok.Data;

@Data
public class RhesisVo extends BaseVo<Rhesis> {
    private String rhesisId;
    private String quote;
    private String author;
    private String pomeId;
    private String pomeName;

    private String rand;
}
