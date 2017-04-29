/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gojek.core;

import com.gojek.exceptions.InvalidCommand;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nimeshagarwal
 */
public class CommandLineExecutor {

    private final CommandExecutor commandExecutor;

    public CommandLineExecutor(CommandExecutor commandExecutor) {
        this.commandExecutor = commandExecutor;
    }

    public void start() {
        // start the CLI
        System.out.println("welcome to parking lot program. Type 'bye' to exit; 'help' for help.");

        Scanner reader = new Scanner(System.in);
        while (true) {
            String line = reader.nextLine();
            if (isExit(line)) {
                break;
            } else if (isHelp(line)) {
                printHelp();
            } else {
                try {
                    String result = commandExecutor.execute(line);
                    System.out.println(result);
                } catch (InvalidCommand ex) {
                    System.out.println("invalid command");
                }                
            }
        }

        //Exit
        System.out.println("thank you for using parking lot program.");
    }

    private static void printHelp() {
        System.out.println("------------------------------");
        System.out.println("type 'create_parking_lot <number>' to create car parking slots");
        System.out.println("type 'park <reg-number> <color>' to park car");
        System.out.println("type 'leave <slot-number>' to take out car at slot-number");
        System.out.println("type 'status' to print complete status");
        System.out.println("type 'registration_numbers_for_cars_with_colour <color>' to print reg. numbers by color");
        System.out.println("type 'slot_numbers_for_cars_with_colour <color>' to print slot numbers by color");
        System.out.println(
                "type 'slot_number_for_registration_number <reg. number>' to print slot number by reg. number");

        System.out.println("------------------------------");
    }

    private static boolean isHelp(String line) {
        return "help".equalsIgnoreCase(line);
    }

    private static boolean isExit(String line) {
        return "bye".equalsIgnoreCase(line) || "exit".equalsIgnoreCase(line);
    }
}
