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
public class ReportSlotsByColorCommand implements Command {

    private final String color;

    public ReportSlotsByColorCommand(String color) {
        this.color = color;
    }

    @Override
    public String execute() {
        SlotAllocator allocator = SlotAllocator.getInstance();
        List<Slot> slots = allocator.getSlots(color);
        String slotNumbers = "";
        if (slots != null) {
            slotNumbers = slots.stream().map(slot -> slot.getSlotNumber().toString())
                    .collect(Collectors.joining(", "));
        }

        return slotNumbers;
    }

}
