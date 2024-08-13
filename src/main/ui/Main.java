package ui;

import java.io.FileNotFoundException;

// Main class to run the application
public class Main {
    public static void main(String[] args) {
        try {
            new TimeTableGeneratorUI();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}
