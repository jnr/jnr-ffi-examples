package gettimeofday;

import jnr.ffi.*;
import jnr.ffi.Runtime;
import jnr.ffi.annotations.Out;
import jnr.ffi.annotations.Transient;

/**
 * Retrieves the current system time using gettimeofday(3)
 */
public class Gettimeofday {
    public static final class Timeval extends Struct {
        public final time_t tv_sec = new time_t();
        public final SignedLong tv_usec = new SignedLong();

        public Timeval(Runtime runtime) {
            super(runtime);
        }
    }

    public interface LibC  {
        public int gettimeofday(@Out @Transient Timeval tv, Pointer unused);
    }

    public static void main( String[] args ) {
        LibC libc = LibraryLoader.create(LibC.class).load("c");
        Runtime runtime = Runtime.getRuntime(libc);

        Timeval tv = new Timeval(runtime);
        libc.gettimeofday(tv, null);
        System.out.println("gettimeofday tv.tv_sec=" + tv.tv_sec.get() + " tv.tv_usec=" + tv.tv_usec.get());
    }
}
