package com.gojek.core;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import com.gojek.entities.Car;
import com.gojek.entities.Slot;
import com.gojek.exceptions.NoFreeSlotsAvailable;
import com.gojek.exceptions.SlotIsNotYetOccupied;

public class SlotAllocator {

	private final PriorityQueue<Slot> freeSlots;
	private final Map<Integer, Slot> occupiedSlots; // slotNumber -> slot
	private final Map<String, List<Slot>> colorsToSlots;
	private final Map<String, Slot> regToSlot;

	public SlotAllocator() {
		this.freeSlots = new PriorityQueue<Slot>();
		this.occupiedSlots = new HashMap<Integer, Slot>();
		this.colorsToSlots = new HashMap<String, List<Slot>>();
		this.regToSlot = new HashMap<String, Slot>();
	}

	public void createSlots(int numberOfSlots) {
		for (int i = 1; i <= numberOfSlots; i++) {
			freeSlots.add(new Slot(i));
		}
	}

	public Slot park(Car car) throws NoFreeSlotsAvailable {
		Slot slot = freeSlots.poll();

		if (slot == null) {
			throw new NoFreeSlotsAvailable("all slots all occupied");
		}

		slot.setCar(car);
		slot.setIsFree(false);

		if (!colorsToSlots.containsKey(car.getColor())) {
			colorsToSlots.put(car.getColor(), new LinkedList<Slot>());
		}
		colorsToSlots.get(car.getColor()).add(slot);

		regToSlot.put(car.getRegistrationNumber(), slot);

		occupiedSlots.put(slot.getSlotNumber(), slot);
		
		return slot;
	}

	public Car leave(Integer slotNumber) throws SlotIsNotYetOccupied {
		Slot slot = occupiedSlots.get(slotNumber);
		if (slot == null) {
			throw new SlotIsNotYetOccupied("slot is currently free");
		}

		Car car = slot.getCar();

		occupiedSlots.remove(slotNumber);
		slot.setCar(null);
		slot.setIsFree(true);
		freeSlots.add(slot);

		if (colorsToSlots.containsKey(car.getColor())) {
			colorsToSlots.get(car.getColor()).remove(slot);
		}

		regToSlot.remove(car.getRegistrationNumber());

		return car;
	}

	public List<Slot> getSlots(String color) {
		return colorsToSlots.get(color);
	}

	public Slot getSlot(String registrationNumber) {
		return regToSlot.get(registrationNumber);
	}

	public void status() {
		System.out.println("Slot Number \t Registration Number \t Color");
		for (Integer slotNumber : occupiedSlots.keySet()) {
			Car car = occupiedSlots.get(slotNumber).getCar();
			System.out.println(slotNumber + "\t" + car.getRegistrationNumber() + "\t" + car.getColor());
		}
	}
}