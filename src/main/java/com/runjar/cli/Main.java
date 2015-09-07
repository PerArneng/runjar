package com.runjar.cli;

import com.runjar.execute.CommonsExecJarExecutor;
import com.runjar.execute.JarExecutor;

import java.io.File;

public class Main {

    public static void main(String[] args) {

        System.out.println("runjar: runjar v0.1");

        if (args.length < 1) {
            System.err.println("runjar: need artifact as argument");
            System.exit(1);
        }

        File artifact = new File(args[0]);
        if (!artifact.exists()) {
            System.err.println("runjar: file does not exist " + args[0]);
            System.exit(1);
        }

        JarExecutor executor = new CommonsExecJarExecutor();
        executor.execute(new File(args[0]));

    }

}
