package com.runjar.cli;

public interface ArgumentsParser {

    Arguments parse(String[] args) throws ArgumentsParseException;

    String usage(String programName);

}
