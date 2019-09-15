package cn.qin.vo.pomeVo;

import cn.qin.base.vo.BaseVo;
import cn.qin.entity.Pome;
import lombok.Data;

import java.util.List;

@Data
public class PomeSerachList  extends BaseVo<Pome> {
    private List<PomeSearchVo> authorList;
    private List<PomeSearchVo> pomeList;
}
