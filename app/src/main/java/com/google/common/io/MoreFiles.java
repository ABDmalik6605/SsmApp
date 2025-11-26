package com.google.common.io;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.TreeTraverser;
import com.google.common.graph.SuccessorsFunction;
import com.google.common.graph.Traverser;
import com.google.common.io.ByteSource;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.Channels;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.Charset;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileSystemException;
import java.nio.file.LinkOption;
import java.nio.file.NoSuchFileException;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.SecureDirectoryStream;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.stream.Stream;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* loaded from: classes2.dex */
public final class MoreFiles {
    private static final SuccessorsFunction<Path> FILE_TREE = new SuccessorsFunction<Path>() { // from class: com.google.common.io.MoreFiles.1
        @Override // com.google.common.graph.SuccessorsFunction
        public Iterable<Path> successors(Path path) {
            return MoreFiles.fileTreeChildren(path);
        }
    };

    private MoreFiles() {
    }

    public static ByteSource asByteSource(Path path, OpenOption... openOptionArr) {
        return new PathByteSource(path, openOptionArr);
    }

    private static final class PathByteSource extends ByteSource {
        private static final LinkOption[] FOLLOW_LINKS = new LinkOption[0];
        private final boolean followLinks;
        private final OpenOption[] options;
        private final Path path;

        private PathByteSource(Path path, OpenOption... openOptionArr) {
            this.path = (Path) Preconditions.checkNotNull(path);
            OpenOption[] openOptionArr2 = (OpenOption[]) openOptionArr.clone();
            this.options = openOptionArr2;
            this.followLinks = followLinks(openOptionArr2);
        }

        private static boolean followLinks(OpenOption[] openOptionArr) {
            for (OpenOption openOption : openOptionArr) {
                if (openOption == LinkOption.NOFOLLOW_LINKS) {
                    return false;
                }
            }
            return true;
        }

        @Override // com.google.common.io.ByteSource
        public InputStream openStream() throws IOException {
            return java.nio.file.Files.newInputStream(this.path, this.options);
        }

        private BasicFileAttributes readAttributes() throws IOException {
            return java.nio.file.Files.readAttributes(this.path, BasicFileAttributes.class, this.followLinks ? FOLLOW_LINKS : new LinkOption[]{LinkOption.NOFOLLOW_LINKS});
        }

        @Override // com.google.common.io.ByteSource
        public Optional<Long> sizeIfKnown() {
            try {
                BasicFileAttributes attributes = readAttributes();
                if (attributes.isDirectory() || attributes.isSymbolicLink()) {
                    return Optional.absent();
                }
                return Optional.of(Long.valueOf(attributes.size()));
            } catch (IOException unused) {
                return Optional.absent();
            }
        }

        @Override // com.google.common.io.ByteSource
        public long size() throws IOException {
            BasicFileAttributes attributes = readAttributes();
            if (attributes.isDirectory()) {
                throw new IOException("can't read: is a directory");
            }
            if (attributes.isSymbolicLink()) {
                throw new IOException("can't read: is a symbolic link");
            }
            return attributes.size();
        }

        @Override // com.google.common.io.ByteSource
        public byte[] read() throws IOException {
            SeekableByteChannel seekableByteChannelNewByteChannel = java.nio.file.Files.newByteChannel(this.path, this.options);
            try {
                byte[] file = Files.readFile(Channels.newInputStream(seekableByteChannelNewByteChannel), seekableByteChannelNewByteChannel.size());
                if (seekableByteChannelNewByteChannel != null) {
                    seekableByteChannelNewByteChannel.close();
                }
                return file;
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    if (seekableByteChannelNewByteChannel != null) {
                        if (th != null) {
                            try {
                                seekableByteChannelNewByteChannel.close();
                            } catch (Throwable th3) {
                                th.addSuppressed(th3);
                            }
                        } else {
                            seekableByteChannelNewByteChannel.close();
                        }
                    }
                    throw th2;
                }
            }
        }

