package com.example.counter;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView txtCounter;
    Button btnStart, btnStop;

    int count = 0;
    boolean isRunning = false;

    Handler handler = new Handler();

    Runnable runnable = new Runnable() {
        @Override
        public void run() {

            if (isRunning) {
                count++;
                txtCounter.setText(String.valueOf(count));
                handler.postDelayed(this, 1000);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtCounter = findViewById(R.id.txtCounter);
        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);

        btnStart.setOnClickListener(view -> {
            if (!isRunning) {
                isRunning = true;
                handler.post(runnable);
            }
        });

        btnStop.setOnClickListener(view -> {
            isRunning = false;
            handler.removeCallbacks(runnable);
        });
    }
}