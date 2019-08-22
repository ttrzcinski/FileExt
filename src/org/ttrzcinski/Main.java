package org.ttrzcinski;

import org.ttrzcinski.fileext.Mounts;

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
        final Mounts mounts = new Mounts.MountsBuilder()
                .mount("all", "./usr")
                .mount("maven", "./m2")
                .unmount("maven")
                .build();
        mounts.toConsole();
        System.out.println("---");
        System.out.println(mounts.toJSON());
    }
}
