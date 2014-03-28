package guru.nidi.j7fs;

import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;

/**
 *
 */
public class FlatMemoryFileAttributes implements BasicFileAttributes {
    private final String name;
    private final long size;

    public FlatMemoryFileAttributes(String name, long size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public FileTime lastModifiedTime() {
        return null;
    }

    @Override
    public FileTime lastAccessTime() {
        return null;
    }

    @Override
    public FileTime creationTime() {
        return null;
    }

    @Override
    public boolean isRegularFile() {
        return true;
    }

    @Override
    public boolean isDirectory() {
        return false;
    }

    @Override
    public boolean isSymbolicLink() {
        return false;
    }

    @Override
    public boolean isOther() {
        return false;
    }

    @Override
    public long size() {
        return size;
    }

    @Override
    public Object fileKey() {
        return name;
    }
}
