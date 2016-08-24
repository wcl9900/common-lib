package com.arta.lib.demo.animation.activitytransition;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.arta.lib.animation.ActivityAnimator;
import com.arta.lib.demo.R;

public class ActivityTransitionMainActivity extends Activity implements OnItemClickListener
{
	private String[] _animationList = {"fade", "flipHorizontal", "flipVertical", "disappearTopLeft", "appearTopLeft", "appearBottomRight", "disappearBottomRight", "unzoom"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activitytransition_activity_main);

		ListView list = (ListView) this.findViewById(R.id.listView);
		ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, _animationList);
		list.setAdapter(adapter);
		list.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3)
	{
		Intent i = new Intent(this, SecondActivity.class);
		i.putExtra("backAnimation", _animationList[arg2]);
		startActivity(i);
		try{
			ActivityAnimator anim = new ActivityAnimator();
			anim.getClass().getMethod(_animationList[arg2] + "Animation", Activity.class).invoke(anim, this);
		}
		catch (Exception e) { Toast.makeText(this, "An error occured " + e.toString(), Toast.LENGTH_LONG).show(); }
	}
}
