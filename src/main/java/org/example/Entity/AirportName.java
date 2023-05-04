package org.example.Entity;

import lombok.Data;

@Data
public class AirportName {

    private int numberInFile;
    private String name;

    public AirportName(int numberInFile, String name) {
        this.numberInFile = numberInFile;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Airport{" +
                "numberInFile=" + numberInFile +
                ", name='" + name + '\'' +
                '}';
    }
}
