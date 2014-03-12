package com.sopinet.utils;

import android.content.Context;
import junit.framework.Assert;

public class DinamicHelper {

	public static int getDrawable(Context context, String name)
	{
	    Assert.assertNotNull(context);
	    Assert.assertNotNull(name);
	
	    return context.getResources().getIdentifier(name,
	            "drawable", context.getPackageName());
	}	
}