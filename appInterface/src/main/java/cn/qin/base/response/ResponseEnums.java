/*
 * Project Name : fb-microservice-biz
 * File Name : ResponseEnum.java
 * Date : 17-10-30 上午10:51
 * Author : qiaohao
 * Copyright (c) 2017, Feng Bang Leasing(Shang Hai)Co.,Ltd. All Rights Re
 * served.
 */

package cn.qin.base.response;

/**
 * @ClassName: ResponseEnum
 * @Description: 响应状态枚举
 * @author qiaohao
 * @date 2017/10/30
 */
public enum ResponseEnums implements ResponseType{

    SUCCESS("200","success","请求成功"),
    FAILURE("99999999","failure","请求失败");


    ResponseEnums(String code, String mark, String message) {
        this.code = code;
        this.msg = message;
        this.mark = mark;
    }

    private String code;

    private String msg;

    private String mark;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return msg;
    }

    public String getMark(){return mark;}
}
