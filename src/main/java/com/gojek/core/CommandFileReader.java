/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gojek.core;

import com.gojek.exceptions.InvalidCommand;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author nimeshagarwal
 */
public class CommandFileReader {

    public static List<String> read(String fileName) throws InvalidCommand {
        System.out.println("parsing file " + fileName);
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            List<String> commands = br.lines().collect(Collectors.toList());
            return commands;
        } catch (IOException e) {
            throw new InvalidCommand("unable to process file; error:" + e);
        }
    }
}
