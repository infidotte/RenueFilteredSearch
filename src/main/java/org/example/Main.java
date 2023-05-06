package org.example;

import org.example.Entity.AirportName;
import org.example.FileStructure.ParseService;
import org.example.Utils.CSVReader;
import org.example.Utils.Filter;
import org.example.Utils.Timer;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static final String path = "src/main/resources/airports.csv";

    public static void main(String[] args) throws IOException {
        CSVReader reader = new CSVReader(path);
        Scanner scanner = new Scanner(System.in);
        ParseService service = new ParseService();
        Filter filterUtil = new Filter();

        SortedSet<AirportName> sortedSet = reader.indexAndName();
        service.resolve(sortedSet);
        SortedSet<Integer> answer = new TreeSet<>((n1, n2) -> n1 - n2);

        while (!scanner.hasNext("!quit")) {
            String filter = scanner.nextLine();
            String name = scanner.nextLine().toLowerCase();
            Timer timer;
            if (filter.isEmpty()) {
                timer = new Timer();
                service.find(answer, name);
                ArrayList<String> airports = reader.find(answer);
                Long ms = timer.getTime();
                for (String str : airports) {
                    parse(str);
                }
                System.out.println("Количество найденных строк: " + airports.size() + " Время, затраченное на поиск: " + ms + " мс");

            } else {
                timer = new Timer();
                service.find(answer, name);
                List<String> airports = reader.find(answer).stream().filter(filterUtil.prepareFilter(filter)).collect(Collectors.toList());
                Long ms = timer.getTime();
                for (String str : airports) {
                    parse(str);
                }
                System.out.println("Количество найденных строк: " + airports.size() + " Время, затраченное на поиск: " + ms + " мс");

            }
        }
    }
    private static void parse(String s){
        String name = s.split(",")[1];
        System.out.println(name+"[" + s + "]");
    }
}