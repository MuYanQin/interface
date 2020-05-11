package cn.qin.vo.pomeVo;

import cn.qin.base.vo.BaseVo;
import cn.qin.entity.Pome;
import lombok.Data;

@Data
public class PomeAboutVo extends BaseVo<Pome> {
    private String searchText;
    private String text;
    private String searchType;
    private String itemId;
    private String content;
    private String author;
}
