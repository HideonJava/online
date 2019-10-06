package com.java.online.util;

import com.java.online.code.RunInfo;
import com.java.online.lib.SpecialClassLoader;

import java.lang.reflect.Method;

public final class ReflectUtil {

    private ReflectUtil(){
        throw new UnsupportedOperationException();
    }

    public static RunInfo getRunInfo(String codeId, SpecialClassLoader classLoader){
        RunInfo info = new RunInfo();
        try {
            Class<?> mainClass = classLoader.findClass(codeId);
            Object target = mainClass.newInstance();
            Method method = mainClass.getMethod("main",String[].class);
            info.setMain(method);
            info.setTarget(target);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return info;
    }
}
