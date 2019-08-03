package com.sec.util;

public class Debugger {
	private static boolean valid = false;
	
	public static void setValid() {
		Debugger.valid = true;
	}

	public static void setInvalid() {
		Debugger.valid = false;
	}
	
	public static void log(String log) {
		if(Debugger.valid)
			System.err.println(log);
	}
	public static void log(Object log) {
		if(Debugger.valid)
			System.err.println(log);
	}
	public static void log() {
		if(Debugger.valid)
			System.err.println();
	}

}