package cn.qin.entity;

import cn.qin.base.entity.BaseEntity;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class CollectionData extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "select REPLACE(uuid(),'-','')")
    private String collectionId;
    private String objectId;
    private String userId;
    private String type;//1成语2诗文3名句4文字 5兼职
}
