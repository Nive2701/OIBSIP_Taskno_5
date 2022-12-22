package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.Chronometer;

public class MainActivity extends AppCompatActivity {
    Button B_start, B_stop, B_reset, B_resume;
    private int seconds = 0;
    private boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startTimer();
        B_start = findViewById(R.id.start);
        B_stop = findViewById(R.id.stop);
        B_reset = findViewById(R.id.Reset);
        B_resume = findViewById(R.id.Resume);

        B_start.setOnClickListener(view -> running = true);
        B_stop.setOnClickListener(view -> running = false);
        B_resume.setOnClickListener(view -> running = true);
        B_reset.setOnClickListener(view -> {
            running = false;
            seconds = 0;
        });
    }
    private void startTimer() {
        final Chronometer chronometer = findViewById(R.id.chrono);
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int min = ((seconds/100)%3600)/60;
                int sec = (seconds/100)%60;
                int milliSec = (seconds)%100;
                @SuppressLint("DefaultLocale") String time=String.format("%02d:%02d:%02d",min,sec,milliSec);
                chronometer.setText(time);
                if (running) {
                    seconds++;
                }
                handler.postDelayed(this, 10);
            }
        });
    }
}
