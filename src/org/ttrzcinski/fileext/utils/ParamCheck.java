package org.ttrzcinski.fileext.utils;

import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.Set;

/**
 * Checks given parameters.
 */
public final class ParamCheck {

    /**
     * Known unix paths, which are OK.
     */
    private static final Set<String> FIXED_PATHS = Set.of("~", ".");

    /**
     * File path pattern to match given string paths.
     */
    private static Pattern filePathPattern;

    /**
     * Hidden constructor - there is no point to initialize an instance.
     */
    private ParamCheck() { }

    /**
     * Initializes a file path's pattern.
     */
    private static void initFilePathPattern() {
        if (filePathPattern == null) {
            String pattern;
            if (OSInfo.isWindows()) {
                pattern = "([A-Z|a-z]:\\\\[^*|\"<>?\\n]*)|(\\\\\\\\.*?\\\\.*)";
            } else {
                pattern = "^/|(/[a-zA-Z0-9_-]+)+$";
            }
            filePathPattern = Pattern.compile(pattern);
        }
    }

    /**
     * Validates, if given path is a right directory path with regex.
     *
     * @param path given path
     * @return true means, if is a right path, false otherwise
     */
    public static boolean isPath(final String path) {
        // Check, if entered param has value
        if (!isSet(path)) {
            return false;
        }
        // Initialize pattern matcher
        initFilePathPattern();
        // Check if 2nd check as instance
        return filePathPattern.matcher(path).matches() && isPathWithTry(path);
    }

    /**
     * Checks, if given path is a valid file path with instance.
     *
     * @param path given path
     * @return true means it's valid, false otherwise
     */
    public static final boolean isPathWithTry(final String path) {
        boolean result = false;
        if (!isSet(path)) { return result; }
        final String fixedPath = path.trim().toLowerCase();
        // Check, if it is a home or root
        if (FIXED_PATHS.contains(fixedPath)) {
            result = true;
        } else {
            try {
                Paths.get(fixedPath);
                result = true;
            } catch (InvalidPathException | NullPointerException ex) {
                result = false;
            }
        }
        return result;
    }

    /**
     * Checks, if given param array contains any value.
     *
     * @param params given param to check
     * @return true means is is set, false otherwise
     */
    public static boolean isSet(final Object[] params) {
        boolean res = true;
        // If there is nothing outside to check
        if (params == null || params.length == 0) {
            res = false;
        } else if (params instanceof String[]) {
            res = isSet((String[]) params);
        } else {
            // If there is inside at least one empty item
            for (Object param : params) {
                if (!isSet(param)) {
                    res = false;
                    break;
                }
            }
        }
        return res;
    }

    /**
     * Checks, if given params string array contains only values.
     *
     * @param params given param to check
     * @return true means is is set, false otherwise
     */
    public static boolean isSet(final String[] params) {
        boolean result = true;
        // If there is nothing outside to check
        if (params == null || params.length == 0) {
            result = false;
        } else {
            // If there is inside at least one empty item
            for (String param : params) {
                if (!isSet(param)) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Checks, if given param contains any value after trim.
     *
     * @param param given param to check
     * @return true means is is set, false otherwise
     */
    public static boolean isSet(final String param) {
        return param != null ? param.trim().length() > 0 : false;
    }

    /**
     * Checks, if given param contains any value.
     *
     * @param param given param to check
     * @return true means is is set, false otherwise
     */
    public static boolean isSet(final Object param) {
        return param != null;
    }

}
