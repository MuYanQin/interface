package cn.qin.entity;

import cn.qin.base.entity.BaseEntity;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class Author extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "select REPLACE(uuid(),'-','')")
    private String authorId;
    private String name;
}
