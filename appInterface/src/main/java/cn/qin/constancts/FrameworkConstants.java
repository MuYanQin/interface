package cn.qin.constancts;

/**
 * @author qiaomengnan
 * @ClassName: Constants
 * @Description: 框架常量配置
 * @date 2018/1/7
 */
public class FrameworkConstants {


    public static final String MAPPER_SCAN =  "cn.qin.dao";

    //1、insertData
    //2、insertWordData
    public static final String AOP_REPOSITORY_INSERT = "execution(* cn.qin.dao..*.insert*Data(..))";

    public static final String AOP_REPOSITORY_UPDATE = "execution(* cn.qin.dao..*.update*Data(..))";

    public static final String AOP_REPOSITORY_DELETE = "execution(* cn.qin.daoa..*.delete*Data(..))";

    public static final String AOP_REPOSITORY_INSERT_LIST = "execution(* cn.qin.dao..*.insert*DataList(..))";

    public static final String AOP_REPOSITORY_UPDATE_LIST = "execution(* cn.qin.dao..*.update*DataList(..))";

    public static final String AOP_REPOSITORY_DELETE_LIST = "execution(* cn.qin.dao..*.delete*DataList(..))";

}
