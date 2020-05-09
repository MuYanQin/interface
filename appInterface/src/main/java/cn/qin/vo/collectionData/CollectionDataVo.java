package cn.qin.vo.collectionData;

import cn.qin.base.vo.BaseVo;
import cn.qin.entity.CollectionData;
import cn.qin.util.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class CollectionDataVo  extends BaseVo<CollectionData> {
    private String collectionId;
    private String objectId;
    private String objectName;//字、成语、诗文名称  名句名称
    private String authorName;//3、4有值
    private String content;//1、2、3有值
    @JsonFormat(pattern = DateUtils.formatStr_yyyyMM)
    private Date   collectionDate;//收藏时间
    private String type;//1文字2成语3名句4诗文

    private String userId;
    @JsonIgnore
    public String getUserId() {
        return userId;
    }
    @JsonProperty
    public void setUserId(String userId) {
        this.userId = userId;
    }
}
