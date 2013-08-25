package getlogin;

import jnr.ffi.LibraryLoader;

/**
 * Gets the login name of the current user
 */
public class Getlogin {
    public interface LibC  {
        public String getlogin();
    }

    public static void main( String[] args ) {
        LibC libc = LibraryLoader.create(LibC.class).load("c");

        System.out.println("login=" + libc.getlogin());
    }
}
