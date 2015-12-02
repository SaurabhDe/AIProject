package com.example.soumya.myscheduler;

import java.util.Date;

/**
 * Created by Soumya on 02-12-2015.
 */
public class Block {
    private String name;
    private Date startTime;
    private Date endTime;

    public Block(String name, Date sTime, Date eTime) {
        this.name = name;
        this.startTime = sTime;
        this.endTime = eTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
