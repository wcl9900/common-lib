package com.arta.lib.widget;

import android.content.ContextWrapper;

import com.unity3d.player.UnityPlayer;

/**
 * 自定义UnityPlayer
 * @author 王春龙
 *2016年3月24日
 */
public class DefineUnityPlayer extends UnityPlayer {

	public DefineUnityPlayer(ContextWrapper arg0) {
		super(arg0);
	}

	@Override
	protected void kill() {
//		super.kill();
	}
}
