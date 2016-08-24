package com.arta.lib.demo.widget.titanic;

import android.app.Activity;
import android.os.Bundle;

import com.arta.lib.demo.R;
import com.arta.lib.widget.titanic.Titanic;
import com.arta.lib.widget.titanic.TitanicTextView;


public class TitanicMainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.titanic_activity_main);

        TitanicTextView tv = (TitanicTextView) findViewById(R.id.my_text_view);

        // set fancy typeface
        tv.setTypeface(Typefaces.get(this, "Satisfy-Regular.ttf"));

        // start animation
        new Titanic().start(tv);
    }

}
