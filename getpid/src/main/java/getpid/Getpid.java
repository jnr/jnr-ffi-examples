package getpid;

import jnr.ffi.LibraryLoader;
import jnr.ffi.provider.FFIProvider;

/**
 * Hello world!
 */
public class Getpid {
    public interface LibC {
        public long getpid();

        public long getppid();
    }

    public static void main(String[] args) {
        LibraryLoader<LibC> loader = (FFIProvider.getSystemProvider()).createLibraryLoader(LibC.class);
        LibC libc = loader.load("c");

        System.out.println("pid=" + libc.getpid() + " parent pid=" + libc.getppid());
    }
}
