package com.java.online.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class RegexpUtil {

    private RegexpUtil(){
        throw new UnsupportedOperationException();
    }

    public static String deleteAll(String str, String reg){
        return str.replaceAll(reg,"");
    }

    public static boolean find(String src, String reg){
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(src);
        return matcher.find();
    }

    public static String matcher(String src, String reg, int group){
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(src);
        return matcher.find() ? matcher.group(group) : "";
    }

    public static String matcher(String src, String reg){
        return matcher(src, reg,2);
    }

}
