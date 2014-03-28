package guru.nidi.j7fs;

import java.io.IOException;
import java.net.URI;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.FileAttributeView;
import java.nio.file.spi.FileSystemProvider;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 */
public class FlatMemoryFileSystemProvider extends FileSystemProvider {
    private FlatMemoryFileSystem fileSystem = new FlatMemoryFileSystem(this);

    @Override
    public String getScheme() {
        return "flatmem";
    }

    @Override
    public FileSystem newFileSystem(URI uri, Map<String, ?> env) throws IOException {
        throw new FileSystemAlreadyExistsException();
    }

    @Override
    public FileSystem getFileSystem(URI uri) {
        return fileSystem;
    }

    @Override
    public Path getPath(URI uri) {
        return new FlatPath(fileSystem, uri.toString().substring(getScheme().length() + 3));
    }

    @Override
    public SeekableByteChannel newByteChannel(Path path, Set<? extends OpenOption> options, FileAttribute<?>... attrs) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public DirectoryStream<Path> newDirectoryStream(Path dir, DirectoryStream.Filter<? super Path> filter) throws IOException {
        return null; //TODO
    }

    @Override
    public void createDirectory(Path dir, FileAttribute<?>... attrs) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Path path) throws IOException {
        fileSystem.files.remove(path.toString());
    }

    @Override
    public void copy(Path source, Path target, CopyOption... options) throws IOException {
        fileSystem.files.put(target.toString(), fileSystem.files.get(source.toString()));
    }

    @Override
    public void move(Path source, Path target, CopyOption... options) throws IOException {
        copy(source, target, options);
        delete(source);
    }

    @Override
    public boolean isSameFile(Path path, Path path2) throws IOException {
        return path.toString().equals(path2.toString());
    }

    @Override
    public boolean isHidden(Path path) throws IOException {
        return false;
    }

    @Override
    public FileStore getFileStore(Path path) throws IOException {
        return fileSystem.getFileStores().iterator().next();
    }

    @Override
    public void checkAccess(Path path, AccessMode... modes) throws IOException {
        if (modes.length == 0 && !fileSystem.files.containsKey(path.toString())) {
            throw new NoSuchFileException(path.toString());
        }
    }

    @Override
    public <V extends FileAttributeView> V getFileAttributeView(Path path, Class<V> type, LinkOption... options) {
        if (type == FlatMemoryAttributeView.class) {
            return (V) new FlatMemoryAttributeView(readAttributes(path, FlatMemoryFileAttributes.class, options));
        }
        throw new UnsupportedOperationException();
    }

    @Override
    public <A extends BasicFileAttributes> A readAttributes(Path path, Class<A> type, LinkOption... options) {
        if (type == FlatMemoryFileAttributes.class) {
            byte[] content = fileSystem.files.get(path.toString());
            return (A) new FlatMemoryFileAttributes(path.toString(), content == null ? 0 : content.length);
        }
        throw new UnsupportedOperationException();
    }

    @Override
    public Map<String, Object> readAttributes(Path path, String attributes, LinkOption... options) throws IOException {
        return new HashMap<>();
    }

    @Override
    public void setAttribute(Path path, String attribute, Object value, LinkOption... options) throws IOException {
        throw new UnsupportedOperationException();
    }
}
