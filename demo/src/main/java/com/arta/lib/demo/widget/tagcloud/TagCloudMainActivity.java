package com.arta.lib.demo.widget.tagcloud;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.arta.lib.adapter.BaseAdapterEntityViewManage;
import com.arta.lib.demo.R;
import com.arta.lib.widget.tagcloudlib.BaseEntityTagCloudViewAdapter;
import com.arta.lib.widget.tagcloudlib.TagCloudView;
import com.arta.lib.widget.tagcloudlib.TagCloudView.OnTagClickListener;

public class TagCloudMainActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	LinearLayout ll;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tagcloud_activity_main);
        TagCloudView tagCloudView = (TagCloudView) findViewById(R.id.tag_cloud);
        tagCloudView.setBackgroundColor(Color.LTGRAY);

        TextTagsAdapter tagsAdapter = new TextTagsAdapter(new String[13]);
        List<String> dataList = new ArrayList<String>();
        for(int i = 0; i < 15; i++){
        	dataList.add(i+"~~");
        }
        BaseEntityTagCloudViewAdapter<String> adapter = 
        		new BaseEntityTagCloudViewAdapter<String>(this, dataList, adapterItemManage);
        tagCloudView.setAdapter(tagsAdapter);
//        tagCloudView.setAdapter(adapter);
        tagCloudView.setOnTagClickListener(new OnTagClickListener() {
			
			@Override
			public void onItemClick(ViewGroup parent, View view, int position) {
				Toast.makeText(TagCloudMainActivity.this, position+"", Toast.LENGTH_SHORT).show();
			}
		});
    }
    
    private BaseAdapterEntityViewManage<String> adapterItemManage = new BaseAdapterEntityViewManage<String>() {
		
		@Override
		public View updateAdapterItemView(Context context, View updateView,
				String entity, int position) {

			TextView tv = (TextView)updateView;
	        tv.setText("No." + position);
	        tv.setGravity(Gravity.CENTER);
	        ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(
	        		MarginLayoutParams.WRAP_CONTENT, MarginLayoutParams.WRAP_CONTENT);
	        tv.setLayoutParams(lp);
	        
	        return updateView;
		}
		
		@Override
		public View getAdapterItemView(Context context, String entity, int position) {
	        return new TextView(context);
		}
	};
	
	public int getFontHeight(float fontSize)  
	{  
	    Paint paint = new Paint();  
	    paint.setTextSize(fontSize);  
	    FontMetrics fm = paint.getFontMetrics();  
	    return (int) Math.ceil(fm.descent - fm.top) + 2;  
	}  
}
