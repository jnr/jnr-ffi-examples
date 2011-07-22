package getlogin;

import jnr.ffi.Library;

/**
 * 
 */
public class Getlogin {
    public interface LibC  {
        public String getlogin();
    }

    public static void main( String[] args )
    {
        LibC libc = Library.loadLibrary("c", LibC.class);

        System.out.println("login=" + libc.getlogin());
    }
}
