package cn.qin.vo.wordVo;

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
    private List<String> ciyuList;
    private List<String> idiomList;
}
