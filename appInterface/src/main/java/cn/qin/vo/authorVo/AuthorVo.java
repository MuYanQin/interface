package cn.qin.vo.authorVo;

import lombok.Data;

@Data
public class AuthorVo {
    private String authorId;
    private String name;
    private String intro;
    private String count;//文章个数
}
