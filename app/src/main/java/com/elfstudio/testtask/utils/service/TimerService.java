package com.elfstudio.testtask.utils.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class TimerService extends Service {

    public static final long TIME_INTERVAL = 60000;
    private final Handler handler = new Handler();
    private Timer timer = null;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("unsupported Operation");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (timer != null) {
            timer.cancel();
        } else {
            timer = new Timer();
        }

        timer.scheduleAtFixedRate(new TimeDisplayTimerTask(), 0, TIME_INTERVAL);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "In Destroy", Toast.LENGTH_SHORT).show();
        timer.cancel();
    }

    private class TimeDisplayTimerTask extends TimerTask {

        @Override
        public void run() {
            handler.post(() -> Toast.makeText(getApplicationContext(), "Displaying time toast every 1 min :- " + Calendar.getInstance().getTime(), Toast.LENGTH_SHORT).show());
        }
    }
}
