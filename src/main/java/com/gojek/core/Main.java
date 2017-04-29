package com.gojek.core;

import java.util.List;
import java.util.Scanner;

import com.gojek.exceptions.InvalidCommand;
import com.gojek.exceptions.NoFreeSlotsAvailable;
import com.gojek.exceptions.SlotIsNotYetOccupied;

public class Main {

    public static void main(String[] arg) throws InvalidCommand, NoFreeSlotsAvailable, SlotIsNotYetOccupied {
        Boolean isFileMode = arg != null && arg.length > 0 && arg[0].endsWith(".txt");;

        CommandExecutor commandExecutor = new CommandExecutor();
        if (isFileMode) {
            List<String> commands = CommandFileReader.read(arg[0]);
            for (String command : commands) {
                String result = commandExecutor.execute(command);
                System.out.println(result);
            }
        } else {
            CommandLineExecutor executor = new CommandLineExecutor(commandExecutor);
            executor.start();
        }
    }
}
