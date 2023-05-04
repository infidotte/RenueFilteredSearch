package org.example.Utils;

import java.util.Date;

public class Timer {
    private Long start;

    public Timer() {
        this.start = new Date().getTime();
    }

    public Long getTime() {
        Long result = new Date().getTime() - start;
        start = new Date().getTime();
        return result;
    }
}
