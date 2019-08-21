package org.ttrzcinski.fileext;

import org.json.JSONObject;
import org.ttrzcinski.fileext.interfaces.I2JSON;
import org.ttrzcinski.fileext.utils.JSONOutput;
import org.ttrzcinski.fileext.utils.ParamCheck;
import org.ttrzcinski.fileext.utils.StringFix;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Mounts implements I2JSON {

    private static Map<String, Path> mounts;

    public Mounts() {
        initPaths();

        readBasePaths();
    }

    private static void initPaths() {
        if (mounts == null) {
            mounts = new HashMap<>();
        }
    }

    public void readBasePaths() {
        // Read project's path
        Path currentRelativePath = Paths.get("");
        String projectDir = currentRelativePath.toAbsolutePath().toString();
        mounts.put("project", Path.of(projectDir));
        // It's sources
        mounts.put("sources", Path.of(String.format("%s/src", projectDir)));
        // It's tests
        mounts.put("tests", Path.of(String.format("%s/test", projectDir)));
        // It's resources
        mounts.put("resources", Path.of(String.format("%s/res", projectDir)));
        // It's outputs
        mounts.put("out", Path.of(String.format("%s/out", projectDir)));
        // User's home directory
        String home = System.getProperty("user.home");
        mounts.put("home", Path.of(home));
    }


    // Static class Builder
    public static class MountsBuilder {

        /// instance fields
        private Map<String, Path> mounts;

        public MountsBuilder() {
            this.mounts = new HashMap<>();
        }

        // Setter methods
        public MountsBuilder mount(String name, Path path) {
            if (ParamCheck.isSet(name) && path != null) {
                mounts.put(name.toLowerCase(), path);
            }
            return this;
        }

        public MountsBuilder mount(String name, String path) {
            if (ParamCheck.isSet(name) && ParamCheck.isPath(path)) {
                mounts.put(name.toLowerCase(), Path.of(path));
            }
            return this;
        }

        public MountsBuilder unmount(String name) {
            if (ParamCheck.isSet(name)) {
                mounts.remove(name.toLowerCase());
            }
            return this;
        }

        // build method to deal with outer class
        // to return outer instance
        public Mounts build() {
            var instance = new Mounts();
            mounts.keySet().forEach(key -> instance.mount(key, mounts.get(key)));
            return instance;
        }
    }

    /**
     * Returns pointed mount's path, if mount exists.
     *
     * @param name name of mount
     * @return mount's path, if found, null otherwise
     */
    public Path getPathOf(String name) {
        return ParamCheck.isSet(name) ? mounts.get(name.trim().toLowerCase()) : null;
    }

    /**
     * Returns wanted mount, if mount exists.
     *
     * @param name name of mount
     * @return mount, if found, null otherwise
     */
    public Mount get(String name) {
        return ParamCheck.isSet(name) ? new Mount(StringFix.simple(name)) : null;
    }

    public void toConsole() {
        initPaths();

        System.out.println("List of mounts:");
        mounts.forEach((key, value) -> System.out.printf("%s : %s%n", key, value));
    }

    public void mount(String name, Path path) {
        if (ParamCheck.isSet(name) && path != null) {
            mounts.put(name.toLowerCase(), path);
        }
    }

    public void mount(String name, String path) {
        if (ParamCheck.isSet(name) && ParamCheck.isPath(path)) {
            mounts.put(name.toLowerCase(), Path.of(path));
        }
    }

    public void unmount(String name) {
        if (ParamCheck.isSet(name)) {
            mounts.remove(name.toLowerCase());
        }
    }

    public String toJSON() {
        Map<String,String> newMap = mounts.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().toAbsolutePath().toString()));
        return new JSONObject(newMap).toString();
    }
}
