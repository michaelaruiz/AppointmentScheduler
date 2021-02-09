/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Scanner;
import java.util.logging.Level;

/**
 *
 * @author michaelaruiz
 */
public class Logger {
    //filename

    private static final String FILENAME = "Login_Log.txt";

    public static void log(String userName) throws FileNotFoundException, IOException {

        FileWriter fwriter = new FileWriter(FILENAME, true);
        try (PrintWriter outputFile = new PrintWriter(fwriter, true)) {
            outputFile.append(userName + " Logged In At " + LocalDateTime.now() + "\n");
        }
        System.out.println("File Written");

    }
}
