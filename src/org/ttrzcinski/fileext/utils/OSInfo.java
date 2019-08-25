package org.ttrzcinski.fileext.utils;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * Passed information about OS.
 */
public final class OSInfo {

    /**
     * Hidden constructor - there is not point to initialize an instance.
     */
    private OSInfo() { }

    // TODO ASK CURRENT DIR ABOUT '.' - to confirm \ or /

    /**
     * Checks, if current OS is Windows-based.
     *
     * @return true means it's Windows
     */
    public static boolean isWindows() {
        return System.getProperty("os.name").contains("Windows");
    }

    /**
     * Checks, if current OS is *nix-based.
     *
     * @return true means
     */
    public static boolean isNix() {
        return !isWindows();
    }

    /**
     * Reads current screen resolution.
     *
     * @return screen size as dimension
     */
    public static final Dimension readResolution() {
        return Toolkit.getDefaultToolkit().getScreenSize();
    }
}
