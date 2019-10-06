package com.java.online.util;

public class Constants {
    public static final int TIMEOUT = 15;
	public static final String TIMEOUT_ERROR_MESSAGE = "程序运行超时,请保持在" + TIMEOUT + "S内";
	public static final String SCANNER_ERROR_MESSAGE = "请检查输入参数";
	public static final String SYSTEM_ERROR_MESSAGE = "系统发生了意外的错误";
	public static final String REGEXP_PKG = "package\\s([a-zA-Z]\\w*)(\\.[a-zA-Z]\\w*)*;";
	public static final String REGEXP_CLASS = "(?:public\\s)?(?:.*\\s)?(class)\\s+([$_a-zA-Z][$_a-zA-Z0-9]*)";
}
