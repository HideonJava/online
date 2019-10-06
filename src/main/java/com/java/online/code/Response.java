package com.java.online.code;

public class Response {

    private int code = SUCCESS;
    private String result;
    public static final int SUCCESS = 0;
    public static final int FAILURE = -1;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
