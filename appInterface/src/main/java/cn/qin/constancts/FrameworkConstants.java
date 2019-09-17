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
    public static final String AOP_REPOSITORY_INSERT = "execution(* cn.qin.base.repository..*.insert*Data(..))";

    public static final String AOP_REPOSITORY_UPDATE = "execution(* cn.qin.base..*.update*Data(..))";

    public static final String AOP_REPOSITORY_DELETE = "execution(* cn.qin.base..*.delete*Data(..))";

    public static final String AOP_REPOSITORY_INSERT_LIST = "execution(* cn.qin.base..*.insert*DataList(..))";

    public static final String AOP_REPOSITORY_UPDATE_LIST = "execution(* cn.qin.base..*.update*DataList(..))";

    public static final String AOP_REPOSITORY_DELETE_LIST = "execution(* cn.qin.base..*.delete*DataList(..))";

}
