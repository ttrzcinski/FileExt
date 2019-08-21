package org.ttrzcinski.fileext.utils;

public class StringFix {

    public static String toNotNull(String given) {
        return given == null || given.trim().length() == 0 ? "" : given.trim();
    }

    public static String simple(String given) {
        return toNotNull(given).toLowerCase();
    }
}
