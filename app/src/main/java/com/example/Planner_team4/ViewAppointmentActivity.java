package com.example.Planner_team4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class ViewAppointmentActivity extends AppCompatActivity {

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
    SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm", Locale.ENGLISH);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_appointment);
        TextView appointmentInfo = (TextView) findViewById(R.id.appointmentInfo);
        String appointmentText = "";

        ArrayList<Appointment> appointmentList = AppointmentList.getInstance().getList();

        Appointment appointment = appointmentList.get(appointmentList.size()-1);
        String user = appointment.user;
        String title = appointment.title;
        String startDate = dateFormat.format(appointment.startDate);
        String endDate = dateFormat.format(appointment.endDate);
        String startTime = timeFormat.format(appointment.startTime);
        String endTime = timeFormat.format(appointment.endTime);
        String notes = appointment.notes;

        appointmentText = title + "\n";

        if (startDate.equals(endDate)) {
            appointmentText = appointmentText + startDate + "\n";
        }
        else
            appointmentText = appointmentText + "From " + startDate + " to " + endDate + "\n";

        if (startTime.equals(endTime)) {
            appointmentText = appointmentText + startTime + "\n";
        }
        else
            appointmentText = appointmentText + startTime + "-" + endTime + "\n";

        if (notes != "") {
            appointmentText = appointmentText + "Notes: " + notes;
        }

        appointmentInfo.setText(appointmentText);


        Button btnCloseView = (Button)findViewById(R.id.btnCloseAppointment);
        btnCloseView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewAppointmentActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });

    }

//    public void closeView(View view) {
//       setContentView(R.layout.calendar_layout);
//    }
}
