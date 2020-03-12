package com.qf.util;

public class StringUtil {


    /**
     * 封装redis键
     */
    public static String getRedisKey(String pre ,String key){
        StringBuilder sb = new StringBuilder();
        sb.append(pre);
        sb.append(key);
        return sb.toString();
    }

}
