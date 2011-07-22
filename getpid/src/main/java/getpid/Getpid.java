package getpid;

import jnr.ffi.*;

/**
 * Hello world!
 *
 */
public class Getpid {
    public interface LibC  {
        public long getpid();
        public long getppid();
    }

    public static void main( String[] args )
    {
        LibC libc = Library.loadLibrary("c", LibC.class);

        System.out.println("pid=" + libc.getpid() + " parent pid=" + libc.getppid());
    }
}
