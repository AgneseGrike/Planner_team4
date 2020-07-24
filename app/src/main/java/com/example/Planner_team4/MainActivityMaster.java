package com.example.Planner_team4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;

import java.text.DateFormat;
import java.util.Calendar;

public class MainActivityMaster extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MainActivity";

    private TextView theDate;

    Toolbar toolbar;
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;

    ImageView imageView;
    TextView name, email, id;
    Button signOut;

    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_master);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        imageView = findViewById(R.id.imageView);
//        name = findViewById(R.id.nameTextView);
//        email = findViewById(R.id.emailTextView);
//        id = findViewById(R.id.idTextView);
        signOut = findViewById(R.id.signoutButton);
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.signoutButton:
                        signOut();
                        break;
                }

            }
        });

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();

//            name.setText(personName);
//            email.setText(personEmail);
//            id.setText(personId);
//            Glide.with(this).load(String.valueOf(personPhoto)).into(imageView);
        }



        Intent incomingIntent = getIntent();
        /*String date = incomingIntent.getStringExtra("date");
        theDate.setText(date);*/

        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());
        theDate = findViewById(R.id.text_view_date);
        theDate.setText(currentDate);

        toolbar = findViewById(R.id.toolbar);
        /*setSupportActionBar(toolbar);*/

        navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        drawer = findViewById(R.id.drawer);
        toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.open,R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.nav_calendar) {
                Intent intent = new Intent(MainActivityMaster.this, CalendarActivity.class);
                startActivity(intent);
        }

        if(item.getItemId() == R.id.nav_tasks) {
            Intent intent = new Intent(MainActivityMaster.this, TasksActivity.class);
            startActivity(intent);
        }

        if(item.getItemId() == R.id.nav_weather) {
            Intent intent = new Intent(MainActivityMaster.this, WeatherActivity.class);
            startActivity(intent);
        }

        if(item.getItemId() == R.id.nav_appointment) {
            Intent intent = new Intent(MainActivityMaster.this, AppointmentActivity.class);
            startActivity(intent);
        }
        return true;
    }

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(MainActivityMaster.this, "Signed out succesfully", Toast.LENGTH_LONG).show();
                        finish();
                    }
                });
    }

}