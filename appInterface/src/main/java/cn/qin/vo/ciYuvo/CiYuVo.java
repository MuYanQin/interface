package cn.qin.vo.ciYuvo;

import cn.qin.base.vo.BaseVo;
import cn.qin.entity.CiYu;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Data
public class CiYuVo extends BaseVo<CiYu> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "select REPLACE(uuid(),'-','')")
    private String id;
    private String ci;
    private String expl;
    private String spell;
    private List<CiYuVo> ciYuVoList;
}
