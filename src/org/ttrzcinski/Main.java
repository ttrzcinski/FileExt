package org.ttrzcinski;

import org.ttrzcinski.fileext.Mounts;

public class Main {

    public static void main(String[] args) {
        Mounts mounts = new Mounts.MountsBuilder()
                .mount("all","./usr")
                .mount("maven","./m2")
                .unmount("maven")
                .build();
        mounts.toConsole();
        System.out.println("---");
        System.out.println(mounts.toJSON());
    }
}
