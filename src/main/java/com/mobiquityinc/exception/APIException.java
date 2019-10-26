package com.mobiquityinc.exception;

public class APIException extends Exception {
    private String line;
    private long lineNumber;

	public APIException() {
		super();
	}
	
    public APIException(Throwable cause, String line, long lineNumber) {
        super(cause);
        this.line = line;
        this.lineNumber = lineNumber;
    }

    public APIException(String message, String line, long lineNumber) {
        super(message);
        this.line = line;
        this.lineNumber = lineNumber;
    }

    public APIException(String message, Throwable cause, String line, long lineNumber) {
        super(message, cause);
        this.line = line;
        this.lineNumber = lineNumber;
    }

	public APIException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public APIException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public APIException(String message, Exception e) {
		super(message, e);
	}

	public APIException(String message) {
		super(message);
	}

	public APIException(Throwable cause) {
		super(cause);
	}


}
