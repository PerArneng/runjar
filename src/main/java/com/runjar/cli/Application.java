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
public class Application {

    private static ApplicationContext applicationContext = null;

    @Bean
    public ArgumentsParser argumentsParser() {
        return new JCommanderArgumentsParser();
    }

    @Bean
    public ManifestInfoReader manifestInfoReader() {
        return new ManifestInfoReader();
    }

    @Bean
    public CommandLineRunner commandLineRunner(ArgumentsParser argumentsParser,
                                               ManifestInfoReader manifestInfoReader) {
        return new RunJarCommandLineRunner(argumentsParser, manifestInfoReader);
    }

    public static void main(String[] args) {
        final SpringApplication app = new SpringApplication(Application.class);
        app.setShowBanner(false);
        applicationContext = app.run(args);
    }


}
