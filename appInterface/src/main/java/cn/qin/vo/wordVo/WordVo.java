package cn.qin.vo.wordVo;

import cn.qin.base.vo.BaseVo;
import cn.qin.entity.Word;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class WordVo extends BaseVo<Word> {
    private String wordId;
    private String word;
    private String pinyin;

    @JsonIgnore
    private String bushou;
    @JsonIgnore
    private Integer bihua;

    @JsonIgnore
    private List<WordVo> wordVos;
}
