package com.arta.lib.widget.listener;

import java.util.Timer;
import java.util.TimerTask;

import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

/**
 * 轮播计时器
 * @author 王春龙
 *
 */
@SuppressLint("HandlerLeak")
public abstract class OnTouchTimerLoop implements OnTouchListener {
	
	private int sleepTime;		//休眠时间
	private int periodTime;		//轮播间隔时间
	private int delayTime;		//轮播启动延时
	
	private Timer timer;
	private TimerTask timerTask;
	
	public OnTouchTimerLoop(){
		this(3000, 4000, 2000);
	}
	
	public OnTouchTimerLoop(int delayTime, int sleepTime, int periodTime){
		this.delayTime = delayTime;
		this.sleepTime = sleepTime;
		this.periodTime = periodTime;
		
		timer = new Timer();
		timerTask = new TimerTask() {
			@Override
			public void run() {
				doAction();
			}
		};
		timer.schedule(timerTask, this.delayTime, this.periodTime);
	}
	
	public void pause(){
		if(timerTask != null){
			timerTask.cancel();
			timerTask = null;
		}
	}
	
	public void start(){
		if(timerTask != null){
			timerTask.cancel();
			timerTask = null;
		}
		timerTask = new TimerTask() {
			@Override
			public void run() {
				doAction();
			}
		};
		timer.schedule(timerTask, sleepTime, periodTime);
	}
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		start();
		return false;
	}

	/**
	 * 执行动作
	 */
	public abstract void doAction();
}
