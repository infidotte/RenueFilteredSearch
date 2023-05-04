package org.example;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

public class CSVReader {
    private final String path;
    private final SortedSet<AirportName> sortedSet =  new TreeSet<>((n1, n2) -> n1.getNumberInFile() - n2.getNumberInFile());

    public CSVReader(String path) {
        this.path = path;
    }

    public SortedSet<AirportName> indexAndName(){
        try (FileInputStream inputStream = new FileInputStream(path);
             Scanner sc = new Scanner(inputStream)) {
            int index = 0;
            while (sc.hasNextLine()) {
                AirportName airportName = new AirportName(index, sc.nextLine().split(",")[1].replaceAll("\"", ""));
                sortedSet.add(airportName);
                index++;
            }
            return sortedSet;
        } catch (IOException e) {
            throw new RuntimeException(e.toString());
        }
    }
    public ArrayList<String> findByName(String s) {
        ArrayList<Integer> integers = new ArrayList<>();
        for (AirportName air : sortedSet) {
            if (air.getName().startsWith(s)) {
                integers.add(air.getNumberInFile());
            }
        }
        ArrayList<String> list = new ArrayList<>();
        int prev = 0;
        try {
            BufferedReader lines = Files.newBufferedReader(Path.of(path));
            for (Integer index : integers) {
                String line = "";
                for (; prev <= index; prev++) {
                    line = lines.readLine();
                }
                list.add(line);
            }
            return list;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
