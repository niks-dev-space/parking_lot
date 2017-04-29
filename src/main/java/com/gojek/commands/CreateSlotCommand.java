/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gojek.commands;

import com.gojek.core.SlotAllocator;

/**
 *
 * @author nimeshagarwal
 */
public class CreateSlotCommand implements Command {

    private final Integer numberOfSlots;

    public CreateSlotCommand(Integer numberOfSlots) {
        this.numberOfSlots = numberOfSlots;
    }

    @Override
    public String execute() {
        SlotAllocator allocator = SlotAllocator.getInstance();
        allocator.createSlots(numberOfSlots);
        return "Created a parking lot with " + numberOfSlots + " slots";
    }

}
