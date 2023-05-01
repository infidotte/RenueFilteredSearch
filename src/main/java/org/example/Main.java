package org.example;

import java.io.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    static ArrayList<Airport> airports = new ArrayList<>();
    static String path = "src/main/resources/airports.csv";
    static SortedSet<Airport> sortedSet = new TreeSet<>((n1, n2) -> n1.getNumberInFile() - n2.getNumberInFile());

    public static void main(String[] args) throws IOException {
        try (FileInputStream inputStream = new FileInputStream(path);
             Scanner sc = new Scanner(inputStream)) {
            int index = 0;
            while (sc.hasNextLine()) {
                sortedSet.add(new Airport(index, sc.nextLine().split(",")[1].replaceAll("\"", "")));
                index++;
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        Scanner scanner = new Scanner(System.in);
        while (!scanner.hasNext("!quit")) {
            String name = scanner.next();
            Date start = new Date();
            ArrayList<String> list = findByName(findByName(name));
            for (String line :
                    list) {
                System.out.println(line);
            }
            System.out.println(new Date().getTime() - start.getTime() + " " + list.size());
        }
    }

    private static ArrayList<Integer> findByName(String s) {
        ArrayList<Integer> integers = new ArrayList<>();
        for (Airport air : sortedSet) {
            if (air.getName().startsWith(s)) {
                integers.add(air.getNumberInFile());
            }
        }
        return integers;
    }

    private static ArrayList<String> findByName(ArrayList<Integer> integers) {
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}