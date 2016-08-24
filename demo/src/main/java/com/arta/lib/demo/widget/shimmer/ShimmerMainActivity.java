package com.arta.lib.demo.widget.shimmer;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.arta.lib.demo.R;
import com.arta.lib.widget.shimmer.Shimmer;
import com.arta.lib.widget.shimmer.ShimmerTextView;

public class ShimmerMainActivity extends Activity {

    ShimmerTextView tv;
    Shimmer shimmer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shimmer_activity_main);

        tv = (ShimmerTextView) findViewById(R.id.shimmer_tv);
    }

    public void toggleAnimation(View target) {
        if (shimmer != null && shimmer.isAnimating()) {
            shimmer.cancel();
        } else {
            shimmer = new Shimmer();
            shimmer.start(tv);
        }
    }
}
