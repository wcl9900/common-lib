package com.arta.lib.widget;
import com.arta.lib.util.ResourceUtil;
import com.arta.lib.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.View;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.widget.ImageView.ScaleType;
import android.view.View.OnTouchListener;
/**
 * 帧动画视图，可以播放逐帧动画,支持动画的暂停，停止，播放，播放延迟，定位播放到指定帧，及播放到帧监听器，帧监听器可以用来对播放到指定帧做一些其他处理。
 * @author 王春龙
 *
 */
public class FrameAnimationView extends View implements OnGestureListener , OnTouchListener,Runnable{

	public enum FramePlayState{
		PLAYING,
		PAUSE,
		LOCATING
	}
	
	private GestureDetector gesture;

	private Paint paint;
	private PaintFlagsDrawFilter pfd;
	
	private Config bitmapConfig;
	
	private Matrix matrix;
	
	private ScaleType scaleType = ScaleType.FIT_XY;
	
	private Bitmap bitmap ;
	
	private int frameDuration = 20;
	
	private int frameCount = 0;
	
	private String extendName;
	private String path;
	private String pointer = ".";
	
	private int bitmapIndex = 0;
	
	private Thread frameThread;
	
	private FramePlayState framePlayState = FramePlayState.PAUSE;
	
	private boolean repeat = true;
	
	private FramePlayListener framePlayListener;
	
	private int toFrame = -1;
	
	private int preFrame = -1;
	
	private int delayTime = 0;
	
	private boolean playEnable = true;
	
	private boolean handUpdate = false;
	
	private boolean relaseThread = false;
	
	private int speed_hand = 30;
	
	private long preTime = 0;
	
	private boolean autoPlay = true;
	
	private int pic_name_length = 0;
	
	/**
	 * 就近定位
	 */
	private boolean agentSearch = false;
	private boolean addFrame = true;
	
	private boolean touchEnable = true;
	
	private String image_no_replacement = "?";
	private boolean replaceName = false;
	
	/**
	 * 帧动画监听器
	 * @author 王春龙
	 *
	 */
	public interface FramePlayListener{
		public void FramePlay(FrameAnimationView FrameAnimationView , int frameIndex , FramePlayState framePlayState);
	}
	
