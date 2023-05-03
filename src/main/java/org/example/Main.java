package org.example;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {
    static ArrayList<String> airports = new ArrayList<>();
    static String path = "src/main/resources/airports.csv";
    static SortedSet<AirportName> sortedSet = new TreeSet<>((n1, n2) -> n1.getNumberInFile() - n2.getNumberInFile());

    public static void main(String[] args) throws IOException {
        try (FileInputStream inputStream = new FileInputStream(path);
             Scanner sc = new Scanner(inputStream)) {
            int index = 0;
            while (sc.hasNextLine()) {
                AirportName airportName = new AirportName(index, sc.nextLine().split(",")[1].replaceAll("\"", ""));
                sortedSet.add(airportName);
                index++;
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        Scanner scanner = new Scanner(System.in);
        while (!scanner.hasNext("!quit")) {
            String filter = scanner.next();
            String name = scanner.next();
            Date start = new Date();
            airports = findByName(findByName(name));
            System.out.println(new Date().getTime() - start.getTime() + " " + airports.size());
            for (String str: airports) {
                System.out.println(str);
            }
        }
    }
    //(column[1]>10&column[5]='GKA')||(column[1]<10000||column[11]=0)
    //(column[1]>10&column[5]='GKA'&column[2]>1)||(column[1]<10000&column[11]=0||(column[2]>100||column[7]<>100))
    private static Condition parseCondition(String string) {
        int columnIndex = Integer.parseInt(string.substring(string.indexOf("[") + 1, string.indexOf("]")));
        String operation = string.substring(string.indexOf("]") + 1, string.indexOf("]") + 2);
        String value = string.substring(string.indexOf(operation) + 1);
        return new Condition(columnIndex, operation, value);
    }

    private static ArrayList<Integer> findByName(String s) {
        ArrayList<Integer> integers = new ArrayList<>();
        for (AirportName air : sortedSet) {
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
/*Condition condition = new Condition(num, op, val);
            Condition condition1 = new Condition(1, "<", "30");
            Predicate<String> predicate = condition.and(condition1);
            Condition condition2 = new Condition(1, "<>", "0");
            predicate = condition2.or(predicate);
            */

    

/*private static void parseFilter(String filter){
        String[] filterWithAnds = filter.split("\\|");
        for (int i = 0; i<filterWithAnds.length; i++){
            if(filterWithAnds[i].contains("&")){
                String[] parsed = filter.split("&");
                for (String str: parsed) {

                }
            }
        }
    }*/

/*private static void parseParenthesis(String filter) {
            if(filter.contains("(") && filter.contains(")")){
                int index = 0;
                while (index != filter.lastIndexOf("(")+1){
                    index = filter.indexOf("(", index)+1;
                    String maxPriority = filter.substring(index, filter.indexOf(")", index));

                }
            }
        }*/

/*private static Predicate<String> parse(String filter) {
        Stack<Integer> stack = new Stack<>();
        Stack<String> brackets = new Stack<>();
        /*StringBuilder stringBuilder = new StringBuilder(filter);
        stringBuilder.insert(0, "(").insert(filter.length(), ")");
        filter = stringBuilder.toString();*/
/*char[] chars = filter.toCharArray();
        if (filter.contains("(") && filter.contains(")")) {
                for (int i = 0; i < chars.length; i++) {
        if (chars[i] == '(') {
        stack.push(i);
        }
        if (chars[i] == ')') {
        String cond = filter.substring(stack.pop() + 1, i);
        if(cond.contains("(") || cond.contains(")")){

        }
        }
        }
        }
        return null;
        }
private static Predicate<String> parseResult(String filter) {
        System.out.println("Filter: " + filter);
        int or = filter.indexOf("||");
        int and = filter.indexOf("&");
        if (or != -1) {
        return parseOr(filter, or);
        } else if (and != -1) {
        return parseAnd(filter, and);
        }
        return null;
        }

private static Predicate<String> parseOr(String filter, int or) {
        if (filter.indexOf("||") != filter.lastIndexOf("||")) {
        System.out.println(filter.substring(0, or));
        Predicate<String> predicate = null;
        int and = filter.substring(0, or).indexOf("&");
        if (and != -1) {
        predicate = parseAnd(filter.substring(0, or), and);
        }
        while (or != filter.lastIndexOf("||")) {
        String maxPriority = filter.substring(or + 2, filter.indexOf("||", or + 2));
        or = filter.indexOf("||", or + 2);
        and = maxPriority.indexOf("&");
        System.out.println(maxPriority);
        if (predicate == null) {
        if (and != -1) {
        predicate = parseCondition(filter.substring(0, or)).or(parseAnd(maxPriority, and));
        } else {
        predicate = parseCondition(filter.substring(0, or)).or(parseCondition(maxPriority));
        }
        } else {
        if (and != -1) {
        predicate = predicate.or(parseAnd(maxPriority, and));
        } else {
        predicate = predicate.or(parseCondition(maxPriority));
        }
        }
        }
        and = filter.substring(or + 2).indexOf("&");
        System.out.println(filter.substring(or + 2));
        if (and != -1) {
        return predicate.or(parseAnd(filter.substring(or + 2), and));
        } else {
        return predicate.or(parseCondition(filter.substring(or + 2)));
        }
        } else {
        int leftAnd = filter.substring(0, or).indexOf("&");
        System.out.println(filter.substring(0, or));
        int rightAnd = filter.substring(or + 2).indexOf("&");
        System.out.println(filter.substring(or + 2));
        if (leftAnd != -1 && rightAnd != -1) {
        return parseAnd(filter.substring(0, or), leftAnd).or(parseAnd(filter.substring(or + 2), rightAnd));
        } else if (leftAnd == -1 && rightAnd != -1) {
        return parseCondition(filter.substring(0, or)).or(parseAnd(filter.substring(or + 2), rightAnd));
        } else if (leftAnd != -1 && rightAnd == -1) {
        return parseAnd(filter.substring(0, or), leftAnd).or(parseCondition(filter.substring(or + 2)));
        } else {
        return parseCondition(filter.substring(0, or)).or(parseCondition(filter.substring(or + 2)));
        }
        }
        }

private static Predicate<String> parseAnd(String filter, int and) {
        if (filter.indexOf("&") != filter.lastIndexOf("&")) {
        System.out.println(filter.substring(0, and));
        Condition left = parseCondition(filter.substring(0, and));
        Predicate<String> predicate = null;
        while (and != filter.lastIndexOf("&")) {
        String maxPriority = filter.substring(and + 1, filter.indexOf("&", and + 1));
        and = filter.indexOf("&", and + 1);
        System.out.println(maxPriority);
        if (predicate == null) {
        predicate = left.and(parseCondition(maxPriority));
        } else {
        predicate = predicate.and(parseCondition(maxPriority));
        }
        }
        System.out.println(filter.substring(and + 1));
        return predicate.and(parseCondition(filter.substring(and + 1)));
        } else {
        System.out.println(filter.substring(0, and));
        System.out.println(filter.substring(and + 1));
        return parseCondition(filter.substring(0, and)).and(parseCondition(filter.substring(and + 1)));
        }
        }*/
//(column[1]>10&column[5]='GKA')||(column[1]<10000||column[11]=0)
//(column[1]>10&column[5]='GKA'&column[2]>1)||(column[1]<10000&column[11]=0||column[2]>100||column[7]<>100)

