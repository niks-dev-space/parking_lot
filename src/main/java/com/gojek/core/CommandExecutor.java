package com.gojek.core;

import java.util.List;
import java.util.stream.Collectors;

import com.gojek.entities.Car;
import com.gojek.entities.Slot;
import com.gojek.exceptions.InvalidCommand;
import com.gojek.exceptions.NoFreeSlotsAvailable;
import com.gojek.exceptions.SlotIsNotYetOccupied;

public class CommandExecutor {

	private SlotAllocator allocator;

	public enum Action {
		create, park, leave, status, reportCarsByColor, reportSlotsByColor, slotNumberByRegNumber
	}

	public CommandExecutor() {
		this.allocator = new SlotAllocator();
	}

	/*
	 * Note: We can use Command object instead of String if we expect more
	 * functionalities like undo/redo to come in-future; We can use processor
	 * patter or template patter for each command but each processor does very
	 * little job so kept it simple
	 */
	public void execute(String command) throws InvalidCommand, SlotIsNotYetOccupied {
		Action action = getAction(command);
		String[] tokens = command.split(" ");

		if (Action.create.equals(action)) {
			int numberOfSlots = Integer.parseInt(tokens[1]);
			allocator.createSlots(numberOfSlots);
			System.out.println("Created a parking lot with " + numberOfSlots + " slots");

		} else if (Action.park.equals(action)) {
			String registrationNumber = tokens[1];
			String color = tokens[2];
			try {
				Slot slot = allocator.park(new Car(registrationNumber, color));
				System.out.println("Allocated slot number: " + slot.getSlotNumber());
			} catch (NoFreeSlotsAvailable ex) {
				System.out.println("Sorry, parking lot is full");
			}
		} else if (Action.leave.equals(action)) {
			Integer slotNumber = Integer.parseInt(tokens[1]);
			allocator.leave(slotNumber);
			System.out.println("Slot number " + slotNumber + " is free");
		} else if (Action.status.equals(action)) {
			allocator.status();
		} else if (Action.reportCarsByColor.equals(action)) {
			String color = tokens[1];
			List<Slot> slots = allocator.getSlots(color);
			String regNumbers = "";
			if (slots != null) {
				regNumbers = slots.stream().map(slot -> slot.getCar().getRegistrationNumber())
						.collect(Collectors.joining(", "));
			}
			System.out.println(regNumbers);
		} else if (Action.reportSlotsByColor.equals(action)) {
			String color = tokens[1];
			List<Slot> slots = allocator.getSlots(color);
			String slotNumbers = "";
			if (slots != null) {
				slotNumbers = slots.stream().map(slot -> slot.getSlotNumber().toString())
						.collect(Collectors.joining(", "));
			}

			System.out.println(slotNumbers);
		} else if (Action.slotNumberByRegNumber.equals(action)) {
			String regNumber = tokens[1];
			Slot slot = allocator.getSlot(regNumber);
			if (slot != null) {
				System.out.println(slot.getSlotNumber());
			} else {
				System.out.println("Not Found");
			}

		}
	}

	private Action getAction(String command) throws InvalidCommand {
		if (command == null || command.isEmpty()) {
			throw new InvalidCommand("invalid command, " + command);
		}

		if (command.startsWith("create_parking_lot")) {
			return Action.create;
		} else if (command.startsWith("park")) {
			return Action.park;
		} else if (command.startsWith("leave")) {
			return Action.leave;
		} else if (command.startsWith("status")) {
			return Action.status;
		} else if (command.startsWith("registration_numbers_for_cars_with_colour")) {
			return Action.reportCarsByColor;
		} else if (command.startsWith("slot_numbers_for_cars_with_colour")) {
			return Action.reportSlotsByColor;
		} else if (command.startsWith("slot_number_for_registration_number")) {
			return Action.slotNumberByRegNumber;
		}

		throw new InvalidCommand("invalid command");
	}
}
