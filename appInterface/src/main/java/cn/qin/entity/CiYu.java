package cn.qin.entity;

import cn.qin.base.entity.BaseEntity;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class CiYu extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "select REPLACE(uuid(),'-','')")
    private String id;
    private String ci;
    private String expl;
    private String spell;

}
