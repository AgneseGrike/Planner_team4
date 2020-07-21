package com.example.Planner_team4;

import java.sql.Time;
import java.util.Date;

public class Appointment {
    String user;
    String title;
    Date startDate;
    Date endDate;
    Time startTime;
    Time endTime;
    String notes;

    public Appointment(String user, String title, Date startDate, Date endDate, Time startTime, Time endTime, String notes) {
        this.user = user;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.notes = notes;
    }
}
