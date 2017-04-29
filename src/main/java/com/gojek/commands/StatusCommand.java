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
public class StatusCommand implements Command {

    @Override
    public String execute() {
        SlotAllocator allocator = SlotAllocator.getInstance();
        return allocator.status();
    }

}
