package com.gojek.core;

import com.gojek.commands.Command;
import com.gojek.commands.CreateSlotCommand;
import com.gojek.commands.LeaveCommand;
import com.gojek.commands.ParkCommand;
import com.gojek.commands.RegNumberToSlotCommand;
import com.gojek.commands.ReportCarByColorCommand;
import com.gojek.commands.ReportSlotsByColorCommand;
import com.gojek.commands.StatusCommand;
import com.gojek.exceptions.InvalidCommand;

public class CommandExecutor {

    public String execute(String line) throws InvalidCommand {
        Command command = createCommand(line);
        return command.execute();
    }

    private Command createCommand(String line) throws InvalidCommand {

        if (line == null || line.isEmpty()) {
            throw new InvalidCommand("invalid command, " + line);
        }

        String[] tokens = line.split(" ");
        Command command = null;
        if (line.startsWith("create_parking_lot")) {
            int numberOfSlots = Integer.parseInt(tokens[1]);
            command = new CreateSlotCommand(numberOfSlots);
        } else if (line.startsWith("park")) {
            String registrationNumber = tokens[1];
            String color = tokens[2];
            command = new ParkCommand(registrationNumber, color);
        } else if (line.startsWith("leave")) {
            Integer slotNumber = Integer.parseInt(tokens[1]);
            command = new LeaveCommand(slotNumber);
        } else if (line.startsWith("status")) {
            command = new StatusCommand();
        } else if (line.startsWith("registration_numbers_for_cars_with_colour")) {
            String color = tokens[1];
            command = new ReportCarByColorCommand(color);
        } else if (line.startsWith("slot_numbers_for_cars_with_colour")) {
            String color = tokens[1];
            command = new ReportSlotsByColorCommand(color);
        } else if (line.startsWith("slot_number_for_registration_number")) {
            String regNumber = tokens[1];
            command = new RegNumberToSlotCommand(regNumber);
        }

        if (command == null) {
            throw new InvalidCommand("invalid command");
        }
        return command;
    }
}
