package guru.nidi.j7fs;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.UserPrincipalLookupService;
import java.nio.file.spi.FileSystemProvider;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 */
public class FlatMemoryFileSystem extends FileSystem {
    private final FileSystemProvider provider;
    private final FileStore store = new FlatMemoryFileStore();
    final Map<String, byte[]> files = new ConcurrentHashMap<>();

    public FlatMemoryFileSystem(FileSystemProvider provider) {
        this.provider = provider;
    }

    @Override
    public FileSystemProvider provider() {
        return provider;
    }

    @Override
    public void close() throws IOException {

    }

    @Override
    public boolean isOpen() {
        return true;
    }

    @Override
    public boolean isReadOnly() {
        return false;
    }

    @Override
    public String getSeparator() {
        return null;
    }

    @Override
    public Iterable<Path> getRootDirectories() {
        return Collections.<Path>singletonList(new FlatPath(this,""));
    }

    @Override
    public Iterable<FileStore> getFileStores() {
        return Collections.singletonList(store);
    }

    @Override
    public Set<String> supportedFileAttributeViews() {
        return Collections.singleton(new FlatMemoryAttributeView(null).name());
    }

    @Override
    public Path getPath(String first, String... more) {
        String res = first;
        for (String m : more) {
            res += m;
        }
        return new FlatPath(this,res);
    }

    @Override
    public PathMatcher getPathMatcher(String syntaxAndPattern) {
        throw new UnsupportedOperationException();
    }

    @Override
    public UserPrincipalLookupService getUserPrincipalLookupService() {
        throw new UnsupportedOperationException();
    }

    @Override
    public WatchService newWatchService() throws IOException {
        throw new UnsupportedOperationException();
    }
}
