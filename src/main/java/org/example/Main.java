package org.example;

import org.example.Entity.AirportName;
import org.example.Utils.CSVReader;
import org.example.Utils.Timer;

import java.io.*;
import java.util.*;

public class Main {

    private static final String path = "src/main/resources/airports.csv";

    public static void main(String[] args) throws IOException {
        CSVReader reader = new CSVReader(path);
        SortedSet<AirportName> sortedSet = reader.indexAndName();
        Scanner scanner = new Scanner(System.in);
        while (!scanner.hasNext("!quit")) {
            String filter = scanner.next();
            String name = scanner.next();
            Timer timer = new Timer();
            ArrayList<String> airports = reader.findByName(name);
            System.out.println(timer.getTime() + " " + airports.size());
            for (String str : airports) {
                System.out.println(str);
            }
        }
    }
}