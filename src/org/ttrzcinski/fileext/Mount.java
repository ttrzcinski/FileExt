package org.ttrzcinski.fileext;

import org.jetbrains.annotations.NotNull;
import org.ttrzcinski.fileext.interfaces.IPath;
import org.ttrzcinski.fileext.utils.FileExt;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

/**
 * Represents a single known mounted point of directories structure.
 *
 * @author <a href="mailto:trzcinski.tomasz.1988@gmail.com">Tomasz T.</a>
 */
public final class Mount implements IPath {

    /**
     * Kept path of the directory.
     */
    private Path keptPath;

    /**
     * Hidden constructor - there is no point to initialize an instance.
     */
    private Mount() { }

    /**
     * Creates new instance with given path.
     *
     * @param path given path
     */
    private Mount(@NotNull final Path path) {
        this.keptPath = path;
    }

    /**
     * Creates new instance with given path.
     *
     * @param path given path
     * @return an instance
     */
    public static Mount of(@NotNull final Path path) {
        return new Mount(path);
    }

    /**
     * Creates new instance with given path.
     *
     * @param path given path
     * @return new instance
     */
    public static Mount of(@NotNull final String path) {
        return new Mount(Path.of(path));
    }

    /**
     * Creates new instance with given path with subdirectory.
     *
     * @param path given path
     * @param subdirectory subdirectory under given path
     * @return new instance
     */
    public static Mount of(@NotNull final String path,
                           @NotNull final String subdirectory) {
        return new Mount(Path.of(String.format("%s/%s", path, subdirectory)));
    }

    /**
     * Returns kept path.
     *
     * @return kept path
     */
    @Override
    public Path getPath() {
        return this.keptPath;
    }

    /**
     * Returns list of files within this mount.
     *
     * @return list of files within this path
     */
    @Override
    public List<File> getFiles() {
        return FileExt.listFilesOf(this.keptPath);
    }

    /**
     * Returns list of files within this mount filtered by passed filter.
     *
     * @param filter given filter criteria
     * @return list of files, if found, empty list otherwise
     */
    @Override
    public List<File> getFiles(final String filter) {
        return FileExt.listFilesOf(this.keptPath, filter);
    }

    /**
     * Returns list of subdirectories within mount.
     *
     * @return list of subdirectories, if found, empty list otherwise
     */
    @Override
    public List<File> getSubdirectories() {
        return FileExt.listSubdirectoriesOf(this.keptPath);
    }

    /**
     * Returns list of subdirectories within mount with filter.
     *
     * @param filter given filter criteria
     * @return list of subdirectories, if found, empty list otherwise
     */
    @Override
    public List<File> getSubdirectories(final String filter) {
        return FileExt.listSubdirectoriesOf(this.keptPath, filter);
    }

    /**
     * Creates file following kept path.
     *
     * @return handle to the file
     */
    @Override
    public File create() {
        return FileExt.create(this.keptPath);
    }

    /**
     * Returns handle to file pointed by path.
     *
     * @return handle to file
     */
    public File read() {
        return FileExt.read(this.keptPath);
    }

    /**
     * Checks, if file exists in kept path.
     *
     * @return true means it exists, false otherwise
     */
    @Override
    public boolean exists() {
        return FileExt.exists(this.keptPath);
    }

    /**
     * Removes file kept in the path.
     *
     * @return handle to the file
     */
    @Override
    public File remove() {
        return FileExt.remove(this.keptPath);
    }
}
