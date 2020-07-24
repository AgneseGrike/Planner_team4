package com.example.Planner_team4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ViewAppointmentList extends AppCompatActivity {
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
    SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm", Locale.ENGLISH);

    String appointmentText = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_appointmentlist);
//        Date x = ;
//        Date y = null;
        ArrayList<Appointment> appointmentList = AppointmentList.getInstance().getList();
//        appointmentText = Integer.toString(appointmentList.size());
//
//        x = dateFormat.parse("23.07.2020");
//        y = timeFormat.parse("23.07.2020");
//        Appointment a1 = new Appointment("001","bla bla", x, x, y, y, "notessss");
//        Appointment a2 = new Appointment("001","bla bla", x, x, y, y, "");
//        appointmentList.add(a1);
//        appointmentList.add(a2);

        for (int i = 0; i<appointmentList.size(); i++) {
            Appointment appointment = appointmentList.get(i);

            String title = appointment.title;
            String startDate = dateFormat.format(appointment.startDate);
            String endDate = dateFormat.format(appointment.endDate);
            String startTime = timeFormat.format(appointment.startTime);
            String endTime = timeFormat.format(appointment.endTime);

            appointmentText = appointmentText + title + " | ";

            if (startDate.equals(endDate)) appointmentText = appointmentText + startDate + " | ";
            else  appointmentText = appointmentText + startDate + "-" + endDate + " | ";

            if (startTime.equals(endTime)) appointmentText = appointmentText + startTime + "\n\n";
            else appointmentText = appointmentText + startTime + "-" + endTime + "\n\n";
        }

        TextView listDisplay = (TextView) findViewById(R.id.appointmentsFormatedList);
        listDisplay.setText(appointmentText);

        Button btn = (Button) findViewById(R.id.btnAddAppointment);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
                    public void onClick(View v) {
                createAppointment();
            }
        });
    }

    public void createAppointment() {
        Intent intent = new Intent(ViewAppointmentList.this, AppointmentActivity.class);
        startActivity(intent);
    }
}
