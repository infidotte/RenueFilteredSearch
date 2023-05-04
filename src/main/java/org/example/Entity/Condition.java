package org.example.Entity;

import java.util.function.Predicate;

public class Condition implements Predicate<String> {
    private final int columnNumber;
    private final String operation;
    private final String value;

    public Condition(int columnNumber, String operation, String value) {
        this.columnNumber = columnNumber;
        this.operation = operation;
        this.value = value.replaceAll("'", "");
    }

    @Override
    public Predicate<String> and(Predicate<? super String> other) {
        return Predicate.super.and(other);
    }

    @Override
    public Predicate<String> or(Predicate<? super String> other) {
        return Predicate.super.or(other);
    }

    @Override
    public boolean test(String input) {
        String[] strings = input.split(",");

        String data = strings[columnNumber - 1].replaceAll("\"", "");
        switch (operation) {
            case ">":
                return Integer.parseInt(data) > Integer.parseInt(value);
            case "<":
                return Integer.parseInt(data) < Integer.parseInt(value);
            case "<>":
                if (parseIntOrNull(value) != null && parseIntOrNull(data) != null) {
                    return Integer.parseInt(data) != parseIntOrNull(value);
                } else {
                    return !data.equals(value);
                }
            case "=":
                if (parseIntOrNull(value) != null && parseIntOrNull(data) != null) {
                    return Integer.parseInt(data) == parseIntOrNull(value);
                } else {
                    return data.equals(value);
                }
        }
        return false;
    }

    public Integer parseIntOrNull(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public String toString() {
        return "Condition{" +
                "columnNumber=" + columnNumber +
                ", operation='" + operation + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
