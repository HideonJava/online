package com.java.online.code;

import java.lang.reflect.Method;

public class RunInfo {

    private Object target;
    private Method main;

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public Method getMain() {
        return main;
    }

    public void setMain(Method main) {
        this.main = main;
    }
}
