package com.runjar.execute;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;

import java.io.File;
import java.io.IOException;

public class CommonsExecJarExecutor implements JarExecutor {

    @Override
    public void execute(File jarFile) {
        try {
            
            CommandLine cmdLine = CommandLine.parse(String.format("java -jar '%s'", jarFile.getAbsolutePath()));
            System.out.println("executing: " + cmdLine);
            DefaultExecutor executor = new DefaultExecutor();
            int exitValue = executor.execute(cmdLine);

            if (exitValue > 0) {
                throw new ExecutionException(exitValue, "return code > 0");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
