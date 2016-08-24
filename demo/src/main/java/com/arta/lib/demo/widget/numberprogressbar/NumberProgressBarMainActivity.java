package com.arta.lib.demo.widget.numberprogressbar;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;

import com.arta.lib.demo.R;
import com.arta.lib.widget.numberprogressbar.NumberProgressBar;


public class NumberProgressBarMainActivity extends Activity {
    private int counter = 0;
    private Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.numberprogressbar_activity_main);

        final NumberProgressBar bnp = (NumberProgressBar)findViewById(R.id.numberbar1);
        counter = 0;
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        bnp.incrementProgressBy(1);
                        counter ++;
                        if (counter == 110) {
                            bnp.setProgress(0);
                            counter=0;

                        }
                    }
                });
            }
        }, 1000, 100);

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
}
