package com.runjar.cli;

public class Arguments {

    private final ProgramMode mode;
    private final String artifact;
    private final boolean verbose;

    public Arguments(ProgramMode mode, boolean verbose, String artifact) {
        this.mode = mode;
        this.artifact = artifact;
        this.verbose = verbose;
    }

    public Arguments(ProgramMode mode, boolean verbose) {
        this(mode,verbose, null);
    }

    public boolean isVerbose() {
        return this.verbose;
    }

    public String getArtifact() {
        return artifact;
    }

    public ProgramMode getMode() {
        return mode;
    }

    @Override
    public String toString() {
        return String.format("%s: %s", this.mode, this.artifact);
    }

    public boolean isMode(ProgramMode mode) {
        return this.mode == mode;
    }
}
