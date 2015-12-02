package com.example.soumya.myscheduler;

import android.widget.EditText;
import android.widget.Spinner;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;

/**
 * Created by Soumya on 18-11-2015.
 */
public class Tasks implements Serializable, Cloneable, Comparable{
    private String name;
    private String subject;
    private Date dueOnDate;
    private int priority;           // int between 1 - 10. Default is 1
    private int noOfHoursRequired;  // Default is 3
    private boolean completed;
//    private String notes;

//    private int noOfSubTypes;
//    private Tasks[] subTypes;
    public Tasks(String name, String subject, Date dueOnDate) {
        this(name, subject, dueOnDate, 1, 3, false, 0, null);
    }

    public Tasks(String name, String subject, Date dueOnDate, int priority) {
        this(name, subject, dueOnDate, priority, 3, false, 0, null);
    }


    public Tasks(String name, String subject, Date dueOnDate, int priority, int noOfHoursRequired) {
        this(name, subject, dueOnDate, priority, noOfHoursRequired, false, 0, null);
    }
    public Tasks(String name, String subject, Date dueOnDate, int priority, int noOfHoursRequired, boolean completed) {
        this(name, subject, dueOnDate, priority, noOfHoursRequired, completed, 0, null);
    }
    public Tasks(String name, String subject, Date dueOnDate, int priority, boolean completed) {
        this(name, subject, dueOnDate, priority, 3, completed, 0, null);
    }

    public Tasks(String name, String subject, Date dueOnDate, int priority, int noOfHoursRequired,
                 boolean completed, int noOfSubTypes, Tasks[] subTypes) {
        this.name = name;
        this.subject = subject;
        this.dueOnDate = dueOnDate;
        this.priority = priority;
        this.noOfHoursRequired = noOfHoursRequired;
        this.completed = completed;
//        this.notes = notes;
//        this.noOfSubTypes = noOfSubTypes;
//        this.subTypes = subTypes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Date getDueOnDate() {
        return dueOnDate;
    }

    public void setDueOnDate(Date dueOnDate) {
        this.dueOnDate = dueOnDate;
    }

    public int getNoOfHoursRequired() {
        return noOfHoursRequired;
    }

    public void setNoOfHoursRequired(int noOfHoursRequired) {
        this.noOfHoursRequired = noOfHoursRequired;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "Tasks{" +
                "name='" + name + '\'' +
                ", subject='" + subject + '\'' +
                ", dueOnDate=" + dueOnDate +
                ", priority=" + priority +
                ", noOfHoursRequired=" + noOfHoursRequired +
                ", completed=" + completed +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Tasks getClone() {
        try {
            return (Tasks)this.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void toggleChecked() {
        completed = !completed;
    }

    @Override
    public int compareTo(Object another) {
        Tasks temp = (Tasks) another;
        if(this.completed == temp.completed && this.name.equals(temp.name) && this.subject.equals(temp.subject)) {
            return 0;
        }
        return -1;
    }


}
