package org.ttrzcinski;

import org.ttrzcinski.fileext.Mounts;
import org.ttrzcinski.fileext.utils.OSInfo;
import org.ttrzcinski.fileext.utils.StringFix;

/**
 * REMOVE AFTER FINISH - Just class to run as application with arguments.
 */
public final class Main {

    /**
     * Hidden constructor - there is not point to initialize an instance.
     */
    private Main() { }

    /**
     * REMOVE AFTER FINISH.
     * to fix: "Uncommented main method found."
     *
     * Entry point to the application.
     *
     * @param args given application arguments
     */
    public static void main(final String[] args) {
        Mounts mounts = Mounts.getInstance()
                .withMount("mvn", "~/.m2");
        mounts.toConsole();
        System.out.println("---");
        System.out.println(mounts.toJSON());
    }
}
