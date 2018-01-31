package com.example.mycam;

import android.annotation.SuppressLint;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import com.example.mycam.*;

@SuppressLint({ "NewApi", "HandlerLeak" })
public class MainActivity extends Activity{
    Context context = MainActivity.this;
    SurfaceView surfaceView;
    CameraSurfaceHolder mCameraSurfaceHolder = new CameraSurfaceHolder();
    InformationView informationView;
    EditText e1;

    
    @SuppressLint("NewApi")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        informationView = new InformationView(this);
        ActionBar actionBar = getActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_main);
//        View decorView = getWindow().getDecorView();
//        int option = View.SYSTEM_UI_FLAG_FULLSCREEN;
//        decorView.setSystemUiVisibility(option);

        initView();
//		findViewById(R.id.button1).setOnClickListener(this);
		findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {    
            @Override    
            public void onClick(View v) {
            	String fn=e1.getText().toString();  
            	System.out.println("=======================");
          	  informationView.saveCapture(fn);
          	  e1.setText("");
            }    
        });    

		FRSDKThread frsdkThread = new FRSDKThread(this);
		informationView.SetFRSDKThread(frsdkThread);
		frsdkThread.setInformationView(informationView);
		e1 = (EditText)findViewById(R.id.editText1fuck);
		new Thread(frsdkThread).start();
    }

    public void initView()
    {
        setContentView(R.layout.activity_main);
        surfaceView = (SurfaceView) findViewById(R.id.surfaceview1);
//		e1 =(EditText)findViewById(R.id.editText1fuck);  
        
        //FrameLayout.LayoutParams cameraFL = new FrameLayout.LayoutParams(320, 240,Gravity.TOP); // set size  
        //cameraFL.setMargins(100, 50, 0, 0);  // set position  
        //surfaceView.setLayoutParams(cameraFL);  
        
        mCameraSurfaceHolder.setInformationView(informationView);
        mCameraSurfaceHolder.setCameraSurfaceHolder(context,surfaceView);
        addContentView(informationView, new LayoutParams (LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        
        ViewGroup.LayoutParams lp = surfaceView.getLayoutParams();  
//        lp.height = LayoutParams.MATCH_PARENT;  
//        lp.width = LayoutParams.MATCH_PARENT;  
        lp.height = 480;  
        lp.width = 640;  
        //System.out.println(lp.width);
        surfaceView.setLayoutParams(lp);
        
        GloableConfig g1 = new GloableConfig();
    	Log.wtf("a==========1", "[" + g1.getRedisKey() + "]");
        GloableConfig g2 = new GloableConfig();
    	Log.wtf("a==========2", "[" + g2.getRedisKey() + "]");
        GloableConfig g3 = new GloableConfig();
    	Log.wtf("a==========3", "[" + g3.getRedisKey() + "]");

    }
    
    
    public Handler msgHandler = new Handler() {  
        @Override  
        public void handleMessage(Message msg) {  
            if (msg.what == 10) {
            	EditText et = (EditText)findViewById(R.id.editText1);
            	if(et != null){
                	et.setText((String)(msg.obj)+"\n"+et.getText());
            	}
//            	msg.
//            	invalidate();
    //            stateText.setText("completed");  
            }  
        }  
    };
    
//	@Override
//	public void onClick(View v) {
//      if(v.getId() == R.id.button1){
////          String fn=e1.getText().toString();  
//    	  Log.i("aaaaaaaaaaaaaaaaaaa", "xxxx");
//    	  System.out.println("=======================");
//    	  informationView.saveCapture("fuck-android");
//    	  
//       }
//	}

    
}
