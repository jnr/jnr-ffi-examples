package getpid;

import jnr.ffi.*;
import jnr.ffi.types.pid_t;

/**
 * Gets the process ID of the current process, and that of its parent.
 */
public class Getpid {
    public interface LibC  {
        public @pid_t long getpid();
        public @pid_t long getppid();
    }

    public static void main(String[] args) {
        LibC libc = LibraryLoader.create(LibC.class).load("c");

        System.out.println("pid=" + libc.getpid() + " parent pid=" + libc.getppid());
    }
}
