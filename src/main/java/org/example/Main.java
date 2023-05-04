package org.example;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {

    private static final String path = "src/main/resources/airports.csv";
    static ArrayList<String> airports = new ArrayList<>();
    static SortedSet<AirportName> sortedSet;

    public static void main(String[] args) throws IOException {
        CSVReader reader = new CSVReader(path);
        sortedSet = reader.indexAndName();
        Scanner scanner = new Scanner(System.in);
        while (!scanner.hasNext("!quit")) {
            String filter = scanner.next();
            String name = scanner.next();
            Date start = new Date();
            airports = reader.findByName(name);
            System.out.println(new Date().getTime() - start.getTime() + " " + airports.size());
            for (String str : airports) {
                System.out.println(str);
            }
        }
    }
    //(column[1]>10&column[5]='GKA')||(column[1]<10000||column[11]=0)
    //(column[1]>10&column[5]='GKA'&column[2]>1)||(column[1]<10000&column[11]=0||(column[2]>100||column[7]<>100))

    private static Airport parseString(String strAirport) {
        String[] parsed = strAirport.replaceAll("\"", "").split(",");
        return new Airport(
                Integer.parseInt(parsed[0]),
                parsed[1],
                parsed[2],
                parsed[3],
                parsed[4],
                parsed[5],
                stringToDouble(parsed[6]),
                stringToDouble(parsed[7]),
                stringToDouble(parsed[8]),
                stringToDouble(parsed[9]),
                parsed[10],
                parsed[11],
                parsed[12],
                parsed[13]
        );
    }

    private static Double stringToDouble(String str) {
        try {
            return str.equals("\\N") ? null : Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return null;
        }
    }

}