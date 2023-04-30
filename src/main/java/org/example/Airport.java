package org.example;

public class Airport {
    private int numberInFile;

    private String name;

    public Airport(int numberInFile, String name) {
        this.numberInFile = numberInFile;
        this.name = name;
    }

    public int getNumberInFile() {
        return numberInFile;
    }

    public void setNumberInFile(int numberInFile) {
        this.numberInFile = numberInFile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
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