	public FrameAnimationView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initAttrs(attrs);
		init();
	}

	public FrameAnimationView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initAttrs(attrs);
		
		init();
	}
	/**
	 * 
	 * @param context
	 * @param path 图片资源所在asset文件夹路径 如：a/1
	 * @param extendName 图片扩展名
	 * @param frameCount 帧数量（即图片张数）
	 * @param frameDuration 帧播放间隔时间
	 */
	public FrameAnimationView(Context context,String path,String extendName,int frameCount,int frameDuration) {
		this(context,path,extendName,frameCount,frameDuration,true);
	}
	/**
	 * 
	 * @param context
	 * @param path 图片资源所在asset文件夹路径 如：a/1
	 * @param extendName 图片扩展名
	 * @param frameCount 帧数量（即图片张数）
	 * @param frameDuration 帧播放间隔时间
	 * @param autoPlay 是否开启线程使帧动画可以支持自动播放
	 */
	public FrameAnimationView(Context context,String path,String extendName,int frameCount,int frameDuration,boolean autoPlay) {
		super(context);
		this.path = path;
		this.extendName = extendName;
		this.frameCount = frameCount;
		this.frameDuration = frameDuration;
		this.autoPlay = autoPlay;
		init();
	}
	private void initAttrs(AttributeSet attrs){
		TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.FrameAnimationView);
		
		path = ta.getString(R.styleable.FrameAnimationView_path);
		extendName = ta.getString(R.styleable.FrameAnimationView_extend_name);
		frameCount = ta.getInt(R.styleable.FrameAnimationView_frame_count, frameCount);
		frameDuration = ta.getInt(R.styleable.FrameAnimationView_frame_duration, frameDuration);
		speed_hand = ta.getInt(R.styleable.FrameAnimationView_speed_hand, speed_hand);
		agentSearch = ta.getBoolean(R.styleable.FrameAnimationView_agent_search, false);
		pic_name_length = ta.getInt(R.styleable.FrameAnimationView_pic_name_length, pic_name_length);
		
		ta.recycle();
	}
	
	public void setBitmapConfig(Config config){
		this.bitmapConfig = config;
	}
	
	public void setScaleType(ScaleType scaleType){
		this.scaleType = scaleType;
	}
	
	@Override
	protected void onDetachedFromWindow() {
		recycle();
		super.onDetachedFromWindow();
	}

	/**
	 * 关闭线程，回收图片资源
	 */
	public void recycle() {
		relaseThread = true;
		if(frameThread != null){
			frameThread.interrupt();
			frameThread = null;
		}
		if(bitmap != null && !bitmap.isRecycled()){
			bitmap.recycle();
			bitmap = null;
		}
		System.gc();
	}
	
	private void init(){
		if(path.contains(image_no_replacement)){
			replaceName = true;
		}
		
		this.setLongClickable(true);
		this.setOnTouchListener(this);
		gesture = new GestureDetector(getContext(), this);
		gesture.setIsLongpressEnabled(true);
		
		matrix = new Matrix();
		
		paint = new Paint();
		paint.setAntiAlias(true);  
		
		pfd = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG|Paint.FILTER_BITMAP_FLAG);
		
		if(autoPlay){
			startFrameThread();
		}
	}
	
	public void setAgentSerchEnable(boolean agentSearch){
		this.agentSearch = agentSearch;
	}
	public boolean isAgentSearch(){
		return this.agentSearch;
	}
	
	public int getFrameCount(){
		return frameCount;
	}
	
	public void setFramePlayState(FramePlayState animPlayState){
		this.framePlayState = animPlayState;
	}
	
	public FramePlayState getAnimPlayState(){
		return this.framePlayState;
	}
	
	public void setOnFramePlayListener(FramePlayListener framePlayListener){
		this.framePlayListener = framePlayListener;
	}
	
	public void setCurrentFrame(int currentFrame){
		if(currentFrame < 0 || currentFrame >= frameCount || currentFrame == bitmapIndex) return;
		
		bitmapIndex = currentFrame;
		handUpdateUI();
	}
	
	public void setRepeat(boolean repeat){
		this.repeat = repeat;
	}
	
	public void setPlayEnable(boolean playEnable){
		this.playEnable = playEnable;
	}
	
	public void setTouchEnable(boolean enable){
		this.touchEnable = enable;
	}
	
	/**
	 * 设定每帧播放时间
	 * @param duration
	 */
	public void setFrameDuration(int duration){
		this.frameDuration = duration;
	}
	
	public boolean isRepeat(){
		return this.repeat;
	}
	
	public int getCurrentFrame(){
		return this.bitmapIndex;
	}
	
	public void play(){
		if(!playEnable || isPlaying()) return;
		
		if(isPlayEnd()){
			bitmapIndex = 0;
		}
		
		toFrame = -1;
		framePlayState = FramePlayState.PLAYING;
	}
	
	public void play(int delayTime){
		if(!playEnable) return;
		
		this.delayTime = delayTime;
		play();
	}
	
	public void playToFrame(int toFrame){
		if(toFrame < 0 || toFrame > frameCount - 1 || isPlaying()) return;
		
		if(bitmapIndex == toFrame){
			this.framePlayState = FramePlayState.LOCATING;
			handUpdateUI();
			return;
		}
		
		addFrame = addFrameFlag(toFrame);
		
		setRepeat(true);
		this.toFrame = toFrame;
		this.framePlayState = FramePlayState.LOCATING;
	}
	private boolean addFrameFlag(int toFrame){
		int addCount = 0;
		int delCount = 0;
		if(bitmapIndex > toFrame){
			addCount = frameCount - 1 - bitmapIndex + toFrame;
			delCount = bitmapIndex - toFrame;
		}
		else{
			addCount = toFrame - bitmapIndex;
			delCount = bitmapIndex + frameCount - 1 - toFrame;
		}
		return addCount <= delCount;
	}
	public void playToFrame(int toFrame , int delayTime){
		this.delayTime = delayTime;
		playToFrame(toFrame);
	}
	
	public boolean isPlaying(){
		return framePlayState == FramePlayState.PLAYING || framePlayState == FramePlayState.LOCATING;
	}
	
	private boolean isPlayEnd() {
		return !repeat && bitmapIndex == frameCount - 1;
	}
	
	public void pause(){
		if(isPlaying()){
			 framePlayState = FramePlayState.PAUSE;
		}
	}
	
	public void stop(){
		framePlayState = FramePlayState.PAUSE;
		bitmapIndex = 0;
		handUpdateUI();
	}
	
	@Override
	public void run() {
		while(true){	
			if(relaseThread) return;
			
			if(handUpdate) continue;
			
			if(framePlayState == FramePlayState.PAUSE){
				continue;
			}
			
			if(framePlayState == FramePlayState.LOCATING){
				if(bitmapIndex == toFrame){
					continue;
				}
			}
			
			try {
				if(delayTime != 0){
					Thread.sleep(delayTime);
					delayTime = 0;
				}
				Thread.sleep(frameDuration);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(addFrame){
				bitmapIndex_add();
			}
			else{
				bitmapIndex_del();
			}
			
			handUpdateUI();
		}
	}
	
	/**
	 * 关闭帧动画更新线程，用来释放线程资源
	 */
	public void stopFrameThread(){
		relaseThread = true;
		if(frameThread != null){
			frameThread.interrupt();
			frameThread = null;
		}
	}
	/**
	 * 开启帧动画线程
	 */
	public void startFrameThread(){
		if(!relaseThread && frameThread != null) return ;
		
		relaseThread = false;
		frameThread = new Thread(this);
		frameThread.start();
	}
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		return gesture.onTouchEvent(event);
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return true;
	}
	@Override
	public boolean onDown(MotionEvent e) {
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		return false;
	}
	
	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		if(!touchEnable) return false;
		
		if(!playEnable) return false;
		
		if(isPlaying()) return false;
		
		if(Math.abs(distanceY) > Math.abs(distanceX) * 2) return false;
		
		if(System.currentTimeMillis() - preTime < speed_hand){
			return true;
		}
		else{
			preTime = System.currentTimeMillis();
		}
		
		if(distanceX < 0){
			if(!repeat && bitmapIndex == frameCount - 1) return false;
			bitmapIndex = (++bitmapIndex) % frameCount;
		}
		else{
			if(!repeat && bitmapIndex == 0) return false;
			bitmapIndex_del();
		}
		
		handUpdateUI();
		
		return true;
	}

	private void handUpdateUI() {
		handUpdate = true;
		postInvalidate();
	}

	@Override
	public void onLongPress(MotionEvent e) {
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		return false;
	}
	
	private void bitmapIndex_add(){
		if(isPlayEnd() || repeat && bitmapIndex == toFrame){
			framePlayState = FramePlayState.PAUSE;
			return;
		}
		
		bitmapIndex = (++bitmapIndex) % frameCount;
	}
	
	private void bitmapIndex_del(){
		if(bitmapIndex == 0 && !repeat){
			return;
		}
		bitmapIndex = bitmapIndex - 1 < 0 ? frameCount - 1 : bitmapIndex - 1;
	}
	
	private String getName(int index){
		if(pic_name_length <= 0){
			return index+"";
		}
		
		String s = index+"";
		int add = pic_name_length - s.length();
		for(int i = 0; i < add; i++){
			s = "0" + s;
		}
		return s;
	}
	
	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
		try {
			if(bitmapIndex != preFrame || bitmap == null){
				if(bitmap != null){
					bitmap.recycle();
					bitmap = null;
				}
				
				String imagePath = null;
				if(replaceName){
					imagePath = path.replace(image_no_replacement, getName(bitmapIndex));
				}
				else{
					imagePath = path + getName(bitmapIndex) + pointer +extendName;
				}
				bitmap = ResourceUtil.getAssetBitmap(getContext() , imagePath, 1, 
						bitmapConfig != null ? bitmapConfig : Config.RGB_565);
				
				resetMatrix(bitmap);
				
				preFrame = bitmapIndex;
			}
			
			canvas.save();
			
			canvas.setDrawFilter(pfd);
			canvas.drawBitmap(bitmap, matrix, paint);
			
			canvas.restore();
			
			if(handUpdate){
				fireFramePlayListener();
				handUpdate = !handUpdate;
			}

		}
		catch(OutOfMemoryError ooe){
			ooe.printStackTrace();
			Log.e("帧动画视图", "读取图片内存溢出！！！！！！！！！！！");
		}
		catch (Exception e) {
			e.printStackTrace();			
			Log.e("帧动画视图", "读取图片出错！！！！！！！！！！！ :" + e.getMessage());
		}
	}

	private void fireFramePlayListener() {
		if(framePlayListener != null){
			framePlayListener.FramePlay(this , bitmapIndex , framePlayState);
			if(bitmapIndex == toFrame && framePlayState == FramePlayState.LOCATING){
				pause();
			}
		}
	}
	
	private void resetMatrix(Bitmap bp){
		switch (scaleType) {
		
		case FIT_XY:
			matrix.setScale((float)getWidth() / (float)bp.getWidth() , (float)this.getHeight() / (float)bp.getHeight());
			break;
		
		case FIT_CENTER :
			float xToy = (float)bp.getWidth() / (float)bp.getHeight();
			float changeWidth = ((float)this.getHeight() * xToy);
			matrix.setScale(changeWidth / (float)bp.getWidth(), (float)this.getHeight() / (float)bp.getHeight());
			matrix.postTranslate((float)getWidth() / 2 - (float)changeWidth / 2, 0);
			break;
			
		case CENTER:
			matrix.setTranslate((float)getWidth() / 2 - (float)bp.getWidth() / 2, (float)getHeight() / 2 - (float)bp.getHeight() / 2);
			break;
		
		default:
			matrix.setScale((float)getWidth() / (float)bp.getWidth() , (float)this.getHeight() / (float)bp.getHeight());
			break;
		}
	}
}