        @Override // com.google.common.io.ByteSource
        public CharSource asCharSource(Charset charset) {
            if (this.options.length == 0) {
                return new ByteSource.AsCharSource(charset) { // from class: com.google.common.io.MoreFiles.PathByteSource.1
                    @Override // com.google.common.io.CharSource
                    public Stream<String> lines() throws IOException {
                        return java.nio.file.Files.lines(PathByteSource.this.path, this.charset);
                    }
                };
            }
            return super.asCharSource(charset);
        }

        public String toString() {
            return "MoreFiles.asByteSource(" + this.path + ", " + Arrays.toString(this.options) + ")";
        }
    }

    public static ByteSink asByteSink(Path path, OpenOption... openOptionArr) {
        return new PathByteSink(path, openOptionArr);
    }

    private static final class PathByteSink extends ByteSink {
        private final OpenOption[] options;
        private final Path path;

        private PathByteSink(Path path, OpenOption... openOptionArr) {
            this.path = (Path) Preconditions.checkNotNull(path);
            this.options = (OpenOption[]) openOptionArr.clone();
        }

        @Override // com.google.common.io.ByteSink
        public OutputStream openStream() throws IOException {
            return java.nio.file.Files.newOutputStream(this.path, this.options);
        }

        public String toString() {
            return "MoreFiles.asByteSink(" + this.path + ", " + Arrays.toString(this.options) + ")";
        }
    }

    public static CharSource asCharSource(Path path, Charset charset, OpenOption... openOptionArr) {
        return asByteSource(path, openOptionArr).asCharSource(charset);
    }

    public static CharSink asCharSink(Path path, Charset charset, OpenOption... openOptionArr) {
        return asByteSink(path, openOptionArr).asCharSink(charset);
    }

    public static ImmutableList<Path> listFiles(Path path) throws IOException {
        try {
            DirectoryStream<Path> directoryStreamNewDirectoryStream = java.nio.file.Files.newDirectoryStream(path);
            try {
                ImmutableList<Path> immutableListCopyOf = ImmutableList.copyOf(directoryStreamNewDirectoryStream);
                if (directoryStreamNewDirectoryStream != null) {
                    directoryStreamNewDirectoryStream.close();
                }
                return immutableListCopyOf;
            } finally {
            }
        } catch (DirectoryIteratorException e) {
            throw e.getCause();
        }
    }

    @Deprecated
    public static TreeTraverser<Path> directoryTreeTraverser() {
        return DirectoryTreeTraverser.INSTANCE;
    }

    private static final class DirectoryTreeTraverser extends TreeTraverser<Path> {
        private static final DirectoryTreeTraverser INSTANCE = new DirectoryTreeTraverser();

        private DirectoryTreeTraverser() {
        }

        @Override // com.google.common.collect.TreeTraverser
        public Iterable<Path> children(Path path) {
            return MoreFiles.fileTreeChildren(path);
        }
    }

    public static Traverser<Path> fileTraverser() {
        return Traverser.forTree(FILE_TREE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Iterable<Path> fileTreeChildren(Path path) {
        if (java.nio.file.Files.isDirectory(path, LinkOption.NOFOLLOW_LINKS)) {
            try {
                return listFiles(path);
            } catch (IOException e) {
                throw new DirectoryIteratorException(e);
            }
        }
        return ImmutableList.of();
    }

    public static Predicate<Path> isDirectory(LinkOption... linkOptionArr) {
        final LinkOption[] linkOptionArr2 = (LinkOption[]) linkOptionArr.clone();
        return new Predicate<Path>() { // from class: com.google.common.io.MoreFiles.2
            @Override // com.google.common.base.Predicate, java.util.function.Predicate
            public /* synthetic */ boolean test(Object obj) {
                return apply((AnonymousClass2) obj);
            }

            @Override // com.google.common.base.Predicate
            public boolean apply(Path path) {
                return java.nio.file.Files.isDirectory(path, linkOptionArr2);
            }

            public String toString() {
                return "MoreFiles.isDirectory(" + Arrays.toString(linkOptionArr2) + ")";
            }
        };
    }

    private static boolean isDirectory(SecureDirectoryStream<Path> secureDirectoryStream, Path path, LinkOption... linkOptionArr) throws IOException {
        return ((BasicFileAttributeView) secureDirectoryStream.getFileAttributeView(path, BasicFileAttributeView.class, linkOptionArr)).readAttributes().isDirectory();
    }

    public static Predicate<Path> isRegularFile(LinkOption... linkOptionArr) {
        final LinkOption[] linkOptionArr2 = (LinkOption[]) linkOptionArr.clone();
        return new Predicate<Path>() { // from class: com.google.common.io.MoreFiles.3
            @Override // com.google.common.base.Predicate, java.util.function.Predicate
            public /* synthetic */ boolean test(Object obj) {
                return apply((AnonymousClass3) obj);
            }

            @Override // com.google.common.base.Predicate
            public boolean apply(Path path) {
                return java.nio.file.Files.isRegularFile(path, linkOptionArr2);
            }

            public String toString() {
                return "MoreFiles.isRegularFile(" + Arrays.toString(linkOptionArr2) + ")";
            }
        };
    }

    public static boolean equal(Path path, Path path2) throws IOException {
        Preconditions.checkNotNull(path);
        Preconditions.checkNotNull(path2);
        if (java.nio.file.Files.isSameFile(path, path2)) {
            return true;
        }
        ByteSource byteSourceAsByteSource = asByteSource(path, new OpenOption[0]);
        ByteSource byteSourceAsByteSource2 = asByteSource(path2, new OpenOption[0]);
        long jLongValue = byteSourceAsByteSource.sizeIfKnown().or((Optional<Long>) 0L).longValue();
        long jLongValue2 = byteSourceAsByteSource2.sizeIfKnown().or((Optional<Long>) 0L).longValue();
        if (jLongValue == 0 || jLongValue2 == 0 || jLongValue == jLongValue2) {
            return byteSourceAsByteSource.contentEquals(byteSourceAsByteSource2);
        }
        return false;
    }

    public static void touch(Path path) throws IOException {
        Preconditions.checkNotNull(path);
        try {
            java.nio.file.Files.setLastModifiedTime(path, FileTime.fromMillis(System.currentTimeMillis()));
        } catch (NoSuchFileException unused) {
            try {
                java.nio.file.Files.createFile(path, new FileAttribute[0]);
            } catch (FileAlreadyExistsException unused2) {
            }
        }
    }

    public static void createParentDirectories(Path path, FileAttribute<?>... fileAttributeArr) throws IOException {
        Path parent = path.toAbsolutePath().normalize().getParent();
        if (parent == null || java.nio.file.Files.isDirectory(parent, new LinkOption[0])) {
            return;
        }
        java.nio.file.Files.createDirectories(parent, fileAttributeArr);
        if (java.nio.file.Files.isDirectory(parent, new LinkOption[0])) {
            return;
        }
        throw new IOException("Unable to create parent directories of " + path);
    }

    public static String getFileExtension(Path path) {
        String string;
        int iLastIndexOf;
        Path fileName = path.getFileName();
        return (fileName == null || (iLastIndexOf = (string = fileName.toString()).lastIndexOf(46)) == -1) ? "" : string.substring(iLastIndexOf + 1);
    }

    public static String getNameWithoutExtension(Path path) {
        Path fileName = path.getFileName();
        if (fileName == null) {
            return "";
        }
        String string = fileName.toString();
        int iLastIndexOf = string.lastIndexOf(46);
        return iLastIndexOf == -1 ? string : string.substring(0, iLastIndexOf);
    }

    public static void deleteRecursively(Path path, RecursiveDeleteOption... recursiveDeleteOptionArr) throws IOException {
        Path parentPath = getParentPath(path);
        Collection<IOException> collectionDeleteRecursivelyInsecure = null;
        if (parentPath == null) {
            throw new FileSystemException(path.toString(), null, "can't delete recursively");
        }
        boolean z = false;
        try {
            DirectoryStream<Path> directoryStreamNewDirectoryStream = java.nio.file.Files.newDirectoryStream(parentPath);
            try {
                if (directoryStreamNewDirectoryStream instanceof SecureDirectoryStream) {
                    z = true;
                    collectionDeleteRecursivelyInsecure = deleteRecursivelySecure((SecureDirectoryStream) directoryStreamNewDirectoryStream, path.getFileName());
                }
                if (directoryStreamNewDirectoryStream != null) {
                    directoryStreamNewDirectoryStream.close();
                }
                if (!z) {
                    checkAllowsInsecure(path, recursiveDeleteOptionArr);
                    collectionDeleteRecursivelyInsecure = deleteRecursivelyInsecure(path);
                }
            } finally {
            }
        } catch (IOException e) {
            if (collectionDeleteRecursivelyInsecure == null) {
                throw e;
            }
            collectionDeleteRecursivelyInsecure.add(e);
        }
        if (collectionDeleteRecursivelyInsecure != null) {
            throwDeleteFailed(path, collectionDeleteRecursivelyInsecure);
        }
    }

    public static void deleteDirectoryContents(Path path, RecursiveDeleteOption... recursiveDeleteOptionArr) throws IOException {
        Collection<IOException> collectionDeleteDirectoryContentsInsecure;
        Collection<IOException> collection = null;
        try {
            DirectoryStream<Path> directoryStreamNewDirectoryStream = java.nio.file.Files.newDirectoryStream(path);
            try {
                if (directoryStreamNewDirectoryStream instanceof SecureDirectoryStream) {
                    collectionDeleteDirectoryContentsInsecure = deleteDirectoryContentsSecure((SecureDirectoryStream) directoryStreamNewDirectoryStream);
                } else {
                    checkAllowsInsecure(path, recursiveDeleteOptionArr);
                    collectionDeleteDirectoryContentsInsecure = deleteDirectoryContentsInsecure(directoryStreamNewDirectoryStream);
                }
                collection = collectionDeleteDirectoryContentsInsecure;
                if (directoryStreamNewDirectoryStream != null) {
                    directoryStreamNewDirectoryStream.close();
                }
            } finally {
            }
        } catch (IOException e) {
            if (0 == 0) {
                throw e;
            }
            collection.add(e);
        }
        if (collection != null) {
            throwDeleteFailed(path, collection);
        }
    }

    @NullableDecl
    private static Collection<IOException> deleteRecursivelySecure(SecureDirectoryStream<Path> secureDirectoryStream, Path path) throws IOException {
        Collection<IOException> collectionDeleteDirectoryContentsSecure = null;
        try {
            if (isDirectory(secureDirectoryStream, path, LinkOption.NOFOLLOW_LINKS)) {
                SecureDirectoryStream<Path> secureDirectoryStreamNewDirectoryStream = secureDirectoryStream.newDirectoryStream(path, LinkOption.NOFOLLOW_LINKS);
                try {
                    collectionDeleteDirectoryContentsSecure = deleteDirectoryContentsSecure(secureDirectoryStreamNewDirectoryStream);
                    if (secureDirectoryStreamNewDirectoryStream != null) {
                        secureDirectoryStreamNewDirectoryStream.close();
                    }
                    if (collectionDeleteDirectoryContentsSecure == null) {
                        secureDirectoryStream.deleteDirectory(path);
                    }
                } finally {
                }
            } else {
                secureDirectoryStream.deleteFile(path);
            }
            return collectionDeleteDirectoryContentsSecure;
        } catch (IOException e) {
            return addException(collectionDeleteDirectoryContentsSecure, e);
        }
    }

    @NullableDecl
    private static Collection<IOException> deleteDirectoryContentsSecure(SecureDirectoryStream<Path> secureDirectoryStream) {
        Collection<IOException> collectionConcat = null;
        try {
            Iterator<Path> it = secureDirectoryStream.iterator();
            while (it.hasNext()) {
                collectionConcat = concat(collectionConcat, deleteRecursivelySecure(secureDirectoryStream, it.next().getFileName()));
            }
            return collectionConcat;
        } catch (DirectoryIteratorException e) {
            return addException(collectionConcat, e.getCause());
        }
    }

    @NullableDecl
    private static Collection<IOException> deleteRecursivelyInsecure(Path path) throws IOException {
        Collection<IOException> collectionDeleteDirectoryContentsInsecure = null;
        try {
            if (java.nio.file.Files.isDirectory(path, LinkOption.NOFOLLOW_LINKS)) {
                DirectoryStream<Path> directoryStreamNewDirectoryStream = java.nio.file.Files.newDirectoryStream(path);
                try {
                    collectionDeleteDirectoryContentsInsecure = deleteDirectoryContentsInsecure(directoryStreamNewDirectoryStream);
                    if (directoryStreamNewDirectoryStream != null) {
                        directoryStreamNewDirectoryStream.close();
                    }
                } finally {
                }
            }
            if (collectionDeleteDirectoryContentsInsecure == null) {
                java.nio.file.Files.delete(path);
            }
            return collectionDeleteDirectoryContentsInsecure;
        } catch (IOException e) {
            return addException(collectionDeleteDirectoryContentsInsecure, e);
        }
    }

    @NullableDecl
    private static Collection<IOException> deleteDirectoryContentsInsecure(DirectoryStream<Path> directoryStream) {
        Collection<IOException> collectionConcat = null;
        try {
            Iterator<Path> it = directoryStream.iterator();
            while (it.hasNext()) {
                collectionConcat = concat(collectionConcat, deleteRecursivelyInsecure(it.next()));
            }
            return collectionConcat;
        } catch (DirectoryIteratorException e) {
            return addException(collectionConcat, e.getCause());
        }
    }

    @NullableDecl
    private static Path getParentPath(Path path) {
        Path parent = path.getParent();
        if (parent != null) {
            return parent;
        }
        if (path.getNameCount() == 0) {
            return null;
        }
        return path.getFileSystem().getPath(".", new String[0]);
    }

    private static void checkAllowsInsecure(Path path, RecursiveDeleteOption[] recursiveDeleteOptionArr) throws InsecureRecursiveDeleteException {
        if (!Arrays.asList(recursiveDeleteOptionArr).contains(RecursiveDeleteOption.ALLOW_INSECURE)) {
            throw new InsecureRecursiveDeleteException(path.toString());
        }
    }

    private static Collection<IOException> addException(@NullableDecl Collection<IOException> collection, IOException iOException) {
        if (collection == null) {
            collection = new ArrayList<>();
        }
        collection.add(iOException);
        return collection;
    }

    @NullableDecl
    private static Collection<IOException> concat(@NullableDecl Collection<IOException> collection, @NullableDecl Collection<IOException> collection2) {
        if (collection == null) {
            return collection2;
        }
        if (collection2 != null) {
            collection.addAll(collection2);
        }
        return collection;
    }

    private static void throwDeleteFailed(Path path, Collection<IOException> collection) throws FileSystemException {
        FileSystemException fileSystemException = new FileSystemException(path.toString(), null, "failed to delete one or more files; see suppressed exceptions for details");
        Iterator<IOException> it = collection.iterator();
        while (it.hasNext()) {
            fileSystemException.addSuppressed(it.next());
        }
        throw fileSystemException;
    }
}
