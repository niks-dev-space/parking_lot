/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gojek.commands;

import com.gojek.core.SlotAllocator;
import com.gojek.entities.Slot;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author nimeshagarwal
 */
public class ReportCarByColorCommand implements Command {

    private final String color;

    public ReportCarByColorCommand(String color) {
        this.color = color;
    }

    @Override
    public String execute() {
        SlotAllocator allocator = SlotAllocator.getInstance();
        List<Slot> slots = allocator.getSlots(color);
        String regNumbers = "";
        if (slots != null) {
            regNumbers = slots.stream().map(slot -> slot.getCar().getRegistrationNumber())
                    .collect(Collectors.joining(", "));
        }
        return regNumbers;
    }

}
