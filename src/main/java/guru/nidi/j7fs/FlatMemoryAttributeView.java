package guru.nidi.j7fs;

import java.io.IOException;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;

/**
 *
 */
public class FlatMemoryAttributeView implements BasicFileAttributeView {
    private final FlatMemoryFileAttributes attributes;

    public FlatMemoryAttributeView(FlatMemoryFileAttributes attributes) {
        this.attributes = attributes;
    }

    @Override
    public String name() {
        return "basic";
    }

    @Override
    public BasicFileAttributes readAttributes() throws IOException {
        return attributes;
    }

    @Override
    public void setTimes(FileTime lastModifiedTime, FileTime lastAccessTime, FileTime createTime) throws IOException {
        throw new UnsupportedOperationException();
    }
}
