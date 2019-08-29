package cn.qin.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author qiaomengnan
 * @ClassName: PycreditTypeConstants
 * @Description:鹏元接口返回值对应解析
 * @date 2018/6/13
 */
public class PycreditTypeUtils {

    /**
     * @Fields  : 反欺诈查询状态描述
     * @author qiaomengnan
     */
    private static final Map<String,String>  treatResult = new HashMap<>();

    /**
     * @Fields  : 反欺诈查询状态描述
     * @author qiaomengnan
     */
    private static final Map<String,String>  status = new HashMap<>();

    /**
     * @Fields  : 公安不良记录犯罪状态
     * @author qiaomengnan
     */
    private static final Map<String,String>  policeCriminalStatus = new HashMap<>();

    /**
     * @Fields  : 公安不良记录犯罪状态
     * @author qiaomengnan
     */
    private static final Map<String,String>  success = new HashMap<>();

    /**
     * @Fields  : 手机三要素
     * @author qiaomengnan
     */
    private static final Map<String,String>  threeElement = new HashMap<>();

    /**
     * @Fields  : 在网时长
     * @author qiaomengnan
     */
    private static final Map<String,String>  onlineTime = new HashMap<>();

    private static final Map<String,String>  onlineTimeInfo = new HashMap<>();

    /**
     * @Fields  : 不良记录调用结果
     * @author qiaomengnan
     */
    private static final Map<String,String>  policeConsistence = new HashMap<>();

    /**
     * @Fields  : 前海错误码
     * @author qiaomengnan
     */
    private static final Map<String,String>  erCode = new HashMap<>();
    /**
     * @Fields  : 身份认证信息-认证信息
     * @author yanggang
     */
    private static final Map<String,String>  result = new HashMap<>();

    public static String getResult(String key){
        return result.get(key);
    }
    public static String getResultCode(String desc){
        for(String key:result.keySet()){
            if(result.get(key).equals(desc)){
                return key;
            }
        }
        return null;
    }
    public static String getOnlineTimeInfoCode(String desc){
        for(String key:onlineTimeInfo.keySet()){
            if(onlineTimeInfo.get(key).equals(desc)){
                return key;
            }
        }
        return null;
    }
    /**
     * @Fields  : 手机状态
     * @author yanggang
     */
    private static final Map<String,String>  phoneStatus = new HashMap<>();

    public static String getPhoneStatus(String key){
        return phoneStatus.get(key);
    }
    /**
     * @Fields  : 运营商名称
     * @author yanggang
     */
    private static final Map<String,String>  operator = new HashMap<>();
    /**
     * @Fields  : 犯罪时间
     * @author yanggang
     */
    private static final Map<String,String>  policeCriminalTimes = new HashMap<>();

    static {
        treatResult.put("1","查得");
        treatResult.put("2","未查得");
        treatResult.put("3","其它原因未查得");

        status.put("1","核查一致");
        status.put("2","核查不一致");
        status.put("3","未知原因");

        policeCriminalStatus.put("0","未比中");
        policeCriminalStatus.put("1","比中在逃");
        policeCriminalStatus.put("2","比中前科");
        policeCriminalStatus.put("3","比中涉毒");
        policeCriminalStatus.put("4","比中吸毒");
        policeCriminalStatus.put("9","修正人员");

        success.put("true", "成功");
        success.put("false", "失败");

        threeElement.put("0", "一致");
        threeElement.put("1", "不一致");
        threeElement.put("2", "库无记录");

        onlineTime.put("0", "查询成功有数据");
        onlineTime.put("1", "库无记录");

        onlineTimeInfo.put("0", "(0,6]");
        onlineTimeInfo.put("1", "(6,12]");
        onlineTimeInfo.put("3", "(12,24]");
        onlineTimeInfo.put("4", "(24,+)");

        policeConsistence.put("0", "请求成功有数据");
        policeConsistence.put("2", "请求成功无数据");

        erCode.put("E000000", "交易成功");

        result.put("1","姓名和公民身份号码一致");
        result.put("2","公民身份号码一致，姓名不一致");
        result.put("3","库中无此号，请到户籍所在地进行核实");

        phoneStatus.put("1","正常在用");
        phoneStatus.put("2","停机");
        phoneStatus.put("3","未启用");
        phoneStatus.put("4","已销号");
        phoneStatus.put("5","其他");
        phoneStatus.put("6","预销号");

        operator.put("1","中国电信");
        operator.put("2","中国移动");
        operator.put("3","中国联通");

        policeCriminalTimes.put("[0,0.25)","0.25 年(即 3 个月)(不含)以内");
        policeCriminalTimes.put("[0.25,0.5)","0.25 年(3 个月)以上,0.5 年(即 6 个月)(不含)以内");
        policeCriminalTimes.put("[0.5,1)","0.5 年(即 6 个月)以上,1 年(不含)以内");
        policeCriminalTimes.put("[1,2)","1 年以上,2 年(不含)以内");
        policeCriminalTimes.put("[2,5)","2 年以上,5 年(不含)以内");
        policeCriminalTimes.put("[5,10)","5 年以上,10 年(不含)以内");
        policeCriminalTimes.put("[10,)","10 年以上");

    }
    public static String getTreatResult(String key){
        return treatResult.get(key);
    }
    public static String getThreeElement(String key){
        return threeElement.get(key);
    }
    public static String getThreeElementCode(String desc){
        for(String key:threeElement.keySet()){
            if(threeElement.get(key).equals(desc)){
                return key;
            }
        }
        return null;
    }
    public static String getOnlineTime(String key){
        return onlineTime.get(key);
    }
    public static String getErCode(String key){ return erCode.get(key); }
    public static String getPoliceConsistence(String key){ return policeConsistence.get(key); }
    public static String getSuccess(String successKey){
        return success.get(successKey);
    }
    public static String getStatus(String key){
        return status.get(key);
    }
    public static String getPoliceCriminalStatus(String key){ return policeCriminalStatus.get(key); }
    public static String getOperator(String key){
        return operator.get(key);
    }
    public static String getPoliceCriminalTimes(String key){ return policeCriminalTimes.get(key); }

}
