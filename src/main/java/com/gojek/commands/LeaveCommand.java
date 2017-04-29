/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gojek.commands;

import com.gojek.core.SlotAllocator;
import com.gojek.exceptions.SlotIsNotYetOccupied;

/**
 *
 * @author nimeshagarwal
 */
public class LeaveCommand implements Command {

    private final Integer slotNumber;

    public LeaveCommand(Integer slotNumber) {
        this.slotNumber = slotNumber;
    }

    @Override
    public String execute() {
        try {
            SlotAllocator allocator = SlotAllocator.getInstance();
            allocator.leave(slotNumber);
            return "Slot number " + slotNumber + " is free";
        } catch (SlotIsNotYetOccupied ex) {
            return "Slot is already free";
        }
    }

}
