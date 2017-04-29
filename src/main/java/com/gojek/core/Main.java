package com.gojek.core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.gojek.exceptions.InvalidCommand;
import com.gojek.exceptions.NoFreeSlotsAvailable;
import com.gojek.exceptions.SlotIsNotYetOccupied;

public class Main {

	private static CommandExecutor commandExecutor = new CommandExecutor();

	public static void main(String[] arg) throws InvalidCommand, NoFreeSlotsAvailable, SlotIsNotYetOccupied {
		// If file then fetch all the commands from file and execute
		if (arg!= null && arg.length > 0 && isFile(arg[0])) {
			List<String> commands = getCommands(arg[0]);
			for (String command : commands) {
				commandExecutor.execute(command);
			}
			return;
		}

		// else start the CLI
		System.out.println("welcome to parking lot program. Type 'bye' to exit; 'help' for help.");

		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		while (true) {
			String line = reader.nextLine();
			if (isExit(line)) {
				break;
			} else if (isHelp(line)) {
				printHelp();
			} else {
				commandExecutor.execute(line);
			}
		}

		//Exit
		System.out.println("thank you for using parking lot program.");
	}

	private static List<String> getCommands(String line) throws InvalidCommand {
		System.out.println("parsing file " + line);
		String fileName = line;
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			List<String> commands = br.lines().collect(Collectors.toList());
			return commands;
		} catch (IOException e) {
			throw new InvalidCommand("unable to process file; error:" + e);
		}
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

	private static boolean isFile(String line) {
		return line.endsWith(".txt");
	}
}