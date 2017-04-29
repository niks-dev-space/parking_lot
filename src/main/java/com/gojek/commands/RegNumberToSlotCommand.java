/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gojek.commands;

import com.gojek.core.SlotAllocator;
import com.gojek.entities.Slot;

/**
 *
 * @author nimeshagarwal
 */
public class RegNumberToSlotCommand implements Command {

    private final String registerNumber;

    public RegNumberToSlotCommand(String color) {
        this.registerNumber = color;
    }

    @Override
    public String execute() {
        SlotAllocator allocator = SlotAllocator.getInstance();
        Slot slot = allocator.getSlot(registerNumber);
        if (slot != null) {
            return slot.getSlotNumber().toString();
        } else {
            return "Not Found";
        }
    }

}
