package com.example.Planner_team4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AppointmentActivity extends AppCompatActivity {
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
    SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm", Locale.ENGLISH);

    String startDateText;
    String endDateText;
    String startTimeText;
    String endTimeText;

    DatePickerDialog picker;
    TimePickerDialog timePicker;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_appointment);
        final EditText endDate = (EditText) findViewById(R.id.appEndDate);
        final EditText startDate = (EditText) findViewById(R.id.appStartDate);
        final EditText endTime = (EditText) findViewById(R.id.appEndTime);
        final EditText startTime = (EditText) findViewById(R.id.appStartTime);

        startDate.setInputType(InputType.TYPE_NULL);
        endDate.setInputType(InputType.TYPE_NULL);

        startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cal = Calendar.getInstance();
                int day = cal.get(Calendar.DAY_OF_MONTH);
                int month = cal.get(Calendar.MONTH);
                int year = cal.get(Calendar.YEAR);
                //date picker dialog
                picker = new DatePickerDialog(AppointmentActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String actualMonth = "";
                        if ((monthOfYear + 1) < 10) {

                            actualMonth = "0" + Integer.toString(monthOfYear + 1);
                        }
                        else {
                            actualMonth = Integer.toString(monthOfYear + 1);
                        }

                        String actualDay = "";
                        if (dayOfMonth < 10) {

                            actualDay = "0" + Integer.toString(dayOfMonth);
                        }
                        else actualDay = Integer.toString(dayOfMonth);

                        startDateText = actualDay + "." + actualMonth + "." + year;
                        startDate.setText(startDateText);
                    }
                }, year, month, day);
                picker.show();
            }
        });

        endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cal = Calendar.getInstance();
                int day = cal.get(Calendar.DAY_OF_MONTH);
                int month = cal.get(Calendar.MONTH);
                int year = cal.get(Calendar.YEAR);

                //date picker dialog
                picker = new DatePickerDialog(AppointmentActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String actualMonth = "";
                        if ((monthOfYear + 1) < 10) {

                            actualMonth = "0" + Integer.toString(monthOfYear + 1);
                        }
                        else actualMonth = Integer.toString(monthOfYear + 1);

                        String actualDay = "";
                        if (dayOfMonth < 10) {

                            actualDay = "0" + Integer.toString(dayOfMonth);
                        }
                        else actualDay = Integer.toString(dayOfMonth);

                        endDateText = actualDay + "." + actualMonth + "." + year;
                        endDate.setText(endDateText);
                    }
                }, year, month, day);
                picker.show();
            }
        });

        startTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int hour = cal.get(Calendar.HOUR_OF_DAY);
                int minute = cal.get(Calendar.MINUTE);
                timePicker = new TimePickerDialog(AppointmentActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {
                        String actualMinute = "";
                        String actualHour = "";
                        if (selectedMinute < 10) {
                            actualMinute = "0" + Integer.toString(selectedMinute);
                        }
                        else actualMinute = Integer.toString(selectedMinute);

                        if (selectedHour < 10) {
                            actualHour = "0" + Integer.toString(selectedHour);
                        }
                        else actualHour = Integer.toString(selectedHour);

                        startTimeText = actualHour + ":" + actualMinute;
                        startTime.setText(startTimeText);
                    }
                }, hour, minute, true);
                timePicker.show();
            }
        });

        endTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int hour = cal.get(Calendar.HOUR_OF_DAY);
                int minute = cal.get(Calendar.MINUTE);
                timePicker = new TimePickerDialog(AppointmentActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {
                        String actualMinute = "";
                        String actualHour = "";
                        if (selectedMinute < 10) {
                            actualMinute = "0" + Integer.toString(selectedMinute);
                        }
                        else actualMinute = Integer.toString(selectedMinute);

                        if (selectedHour < 10) {
                            actualHour = "0" + Integer.toString(selectedHour);
                        }
                        else actualHour = Integer.toString(selectedHour);
                        endTimeText = actualHour + ":" + actualMinute;
                        endTime.setText(endTimeText);
                    }
                }, hour, minute, true);
                timePicker.show();
            }
        });

    }

    public void validateAppointment(View view) throws ParseException {
        TextView error = (TextView) findViewById(R.id.appointmentError);
        EditText title = (EditText) findViewById(R.id.appointmentTitle);
        EditText endDate = (EditText) findViewById(R.id.appEndDate);
        EditText startDate = (EditText) findViewById(R.id.appStartDate);
        EditText endTime = (EditText) findViewById(R.id.appEndTime);
        EditText startTime = (EditText) findViewById(R.id.appStartTime);
        EditText notes = (EditText) findViewById(R.id.appointmentNotes);

        ArrayList<Appointment> appointmentList = AppointmentList.getInstance().getList();

        Date sd;
        Date ed;
        Date st;
        Date et;
        
        if (title.getText().toString().matches("")) {
            error.setText("Title is empty");
            error.setVisibility(View.VISIBLE);
        }
        else if (startDate.getText().toString().matches("")) {
            error.setText("Start date is empty");
            error.setVisibility(View.VISIBLE);
        }
        else if (endDate.getText().toString().matches("")) {
            error.setText("End date is empty");
            error.setVisibility(View.VISIBLE);
        }
        else if (startTime.getText().toString().matches("")) {
            error.setText("Start time is empty");
            error.setVisibility(View.VISIBLE);
        }
        else if (endTime.getText().toString().matches("")) {
            error.setText("End time is empty");
            error.setVisibility(View.VISIBLE);
        }
        else {
            if (!startDate.getText().toString().matches("") || !endDate.getText().toString().matches("") || !startTime.getText().toString().matches("") || !endTime.getText().toString().matches("")) {
                sd = dateFormat.parse(startDateText);
                ed = dateFormat.parse(endDateText);
                st = timeFormat.parse(startTimeText);
                et = timeFormat.parse(endTimeText);

                error.setText(sd.toString());
                error.setVisibility((View.VISIBLE));
                if (sd.after(ed)) {
                    error.setText("Date incorrect");
                    error.setVisibility(View.VISIBLE);
                }
                else if (sd.getTime() == ed.getTime()){
                    if(st.getTime() > et.getTime()) {
                        error.setText("Time incorrect");
                        error.setVisibility(View.VISIBLE);
                    }
                    else {
                        Appointment appointment = new Appointment("007", title.getText().toString(), sd, ed, st, et, notes.getText().toString());
                        appointmentList.add(appointment);
                        String x = appointmentList.get(0).title;
                        Intent intent = new Intent(AppointmentActivity.this, ViewAppointmentActivity.class);
                        startActivity(intent);
//                        setContentView(R.layout.view_appointment);
                    }
                }
                else {
                    Appointment appointment = new Appointment("007", title.getText().toString(), sd, ed, st, et, notes.getText().toString());
                    appointmentList.add(appointment);
                    String x = appointmentList.get(0).title;
                    Intent intent = new Intent(AppointmentActivity.this, ViewAppointmentActivity.class);
                    startActivity(intent);
//                    setContentView(R.layout.view_appointment);
                }
            }
        }
    }
}

