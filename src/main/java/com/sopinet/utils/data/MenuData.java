package com.sopinet.utils.data;

import android.graphics.drawable.Drawable;

import com.sopinet.utils.data.NavImageTitleData;

public class MenuData extends NavImageTitleData {
	public String title;
	public Drawable image;
	
	@Override
	public String getTitle() {
		return title;
	}
	
	@Override
	public Drawable getImage() {
		return image;
	}
}