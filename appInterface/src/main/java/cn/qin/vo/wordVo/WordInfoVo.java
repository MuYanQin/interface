package cn.qin.vo.wordVo;

import cn.qin.vo.ciYuvo.CiYuVo;
import cn.qin.vo.idiomVo.IdiomVo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class WordInfoVo {
    private String wordId;
    private String word;
    private String bushou;
    private Integer bihua;
    private String spell;
    private String bishun;
    private String pinyin;
    private String content;
    private String collectionId;//空为收藏

    private List<CiYuVo> ciyuList;
    private List<IdiomVo> idiomList;
    @JsonIgnore
    private List<CiYuVo> ciYuVos;
    @JsonIgnore
    private List<IdiomVo> idiomVos;
}
