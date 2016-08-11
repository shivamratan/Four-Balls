package com.ratanapps.fourballs;

import android.app.Activity;
import android.os.Bundle;

public class gfx extends Activity
{
	mycustomback ourview;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		ourview=new mycustomback(this);
		setContentView(ourview);
		
	}
}
