package com.arta.lib.demo.widget.verificationcode;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.arta.lib.demo.R;
import com.arta.lib.widget.verificationcode.Code;

@SuppressLint("ShowToast")
public class VerificationCodeActivity extends Activity {
	
	ImageView vc_image;
	Button vc_shuaixi,vc_ok;
    String getCode=null;
    EditText vc_code;
	
	@SuppressLint("ShowToast")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_verificationcode);
		
		vc_image=(ImageView)findViewById(R.id.vc_image);
		vc_image.setImageBitmap(Code.getInstance().getBitmap());
		vc_code=(EditText) findViewById(R.id.vc_code);
		
		getCode=Code.getInstance().getCode(); //获取显示的验证码
		Log.e("info", getCode+"----");
		vc_shuaixi=(Button)findViewById(R.id.vc_shuaixi);
		vc_shuaixi.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				vc_image.setImageBitmap(Code.getInstance().getBitmap());
				getCode=Code.getInstance().getCode();
			}
		});
		
		vc_ok=(Button)findViewById(R.id.vc_ok);
		vc_ok.setOnClickListener(new OnClickListener() {
			
			@SuppressLint("ShowToast")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			String	v_code=vc_code.getText().toString().trim();
			if(v_code==null||v_code.equals("")){
				Toast.makeText(VerificationCodeActivity.this, "没有填写验证码", 2).show();
			}else if(!v_code.equals(getCode)){
				Toast.makeText(VerificationCodeActivity.this, "验证码填写不正确", 2).show();
			}else{
				Toast.makeText(VerificationCodeActivity.this, "操作成功", 2).show();
			}
			
			}
		});
		
	}

}
