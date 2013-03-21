package gettimeofday;

import jnr.ffi.*;
import jnr.ffi.Runtime;
import jnr.ffi.annotations.Out;
import jnr.ffi.annotations.Transient;
import jnr.ffi.provider.FFIProvider;

/**
 * Hello world!
 */
public class Gettimeofday {
    public static final class Timeval extends Struct {
        public final SignedLong tv_sec = new SignedLong();
        public final SignedLong tv_usec = new SignedLong();

        public Timeval(Runtime runtime) {
            super(runtime);
        }
    }

    public interface LibC {
        public int gettimeofday(@Out @Transient Timeval tv, Pointer unused);
    }

    public static void main(String[] args) {

        LibraryLoader<LibC> loader = (FFIProvider.getSystemProvider()).createLibraryLoader(LibC.class);
        LibC libc = loader.load("c");

        Runtime runtime = Runtime.getRuntime(libc);

        Timeval tv = new Timeval(runtime);
        libc.gettimeofday(tv, null);
        System.out.println("gettimeofday tv.tv_sec=" + tv.tv_sec.get() + " tv.tv_usec=" + tv.tv_usec.get());
    }
}
