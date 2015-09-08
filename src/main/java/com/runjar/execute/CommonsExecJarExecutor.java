package com.runjar.execute;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class CommonsExecJarExecutor implements JarExecutor {

    @Override
    public void execute(final File jarFile) {
        try {

            CommandLine cmdline = new CommandLine("java");
            cmdline.addArgument("-jar");
            cmdline.setSubstitutionMap(new HashMap<String, Object>() {{
                put("file", jarFile);
            }});
            cmdline.addArgument("${file}");

            System.out.println("executing: " + cmdline);
            DefaultExecutor executor = new DefaultExecutor();
            int exitValue = executor.execute(cmdline);

            if (exitValue > 0) {
                throw new ExecutionException(exitValue, "return code > 0");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
