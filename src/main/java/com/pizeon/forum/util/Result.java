package com.pizeon.forum.util;

public class Result {
	
	public static final Result SUCCESS = new Result(true);
	public static final Result FAIL = new Result(false);
	
	private boolean valid;
	
	public Result(boolean valid) {
		this.valid = valid;
	}
	
	public boolean isValid() {
		return valid;
	}
	
}
