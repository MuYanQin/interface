package cn.qin.vo.pomeVo;

import cn.qin.base.vo.BaseVo;
import cn.qin.entity.Pome;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PomeVo extends BaseVo<Pome> {

    private String pomeId;
    private String name;
    private String type;
    private String content;
    private String explanation;
    private String appreciation;
    private String authorId;
    private String authorName;
    private String pageIndex;

    @JsonIgnore
    public String getPageIndex() {
        return pageIndex;
    }

    @JsonProperty
    public void setPageIndex(String pageIndex) {
        this.pageIndex = pageIndex;
    }



}
