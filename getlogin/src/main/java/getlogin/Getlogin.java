package getlogin;

import jnr.ffi.LibraryLoader;
import jnr.ffi.provider.FFIProvider;

/**
 *
 */
public class Getlogin {
    public interface LibC {
        public String getlogin();
    }

    public static void main(String[] args) {
        LibraryLoader<LibC> loader = (FFIProvider.getSystemProvider()).createLibraryLoader(LibC.class);
        LibC libc = loader.load("c");

        System.out.println("login=" + libc.getlogin());
    }
}
