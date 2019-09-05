package cn.qin.vo;

import lombok.Data;

import java.util.List;

@Data
public class IdiomListVo {
    private String initial;
    private List<IdiomSearchVo> idioms;
}
