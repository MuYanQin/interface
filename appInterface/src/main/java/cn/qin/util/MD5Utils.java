package cn.qin.util;

import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * @author fangshaofeng
 * @ClassName: MD5Utils
 * @Description: md5工具类
 * @date 2019/1/29
 */
@Slf4j
public class MD5Utils {
    /**
     * @Title:
     * @Description:   返回md5加密字符串
     * @param str
     * @return
     * @throws
     * @author fangshaofeng
     * @date 2018/08/06 11:42:28
     */
    public static String getMD5(String str){
        if(StringUtils.isTrimBlank(str))
            return null;
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes("utf-8"));
            String md5 = new BigInteger(1,md.digest()).toString(16);
            return fillMD5(md5);
        }catch (Exception ex){
            ex.printStackTrace();
            log.error(ex.getMessage());
            return null;
        }
    }

    public static String fillMD5(String md5){
        return md5.length()==32?md5:fillMD5("0"+md5);
    }
}
