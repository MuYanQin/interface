package cn.qin.vo.collectionData;

import cn.qin.base.vo.BaseVo;
import cn.qin.entity.CollectionData;
import lombok.Data;

@Data
public class CollectionDataVo  extends BaseVo<CollectionData> {
    private String collectionId;
    private String objectId;
    private String userId;
    private String type;//1成语2诗文3名句4文字
}
