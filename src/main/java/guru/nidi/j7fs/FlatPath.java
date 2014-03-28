package guru.nidi.j7fs;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.*;
import java.util.Iterator;

/**
 *
 */
public class FlatPath implements Path {
    private final FileSystem fileSystem;
    private final String path;

    public FlatPath(FileSystem fileSystem, String path) {
        this.fileSystem = fileSystem;
        this.path = path;
    }

    @Override
    public FileSystem getFileSystem() {
        return fileSystem;
    }

    @Override
    public boolean isAbsolute() {
        return true;
    }

    @Override
    public Path getRoot() {
        return new FlatPath(fileSystem, "");
    }

    @Override
    public Path getFileName() {
        return this;
    }

    @Override
    public Path getParent() {
        return null;
    }

    @Override
    public int getNameCount() {
        return 1;
    }

    @Override
    public Path getName(int index) {
        if (index != 0) {
            throw new IllegalArgumentException();
        }
        return this;
    }

    @Override
    public Path subpath(int beginIndex, int endIndex) {
        if (beginIndex != 0 || endIndex != 1) {
            throw new IllegalArgumentException();
        }
        return this;
    }

    @Override
    public boolean startsWith(Path other) {
        return startsWith(other.toString());
    }

    @Override
    public boolean startsWith(String other) {
        return path.equals(other);
    }

    @Override
    public boolean endsWith(Path other) {
        return endsWith(other.toString());
    }

    @Override
    public boolean endsWith(String other) {
        return path.equals(other.toString());
    }

    @Override
    public Path normalize() {
        return this;
    }

    @Override
    public Path resolve(Path other) {
        return resolve(other.toString());
    }

    @Override
    public Path resolve(String other) {
        return new FlatPath(fileSystem, other);
    }

    @Override
    public Path resolveSibling(Path other) {
        return null;
    }

    @Override
    public Path resolveSibling(String other) {
        return null;
    }

    @Override
    public Path relativize(Path other) {
        return null;
    }

    @Override
    public URI toUri() {
        return null;
    }

    @Override
    public Path toAbsolutePath() {
        return null;
    }

    @Override
    public Path toRealPath(LinkOption... options) throws IOException {
        return null;
    }

    @Override
    public File toFile() {
        return null;
    }

    @Override
    public WatchKey register(WatchService watcher, WatchEvent.Kind<?>[] events, WatchEvent.Modifier... modifiers) throws IOException {
        return null;
    }

    @Override
    public WatchKey register(WatchService watcher, WatchEvent.Kind<?>... events) throws IOException {
        return null;
    }

    @Override
    public Iterator<Path> iterator() {
        return null;
    }

    @Override
    public int compareTo(Path other) {
        return 0;
    }

    @Override
    public String toString() {
        return path;
    }
}
