package com.example.Planner_team4;

import java.sql.Time;
import java.util.Date;

public class Appointment {
    String user;
    String title;
    Date startDate;
    Date endDate;
    Date startTime;
    Date endTime;
    String notes;

    public Appointment(String user, String title, Date startDate, Date endDate, Date startTime, Date endTime, String notes) {
        this.user = user;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.notes = notes;
    }

    public String getUser() {
        return user;
    }

    public String getTitle() {
        return title;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public String getNotes() {
        return notes;
    }
}
