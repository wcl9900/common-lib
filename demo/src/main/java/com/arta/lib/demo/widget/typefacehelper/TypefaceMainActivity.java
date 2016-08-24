package com.arta.lib.demo.widget.typefacehelper;

import static com.arta.lib.widget.typefacehelper.TypefaceHelper.typeface;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.arta.lib.demo.R;

public class TypefaceMainActivity extends Activity implements View.OnClickListener{

	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.typeface_main_activity);

		findViewById(R.id.btn_simple_example).setOnClickListener(this);
		findViewById(R.id.btn_sample_actionbar).setOnClickListener(this);
		findViewById(R.id.btn_sample_listview).setOnClickListener(this);
		findViewById(R.id.btn_sample_spinner).setOnClickListener(this);
		findViewById(R.id.btn_sample_multifonts).setOnClickListener(this);
		findViewById(R.id.btn_sample_dynamic).setOnClickListener(this);

		// Set custom typeface for title
		typeface(findViewById(R.id.label_main_title));
	}

	@Override public void onClick(View v) {
		switch(v.getId()) {
		case R.id.btn_simple_example:
			startActivity(new Intent(this, SimpleExampleActivity.class));
			break;
		case R.id.btn_sample_actionbar:
			startActivity(new Intent(this, ActionbarExampleActivity.class));
			break;
		case R.id.btn_sample_listview:
			startActivity(new Intent(this, ListExampleActivity.class));
			break;
		case R.id.btn_sample_spinner:
			startActivity(new Intent(this, SpinnerExampleActivity.class));
			break;
		case R.id.btn_sample_multifonts:
			startActivity(new Intent(this, MultipleTypefacesExampleActivity.class));
			break;
		case R.id.btn_sample_dynamic:
			startActivity(new Intent(this, DynamicExampleActivity.class));
			break;
		}
	}
}
