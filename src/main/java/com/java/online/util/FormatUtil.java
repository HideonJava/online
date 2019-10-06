package com.java.online.util;

import java.text.DecimalFormat;

public class FormatUtil {

    private FormatUtil() {
        throw new UnsupportedOperationException();
    }

    public static String memory(long size) {

        String hrSize;

        double b = size;
        double k = size / 1024.0;
        double m = ((size / 1024.0) / 1024.0);
        double g = (((size / 1024.0) / 1024.0) / 1024.0);
        double t = ((((size / 1024.0) / 1024.0) / 1024.0) / 1024.0);

        DecimalFormat dec = new DecimalFormat("0.00");

        if (t > 1) {
            hrSize = dec.format(t).concat("TB");
        } else if (g > 1) {
            hrSize = dec.format(g).concat("GB");
        } else if (m > 1) {
            hrSize = dec.format(m).concat("MB");
        } else if (k > 1) {
            hrSize = dec.format(k).concat("KB");
        } else {
            hrSize = dec.format(b).concat("B");
        }

        return hrSize;
    }

    public static String timeMillis(long size) {

        String tSize;

        double s = 1000;
        double m = 1000 * 60;
        double h = 1000 * 60 * 60;

        if (size < s) {
            tSize = size + "MS";
        } else if (size < m && size >= s) {
            tSize = (int) (size / s) + "S";
        } else if (size < h && size >= m) {
            tSize = (int) (size / m) + "M";
        } else {
            tSize = (int) (size / h) + "S";
        }

        return tSize;
    }
}
