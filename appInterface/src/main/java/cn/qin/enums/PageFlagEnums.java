package cn.qin.enums;

public enum  PageFlagEnums {
    PAGE("0","分页"),
    NOT_PAGE("1","不分页");

    private String flag;

    private String desc;

    PageFlagEnums(String flag,String desc){
        this.flag = flag;
        this.desc = desc;
    }

    public String getFlag() {
        return flag;
    }
}
