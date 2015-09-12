package com.runjar.cli;

import com.runjar.execute.CommonsExecJarExecutor;
import com.runjar.execute.JarExecutor;
import com.runjar.io.ManifestInfoReader;
import com.runjar.model.ManifestInfo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
@ComponentScan
public class Main implements CommandLineRunner {

    private static ApplicationContext applicationContext = null;

    @Bean
    public CommandLineRunner commandLineRunner() {
        return Main.this;
    }

    public static void main(String[] args) {
        final SpringApplication app = new SpringApplication(Main.class);
        app.setShowBanner(false);
        applicationContext = app.run(args);
    }

    @Override
    public void run(String... args) throws Exception {

        Arguments arguments = null;

        ArgumentsParser argumentsParser = new JCommanderArgumentsParser();

        try {
            arguments = argumentsParser.parse(args);
        } catch (ArgumentsParseException e) {
            System.err.println("runjar: " + e.getMessage());
            System.err.println(argumentsParser.usage("runjar"));
            System.exit(1);
        }

        if (arguments.isMode(ProgramMode.HELP)) {
            System.out.println(argumentsParser.usage("runjar"));
            System.exit(0);
        }

        if (arguments.isMode(ProgramMode.INFO)) {
            System.out.println("info");
            System.exit(0);
        }

        if (arguments.isMode(ProgramMode.RUN)) {
            System.out.println("run");
            System.exit(0);
        }


        File artifact = new File(arguments.getArtifact());
        if (!artifact.exists()) {
            System.err.println("runjar: file does not exist " + args[0]);
            System.exit(1);
        }

        ManifestInfoReader manifestInfoReader = new ManifestInfoReader();
        ManifestInfo info = manifestInfoReader.read(artifact);

        if (info == null) {
            System.err.println("runjar: missing META-INF/MANIFEST.MF");
            System.exit(1);
        }

        if (!info.hasMainClass()) {
            System.err.println("runjar: missing Main-Class manifest info");
            System.exit(1);
        }

        System.out.println("runjar: Main-Class: "  + info.getMainClass());

        for (String key : info.getPropertyKeys()) {
            System.out.println(key + ":" + info.getValue(key));
        }

        JarExecutor executor = new CommonsExecJarExecutor();
        executor.execute(artifact);

    }

}
