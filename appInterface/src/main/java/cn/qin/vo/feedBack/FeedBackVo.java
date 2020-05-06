package cn.qin.vo.feedBack;

import cn.qin.base.vo.BaseVo;
import cn.qin.entity.FeedBack;
import lombok.Data;

@Data
public class FeedBackVo extends BaseVo<FeedBack> {
    private String feedBackId;

    private String userId;

    private String moblie;

    private String idea;
}
