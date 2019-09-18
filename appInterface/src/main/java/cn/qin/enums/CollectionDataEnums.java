package cn.qin.enums;

public enum  CollectionDataEnums {

    WORD("1","文字"),
    IDIOM("2","成语"),
    REHSIS("3","名句"),
    POME("4","诗词");

    private String flag;

    private String desc;

    CollectionDataEnums(String flag, String desc){
        this.flag = flag;
        this.desc = desc;
    }

    public String getFlag(){
        return flag;
    }

    public String getDesc() {
        return desc;
    }
}
