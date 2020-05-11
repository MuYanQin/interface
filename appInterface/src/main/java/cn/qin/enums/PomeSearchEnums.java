package cn.qin.enums;

/**
 * @author qiaomengnan
 * @ClassName: DeleteFlag
 * @Description:
 * @date 2018/2/5
 */
public enum PomeSearchEnums {

    AUTHOR("author","作者"),
    POME("pome","诗词"),
    RHESIS("rhesis","名句");

    private String type;

    private String desc;

    PomeSearchEnums(String type, String desc){
        this.type = type;
        this.desc = desc;
    }

    public String getType(){
        return type;
    }

}
