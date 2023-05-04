package org.example;

import lombok.Data;

@Data
public class Airport {
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
}
