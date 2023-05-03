package org.example;

public class Airport {
    @Override
    public String toString() {
        return "Airport{" +
                "first=" + first +
                ", second='" + second + '\'' +
                ", third='" + third + '\'' +
                ", fourth='" + fourth + '\'' +
                ", fifth='" + fifth + '\'' +
                ", sixth='" + sixth + '\'' +
                ", seventh=" + seventh +
                ", eighth=" + eighth +
                ", ninth=" + ninth +
                ", tenth=" + tenth +
                ", eleventh='" + eleventh + '\'' +
                ", twelfth='" + twelfth + '\'' +
                ", thirteenth='" + thirteenth + '\'' +
                ", fourteenth='" + fourteenth + '\'' +
                '}';
    }

    private int first;
    private String second;
    private String third;
    private String fourth;
    private String fifth;
    private String sixth;
    private Double seventh;
    private Double eighth;
    private Double ninth;
    private Double tenth;
    private String eleventh;
    private String twelfth;
    private String thirteenth;
    private String fourteenth;

    public Airport(int first, String second, String third, String fourth, String fifth, String sixth, Double seventh, Double eighth, Double ninth, Double tenth, String eleventh, String twelfth, String thirteenth, String fourteenth) {
        this.first = first;
        this.second = second;
        this.third = third;
        this.fourth = fourth;
        this.fifth = fifth;
        this.sixth = sixth;
        this.seventh = seventh;
        this.eighth = eighth;
        this.ninth = ninth;
        this.tenth = tenth;
        this.eleventh = eleventh;
        this.twelfth = twelfth;
        this.thirteenth = thirteenth;
        this.fourteenth = fourteenth;
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public String getSecond() {
        return second;
    }

    public void setSecond(String second) {
        this.second = second;
    }

    public String getThird() {
        return third;
    }

    public void setThird(String third) {
        this.third = third;
    }

    public String getFourth() {
        return fourth;
    }

    public void setFourth(String fourth) {
        this.fourth = fourth;
    }

    public String getFifth() {
        return fifth;
    }

    public void setFifth(String fifth) {
        this.fifth = fifth;
    }

    public String getSixth() {
        return sixth;
    }

    public void setSixth(String sixth) {
        this.sixth = sixth;
    }

    public Double getSeventh() {
        return seventh;
    }

    public void setSeventh(Double seventh) {
        this.seventh = seventh;
    }

    public Double getEighth() {
        return eighth;
    }

    public void setEighth(Double eighth) {
        this.eighth = eighth;
    }

    public Double getNinth() {
        return ninth;
    }

    public void setNinth(Double ninth) {
        this.ninth = ninth;
    }

    public Double getTenth() {
        return tenth;
    }

    public void setTenth(Double tenth) {
        this.tenth = tenth;
    }

    public String getEleventh() {
        return eleventh;
    }

    public void setEleventh(String eleventh) {
        this.eleventh = eleventh;
    }

    public String getTwelfth() {
        return twelfth;
    }

    public void setTwelfth(String twelfth) {
        this.twelfth = twelfth;
    }

    public String getThirteenth() {
        return thirteenth;
    }

    public void setThirteenth(String thirteenth) {
        this.thirteenth = thirteenth;
    }

    public String getFourteenth() {
        return fourteenth;
    }

    public void setFourteenth(String fourteenth) {
        this.fourteenth = fourteenth;
    }
}
