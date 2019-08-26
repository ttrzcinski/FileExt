package org.ttrzcinski;

import org.ttrzcinski.fileext.Mounts;

/**
 * REMOVE AFTER FINISH - Just class to run as application with arguments.
 *
 * @author <a href="mailto:trzcinski.tomasz.1988@gmail.com">Tomasz T.</a>
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
        final Mounts mounts = Mounts.getInstance()
                .withMount("mvn", "~/.m2");
        mounts.toConsole();
        System.out.println("---");
        System.out.println(mounts.toJSON());
    }
}
