package org.ttrzcinski.fileext.utils;

import java.nio.file.InvalidPathException;
import java.nio.file.Paths;

public class ParamCheck {

    public static boolean isPath(final String path) {
        if (!isSet(path)) return false;
        final String fixedPath = path.trim().toLowerCase();
        // Check, if it is a home or root
        if (fixedPath.equals("~") || fixedPath.equals(".")) {
            return true;
        }
        try {
            Paths.get(fixedPath);
        } catch (InvalidPathException | NullPointerException ex) {
            return false;
        }
        return true;
    }

    public static boolean isSet(String given) {
        return given != null && given.trim().length() > 2;
    }
}
