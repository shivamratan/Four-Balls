package com.ratanapps.fourballs;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.View;

public class mycustomback extends View
{
	Bitmap rball;
     float changingY;
     Typeface tf,tf1;
	public mycustomback(Context context) {
		super(context);
		rball=BitmapFactory.decodeResource(getResources(),R.drawable.rball);
		changingY=0;
		tf=Typeface.createFromAsset(context.getAssets(),"fonts/James Fajardo.ttf");
		tf1=Typeface.createFromAsset(context.getAssets(),"fonts/Quikhand.ttf");
	}
	@Override
	protected void onDraw(Canvas canvas) 
	{
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		canvas.drawColor(Color.WHITE);
		
		Paint txtPaint=new Paint();
		txtPaint.setARGB(30, 254,10,34);
		txtPaint.setTextAlign(Align.CENTER);
		txtPaint.setTextSize(54);
		txtPaint.setTypeface(tf);
		canvas.drawText("Demo Animation",canvas.getWidth()/2, 200, txtPaint);
		
		canvas.drawBitmap(rball, canvas.getWidth()/2, changingY, null);
		
		if(changingY<canvas.getWidth())
			changingY+=10;
		else
			changingY=0;
		
		Rect r=new Rect();
		r.set(0, 400, canvas.getWidth(),500);
		Paint blu=new Paint();
		blu.setColor(Color.YELLOW);
		canvas.drawRect(r, blu);
		
		Paint txtPaint1=new Paint();
		txtPaint1.setColor(Color.RED);
		txtPaint1.setTextAlign(Align.CENTER);
		txtPaint1.setTextSize(64);
		txtPaint1.setTypeface(tf1);
		canvas.drawText("Created by: David",canvas.getWidth()/2,500, txtPaint1);
		
		invalidate();
	}

}
