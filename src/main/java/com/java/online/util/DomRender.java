package com.java.online.util;

import com.java.online.lib.Environment;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class DomRender {

    private static final String ICON_RIGHT = "<i class=\"fa fa-check\"></i>";
    private static final String ICON_WRONG = "<i class=\"fa fa-bolt\"></i>";
    private static final String TP_DEFAULT = "<p>%s<span>%s</span></p>";
    private static final String TP_DIAGNOSTIC = "<p>%s<span>第%s行:%s</span></p>";
    private static final String TP_RESULT = "<p>%s<span>%s</span></p>";
    private static final String TP_THROWABLE = "<p>%s<span>第%s行:%s</span><span class=\"blockquote\">%s</span></p>";
    private static final String TP_THROWABLE_SECURITY = "<p>%s<span>第%s行:%s</span></p>";
    private static final String TP_INFO = "运行时间:%s 运行内存:%s";

    private DomRender() {
        throw new UnsupportedOperationException();
    }

    public static String diagnostic(List<String[]> list) {
        StringBuilder sb = new StringBuilder();
        for (String[] item : list) {
            sb.append(String.format(TP_DIAGNOSTIC, ICON_WRONG, item[0], item[1]));
        }
        return sb.toString();
    }

    public static String result(long memory, long runtime) {
        String out = Environment.getOut();
        String[] arr = out.split("\n");
        memory = memory == 0 ? 1 : memory;
        runtime = runtime == 0 ? 1 : runtime;
        String m = FormatUtil.memory(memory);
        String r = FormatUtil.timeMillis(runtime);
        List<String> list = new ArrayList<>(Arrays.asList(arr));
        if (out.length() == 0) {
            list.add(0, String.format(TP_INFO, r, m));
            list.remove(list.size() - 1);
        } else {
            list.add(String.format(TP_INFO, r, m));
        }
        StringBuilder sb = new StringBuilder();
        for (String item : list) {
            sb.append(String.format(TP_RESULT, ICON_RIGHT, item));
        }
        return sb.toString();
    }

    public static String error(String message) {
        return String.format(TP_DEFAULT, ICON_WRONG, message);
    }

    public static String error(int line, String message) {
        return String.format(TP_THROWABLE_SECURITY, ICON_WRONG, line, message);
    }

    public static String error(int line, String message, String name) {
        return String.format(TP_THROWABLE, ICON_WRONG, line, message, name);
    }

}
