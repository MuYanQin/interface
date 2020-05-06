package cn.qin.vo.applyJob;

import cn.qin.base.vo.BaseVo;
import cn.qin.entity.ApplyJob;
import lombok.Data;

@Data
public class ApplyJobVo extends BaseVo<ApplyJob> {

    private String applyJobId;
    private String partJobDetId;
    private String userId;
}
