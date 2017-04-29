/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gojek.commands;

import com.gojek.core.SlotAllocator;
import com.gojek.entities.Car;
import com.gojek.entities.Slot;
import com.gojek.exceptions.NoFreeSlotsAvailable;

/**
 *
 * @author nimeshagarwal
 */
public class ParkCommand implements Command {

    private final String registrationNumber;
    private final String color;

    public ParkCommand(String registrationNumber, String color) {
        this.registrationNumber = registrationNumber;
        this.color = color;
    }

    @Override
    public String execute() {
        try {
            SlotAllocator allocator = SlotAllocator.getInstance();
            Slot slot = allocator.park(new Car(registrationNumber, color));
            return "Allocated slot number: " + slot.getSlotNumber();
        } catch (NoFreeSlotsAvailable ex) {
            return "Sorry, parking lot is full";
        }
    }

}
