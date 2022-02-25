package com.example.stopwatch_cycle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class StopwatchActivity extends AppCompatActivity {
    private int seconds = 0;
    private boolean running;
    private boolean realisation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);

        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            realisation = savedInstanceState.getBoolean("realisation");
        }
        runTimer();
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
    }

    public void onClickStart(View v){
        running = true;
    }

    public void onClickStop(View v){
        running = false;
    }

    public void onClickReset(View v){
        running = false;
        seconds = 0;
    }

    protected void onPause(){
        super.onPause();
        realisation = running;
        running = false;
    }

    protected  void onResume(){
        super.onResume();
        if(realisation){
            running = true;
        }
    }

    private void runTimer(){
        final TextView timeView = (TextView) findViewById(R.id.time_View);
        final android.os.Handler handler = new android.os.Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = (seconds/3600);
                int minutes = (seconds/3600)/60;
                int sec = seconds%60;
                String time = String.format(Locale.getDefault(), "%02d:%02d:%02d", hours, minutes,sec);
                timeView.setText(time);
                if(running){
                    seconds++;
                }
                handler.postDelayed(this,1000);
            }
        });
    }

}