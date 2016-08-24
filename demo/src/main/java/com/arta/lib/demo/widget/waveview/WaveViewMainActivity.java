package com.arta.lib.demo.widget.waveview;

import android.app.Activity;
import android.os.Bundle;
import android.widget.SeekBar;

import com.arta.lib.demo.R;
import com.arta.lib.widget.waveview.WaveView;

/**
 * Created by kai.wang on 6/17/14.
 */
public class WaveViewMainActivity extends Activity {

    private SeekBar seekBar;
    private WaveView waveView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waveview_main);

        seekBar = (SeekBar) findViewById(R.id.seek_bar);
        waveView = (WaveView) findViewById(R.id.wave_view);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                waveView.setProgress(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}