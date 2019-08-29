package cn.qin.util;

import java.util.UUID;

/**
 * Created by qiaohao on 2017/9/12.
 */
public class UUIDUtils {

    public static String getUUID(){
        String uuid = UUID.randomUUID().toString().replace("-","");
        return uuid;
    }

    /**
     * 获得32个长度的十六进制的UUID
     * @return UUID
     */ public static String get32UUID(){
         UUID id=UUID.randomUUID();
         String[] idd=id.toString().split("-");
         return idd[0]+idd[1]+idd[2]+idd[3]+idd[4];
     }

    /**
     * 获得24个长度的十六进制的UUID
     * @return UUID
     */ public static String get24UUID(){
         UUID id=UUID.randomUUID();
         String[] idd=id.toString().split("-");
         return idd[0]+idd[1]+idd[4];
     }

    /**
     * 获得20个长度的十六进制的UUID
     * @return UUID
     */
    public static String get20UUID(){

        UUID id=UUID.randomUUID();
        String[] idd=id.toString().split("-");
        return idd[0]+idd[4];
    }

}
