package org.ttrzcinski.fileext;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import org.ttrzcinski.fileext.interfaces.I2Console;
import org.ttrzcinski.fileext.interfaces.I2JSON;
import org.ttrzcinski.fileext.interfaces.IMounting;
import org.ttrzcinski.fileext.utils.JSONOutput;
import org.ttrzcinski.fileext.utils.StringFix;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Keeps all known mounted paths and methods to operate on the collection.
 */
public class Mounts implements I2JSON, I2Console, IMounting<Mount> {

    /**
     * Saved mounts of named paths.
     */
    private static Map<String, Mount> mounts;

    /**
     * Kept only instance of mounts set.
     */
    private static Mounts instance;

    /**
     * Crates new mounts set.
     */
    private Mounts() {
        readBasePaths();
    }

    /**
     * Obtains the only existing instance of Mounts.
     *
     * @return kept instance
     */
    public static Mounts getInstance() {
        if (instance == null) {
            // synchronized block to remove overhead
            synchronized (Mounts.class) {
                // if instance is null, initialize
                if (instance == null) {
                    mounts = new HashMap<>();
                    instance = new Mounts();
                }
            }
        }
        return instance;
    }

    /**
     * Reads basic paths from current project.
     */
    public static void readBasePaths() {
        // Read project's path
        Path currentRelativePath = Paths.get("");
        String projectDir = currentRelativePath.toAbsolutePath().toString();
        mounts.put("project", Mount.of(projectDir));
        // It's sources
        mounts.put("sources", Mount.of(projectDir, "src"));
        // It's tests
        mounts.put("tests", Mount.of(projectDir, "test"));
        // It's resources
        mounts.put("resources", Mount.of(projectDir, "res"));
        // It's outputs
        mounts.put("out", Mount.of(projectDir, "out"));
        // User's home directory
        String home = System.getProperty("user.home");
        mounts.put("home", Mount.of(home));
    }

    /**
     * Checks, if mounts contains given name.
     *
     * @param name given name
     * @return true means it exists, false otherwise
     */
    public static final boolean contains(@NotNull final String name) {
        String fixed = StringFix.simple(name);
        return fixed.length() > 0 ? mounts.containsKey(fixed) : false;
    }

    /**
     * Removes all kept mounts,
     */
    public void clear() {
        if (mounts != null) {
            mounts.clear();
        }
    }

    /**
     * Returns number of kept mounts.
     *
     * @return number of kept mounts
     */
    public int size() {
        return mounts != null ? mounts.size() : 0;
    }

    /**
     * Mounts given path with given name.
     *
     * @param name given name
     * @param mount mount
     * @return handle to the mounts
     */
    public Mounts withMount(@NotNull final String name, @NotNull final Mount mount) {
        this.mount(name, mount);
        return instance;
    }

    /**
     * Mounts given path with given name.
     *
     * @param name given name
     * @param path mount's path
     * @return handle to the mounts
     */
    public Mounts withMount(@NotNull final String name, @NotNull final Path path) {
        this.mount(name, path);
        return instance;
    }

    /**
     * Mounts given path with given name.
     *
     * @param name given name
     * @param path mount's path
     * @return handle to the mounts
     */
    public Mounts withMount(@NotNull final String name, @NotNull final String path) {
        this.mount(name, path);
        return instance;
    }

    /**
     * Mounts given path with given name.
     * *
     *
     * @param name given name
     * @param mount mount's path
     */
    @Override
    public void mount(@NotNull final String name, final Mount mount) {
        String fixed = StringFix.simple(name);
        if (fixed.length() > 0 && mount != null) {
            mounts.put(name.toLowerCase(), mount);
        }
    }

    /**
     * Mounts given path with given name.
     *
     * @param name given name
     * @param path mount's path
     */
    public void mount(@NotNull final String name, @NotNull final Path path) {
        String fixed = StringFix.simple(name);
        if (fixed.length() > 0 && path != null) {
            mounts.put(name.toLowerCase(), Mount.of(path));
        }
    }

    /**
     * Mounts given path with given name.
     *
     * @param name given name
     * @param path mount's path
     */
    public void mount(@NotNull final String name, @NotNull final String path) {
        String fixed = StringFix.simple(name);
        String fixedPath = StringFix.toNotNull(path);
        if (fixed.length() > 0 && fixedPath.length() > 0) {
            mounts.put(fixed, Mount.of(fixedPath));
        }
    }

    /**
     * Removes mount with given name.
     *
     * @param name given name of mount
     */
    public void unmount(@NotNull final String name) {
        String fixed = StringFix.simple(name);
        if (contains(fixed)) {
            mounts.remove(fixed);
        }
    }

    /**
     * Returns wanted mount, if mount exists.
     *
     * @param name name of mount
     * @return mount, if found, null otherwise
     */
    public static final Mount get(String name) {
        String fixed = StringFix.simple(name);
        return contains(fixed) ? mounts.get(fixed) : null;
    }

    /**
     * Returns map clone of kept mounts.
     *
     * @return map of mounts
     */
    public static final Map<String, Mount> getAll() {
        return mounts.entrySet().stream()
                .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
    }

    /**
     * Shows on console
     */
    public void toConsole() {
        System.out.println("List of mounts:");
        mounts.forEach((key, value) -> System.out.printf("%s : %s%n", key, value));
    }
    /**
     * Returns set of kept Mounts as JSON set.
     *
     * @return JSON set with all kept mounts.
     */
    public final String toJSON() {
        Map<String, String> map = mounts.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey, e -> e.getValue()
                                .getPath().toAbsolutePath().toString()));
        return JSONOutput.toJSON(map);
    }

}
