package cn.qin.entity;

import cn.qin.base.entity.BaseEntity;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class Word extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "select REPLACE(uuid(),'-','')")
    private String wordId;
    private String word;
    private String bushou;
    private Integer bihua;
    private String initial;
    private String spell;
    private String bishun;
    private String pinyin;
    private String content;

}
