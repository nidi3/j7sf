package guru.nidi.j7fs;

import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.attribute.FileAttributeView;
import java.nio.file.attribute.FileStoreAttributeView;

/**
 *
 */
public class FlatMemoryFileStore extends FileStore {
    @Override
    public String name() {
        return "Pedro's store";
    }

    @Override
    public String type() {
        return "Pedro type";
    }

    @Override
    public boolean isReadOnly() {
        return false;
    }

    @Override
    public long getTotalSpace() throws IOException {
        return Runtime.getRuntime().totalMemory();
    }

    @Override
    public long getUsableSpace() throws IOException {
        return Runtime.getRuntime().freeMemory();
    }

    @Override
    public long getUnallocatedSpace() throws IOException {
        return getTotalSpace() - getUsableSpace();
    }

    @Override
    public boolean supportsFileAttributeView(Class<? extends FileAttributeView> type) {
        return type == FlatMemoryAttributeView.class;
    }

    @Override
    public boolean supportsFileAttributeView(String name) {
        return name.equals(new FlatMemoryAttributeView(null).name());
    }

    @Override
    public <V extends FileStoreAttributeView> V getFileStoreAttributeView(Class<V> type) {
        return null;
    }

    @Override
    public Object getAttribute(String attribute) throws IOException {
        return null;
    }
}
