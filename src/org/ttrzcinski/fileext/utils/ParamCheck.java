package org.ttrzcinski.fileext.utils;

import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
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
     * Hidden constructor - there is no point to initialize an instance.
     */
    private ParamCheck() { }

    /**
     * Checks, if given path is a valid file path.
     *
     * @param path given path
     * @return true means it's valid, false otherwise
     */
    public static boolean isPath(final String path) {
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
     * Checks, if given parameter is set meaning has value.
     *
     * @param given given parameter
     * @return true means it has value, false otherwise
     */
    public static boolean isSet(final String given) {
        return given != null && given.trim().length() > 2;
    }
}
