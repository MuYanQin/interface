package cn.qin.entity;

import cn.qin.base.entity.BaseEntity;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Data
public class Pome extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "select REPLACE(uuid(),'-','')")
    private String pomeId;
    private String name;
    private String type;//1、唐诗。2、宋词 3、元曲 4文
    private String content;//内容
    private String explanation;//名词解释
    private String appreciation;//赏析
    private String authorId;
    private String author;
    private String translation;//翻译
}
