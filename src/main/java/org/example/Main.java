package org.example;
import java.io.IOException;
import java.nio.file.Files;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Date start = new Date();
        ArrayList<Airport> airports = new ArrayList<>();
        try {
            ListIterator<String> list =
                    Files.readAllLines(
                            Paths.get("src\\main\\resources\\airports.csv")
                    ).listIterator();
            int index = 0;
            while (list.hasNext()){
                airports.add(new Airport(index, list.next().replaceAll("\"", "").split(",")[1]));
                index++;
            }
            System.out.println(new Date().getTime() - start.getTime());
        }catch (IOException exception){
            System.out.println(exception.toString());
        }

        Scanner scanner = new Scanner(System.in);
        while (!scanner.hasNext("!quit")){
            System.out.println(scanner.next());
        }
    }
}