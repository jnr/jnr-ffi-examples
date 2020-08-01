package uname;

import jnr.ffi.LibraryLoader;
import jnr.ffi.Runtime;
import jnr.ffi.Struct;
import jnr.ffi.annotations.Out;
import jnr.ffi.annotations.Transient;

public class GetUname {
	private static final int _UTS_NAME_LENGTH_ = 65;

	/*
	 *
	 * struct utsname {
         *     char sysname[];    // Operating system name (e.g., "Linux")
         *     char nodename[];   // Name within "some implementation-defined network"
         *     char release[];    // Operating system release (e.g., "2.6.28")
         *     char version[];    // Operating system version
         *     char machine[];    // Hardware identifier
         * #ifdef _GNU_SOURCE
         *     char domainname[]; // NIS or YP domain name
         *  #endif
         *  };
	 *
	 * */
	static final class UtsNameStruct extends Struct {
        	public final String sysname = new AsciiString(_UTS_NAME_LENGTH_);
        	public final String nodename = new AsciiString(_UTS_NAME_LENGTH_);
        	public final String release = new AsciiString(_UTS_NAME_LENGTH_);
        	public final String version = new AsciiString(_UTS_NAME_LENGTH_);
        	public final String machine = new AsciiString(_UTS_NAME_LENGTH_);

        	public UtsNameStruct(Runtime runtime) {
            		super(runtime);
       		}
    	}

	public interface LibC  {
		public int uname(@Out @Transient UtsNameStruct nameStruct);
	}

	public static void main(String[] args) {
		LibC libc = LibraryLoader.create(LibC.class).load("libc.so.6");
		Runtime runtime = Runtime.getRuntime(libc);
		UtsNameStruct nameStruct = new UtsNameStruct(runtime);
		System.out.println(libc.uname(nameStruct));
		System.out.println(nameStruct.nodename);
		System.out.println(nameStruct.sysname);
		System.out.println(nameStruct.release);
		System.out.println(nameStruct.version);
		System.out.println(nameStruct.machine);
	}
}
