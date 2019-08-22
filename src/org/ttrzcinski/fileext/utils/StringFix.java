package org.ttrzcinski.fileext.utils;

/**
 * Keeps methods to fix String to common standard.
 */
public final class StringFix {

    /**
     * Hidden constructor - there is no point to initialize an instance.
     */
    private StringFix() { }

    /**
     * Fixed string to not-null initialized empty string, if is null.
     *
     * @param given given string to fix
     * @return fixed string
     */
    public static String toNotNull(final String given) {
        if (given == null || given.trim().length() == 0) {
            return "";
        } else {
            return given.trim();
        }
    }

    /**
     * Simplifies string to trimmed and lowercase form.
     *
     * @param given given string to fix
     * @return fixed string
     */
    public static String simple(final String given) {
        return toNotNull(given).toLowerCase();
    }
}
