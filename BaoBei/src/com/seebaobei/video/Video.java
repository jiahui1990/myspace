package com.seebaobei.video;

import java.io.Serializable;

import android.R.integer;


public class Video implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8740487798562901113L;
	
	private int id;
	private int mChannelIndex;
	private String username;
	private String pass;
	private String sn;
	private String name;
	private int img;
	private int type;
	
	private boolean mIfLogin = false; // If login...
	private boolean mIsPlaying = false; // Is playing video...
	private boolean mIfExit = false; // If exit the activity...
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getmChannelIndex() {
		return mChannelIndex;
	}
	public void setmChannelIndex(int mChannelIndex) {
		this.mChannelIndex = mChannelIndex;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getImg() {
		return img;
	}
	public void setImg(int img) {
		this.img = img;
	}
	
	public boolean ismIfLogin() {
		return mIfLogin;
	}
	public void setmIfLogin(boolean mIfLogin) {
		this.mIfLogin = mIfLogin;
	}
	public boolean ismIsPlaying() {
		return mIsPlaying;
	}
	public void setmIsPlaying(boolean mIsPlaying) {
		this.mIsPlaying = mIsPlaying;
	}
	public boolean ismIfExit() {
		return mIfExit;
	}
	public void setmIfExit(boolean mIfExit) {
		this.mIfExit = mIfExit;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
}
