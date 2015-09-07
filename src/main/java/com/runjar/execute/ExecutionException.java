package com.runjar.execute;


public class ExecutionException extends RuntimeException {

    private final int exitCode;

    public ExecutionException(int exitCode, String message, Throwable cause) {
        super(message, cause);
        this.exitCode = exitCode;
    }

    public ExecutionException(int exitCode, String message) {
        super(message);
        this.exitCode = exitCode;
    }

    public int getExitCode() {
        return exitCode;
    }
}
