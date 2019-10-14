package cn.qin.vo.idiomVo;

import cn.qin.base.vo.BaseVo;
import cn.qin.entity.Idiom;
import lombok.Data;

@Data
public class IdiomVo extends BaseVo<Idiom> {
    private String idiomId;

    private String idiom;//成语
    private String spell;//拼音
    private String decipher;//解释
    private String provenance;//出处
    private String samples;//举例
    private String abbreviation;//首字母缩写
    private String uses;//用法
    private String same;//同义词
    private String opposite;//反义词
    private String tag;//首字首拼
    private String collectionId;
}
