package com.runjar.cli;


import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;

import java.util.ArrayList;
import java.util.List;

public class JCommanderArgumentsParser implements ArgumentsParser {

    private static class Args {

        @Parameter(names = {"-i", "--info"}, required = false, description = "Do not run the application. Just show information")
        private boolean infoMode;

        @Parameter(names = {"-v", "--verbose"}, required = false, description = "Print more details about whats going on")
        private boolean verbose;

        @Parameter(names = {"-h", "--help"}, required = false, description = "Show the commandline option help", help = true)
        private boolean helpMode;

        @Parameter(description = "artifact", required = true)
        private List<String> parameters = new ArrayList<String>();


    }

    @Override
    public Arguments parse(String[] args) throws ArgumentsParseException {


        try {
            Args argdata = new Args();
            new JCommander(argdata, args);

            if (argdata.helpMode) {
                return new Arguments(ProgramMode.HELP, argdata.verbose);
            }

            String artifact = argdata.parameters.get(0);

            if (argdata.infoMode) {
                return new Arguments(ProgramMode.INFO, argdata.verbose, artifact);
            }

            return new Arguments(ProgramMode.RUN, argdata.verbose, artifact);
        } catch (ParameterException e) {
            throw new ArgumentsParseException(e.getMessage());
        }
    }

    @Override
    public String usage(String programName) {

        JCommander jCommander = new JCommander(new Args(), new String[] {"-h"});
        jCommander.setProgramName(programName);
        StringBuilder out = new StringBuilder();
        jCommander.usage(out);
        return out.toString();
    }

}
