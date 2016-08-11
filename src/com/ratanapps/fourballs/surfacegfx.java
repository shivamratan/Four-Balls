package com.ratanapps.fourballs;

import java.util.Random;


import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;

public class surfacegfx extends Activity implements OnTouchListener {
	mycustomsurface oursurface;

	Bitmap bmp, bmp1, bmp2, bmp3;
	Bitmap plus;
	float x, y, sx, sy, fx, fy;
	float dx, dy, anix, aniy, scaledx, scaledy;
	float x1, y1, sx1, sy1, fx1, fy1;
	float dx1, dy1, anix1, aniy1, scaledx1, scaledy1;
	float x2, y2, sx2, sy2, fx2, fy2;
	float dx2, dy2, anix2, aniy2, scaledx2, scaledy2;
	float x3, y3, sx3, sy3, fx3, fy3;
	float dx3, dy3, anix3, aniy3, scaledx3, scaledy3;
	float touchy,tsy,tfy;
	int height;
	int startx1;
	int startx2;
	int score;
	Typeface tf1;
	boolean  reduced,reduced1,reduced2,reduced3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		oursurface = new mycustomsurface(this);
		oursurface.setOnTouchListener(this);
		tf1=Typeface.createFromAsset(getAssets(),"fonts/Quikhand.ttf");
		score=0;

		dx = dy = anix = aniy = scaledx = scaledy = 0;
		dx1 = dy1 = anix1 = aniy1 = scaledx1 = scaledy1 = 0;
		dx2 = dy2 = anix2 = aniy2 = scaledx2 = scaledy2 = 0;
		dx3 = dy3 = anix3 = aniy3 = scaledx3 = scaledy3 = 0;

		x = y = sx = sy = fx = fy = 0;
		x1 = y1 = sx1 = sy1 = fx1 = fy1 = 0;
		x2 = y2 = sx2 = sy2 = fx2 = fy2 = 0;
		x3 = y3 = sx3 = sy3 = fx3 = fy3 = 0;
		
		tsy=0;
		touchy=0;
		tfy=0;

		
		reduced=reduced1=reduced2=reduced3=true;
		
		bmp = BitmapFactory.decodeResource(getResources(), R.drawable.red);
		bmp1 = BitmapFactory.decodeResource(getResources(), R.drawable.blue);
		bmp2 = BitmapFactory.decodeResource(getResources(), R.drawable.yellow);
		bmp3 = BitmapFactory.decodeResource(getResources(), R.drawable.green);

		plus = BitmapFactory.decodeResource(getResources(), R.drawable.aes);
		Random r = new Random();
		Display dp = getWindowManager().getDefaultDisplay();
		int width = dp.getWidth();
		height = dp.getHeight();

		// for first ball
		fx = r.nextInt(width - bmp.getWidth());
		fy = r.nextInt(height - bmp.getHeight());
		sx = r.nextInt(width - bmp.getWidth());
		sy = r.nextInt(height - bmp.getHeight());

		dx = fx - sx;
		dy = fy - sy;
		//scaledx = dx / 30;
		//scaledy = dy / 30;
		
		scaledx=10;
		scaledy=10;

		// for second ball
		fx1 = r.nextInt(width - bmp1.getWidth());
		fy1 = r.nextInt(height - bmp1.getHeight());
		sx1 = r.nextInt(width - bmp1.getWidth());
		sy1 = r.nextInt(height - bmp1.getHeight());

		dx1 = fx1 - sx1;
		dy1 = fy1 - sy1;
		//scaledx1 = dx1 / 30;
		//scaledy1 = dy1 / 30;
		scaledx1=10;
		scaledy1=10;

		// for third ball
		fx2 = r.nextInt(width - bmp2.getWidth());
		fy2 = r.nextInt(height - bmp2.getHeight());
		sx2 = r.nextInt(width - bmp2.getWidth());
		sy2 = r.nextInt(height - bmp2.getHeight());

		dx2 = fx2 - sx2;
		dy2 = fy2 - sy2;
		//scaledx2 = dx2 / 30;
		//scaledy2 = dy2 / 30;
		scaledx2=10;
		scaledy2=10;

		// for fourth ball
		fx3 = r.nextInt(width - bmp3.getWidth());
		fy3 = r.nextInt(height - bmp3.getHeight());
		sx3 = r.nextInt(width - bmp3.getWidth());
		sy3 = r.nextInt(height - bmp3.getHeight());

		dx3 = fx3 - sx3;
		dy3 = fy3 - sy3;
		//scaledx3 = dx3 / 30;
		//scaledy3 = dy3 / 30;
		
		scaledx3=10;
		scaledy3=10;
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(oursurface);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		oursurface.pause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		oursurface.resume();

	}

	public boolean onTouch(View arg0, MotionEvent arg1) 
	{
		
	    if(arg1.getY()<height&&arg1.getY()>height-300)
	    {
	    	
	    	switch(arg1.getAction())
	    	{
	    	case MotionEvent.ACTION_DOWN:
	    		 tsy=arg1.getX();
	    		break;
	    	case MotionEvent.ACTION_UP:
	    		 tfy=arg1.getX();
	    		break;
	    	}
	    	touchy=arg1.getX();
	    }
		return true;
	}

	public class mycustomsurface extends SurfaceView implements Runnable {
		SurfaceHolder holder;
		Thread mythread;
		boolean isRunning = true;
		float cursorx, cursorx1, cursorx2, cursorx3;
		float cursory, cursory1, cursory2, cursory3;
		int start=0,end=50;
		private float y_circle;
		public mycustomsurface(surfacegfx sfg) {
			super(sfg);
			holder = getHolder();

		}

		public void run() {
			while (isRunning) {
				if (!holder.getSurface().isValid())
					continue;

				Canvas canvas = holder.lockCanvas();
				canvas.drawColor(Color.WHITE);

				/*if (x != 0 && y != 0) {

					canvas.drawBitmap(bmp, x - (bmp.getWidth() / 2),
							y - (bmp.getWidth() / 2), null);
				}*/

				// if(fx!=0&&fy!=0)
				// {
				cursorx = fx - anix;
				cursory = fy  - aniy;
				canvas.drawBitmap(bmp, cursorx, cursory, null);

				cursorx1 = fx1  - anix1;
				cursory1 = fy1 - aniy1;
				canvas.drawBitmap(bmp1, cursorx1, cursory1, null);

				cursorx2 = fx2  - anix2;
				cursory2 = fy2 - aniy2;
				canvas.drawBitmap(bmp2, cursorx2, cursory2, null);

				cursorx3 = fx3 - anix3;
				cursory3 = fy3  - aniy3;
				canvas.drawBitmap(bmp3, cursorx3, cursory3, null);

				// canvas.drawBitmap(plus, fx-(plus.getWidth()/2),
				// fy-(plus.getWidth()/2), null);
				/*
				 * }
				 * 
				 * if(sx!=0&&sy!=0) {
				 * 
				 * //canvas.drawBitmap(plus, sx-(plus.getWidth()/2),
				 * sy-(plus.getWidth()/2), null); }
				 */
				
				//r.set(0,canvas.getHeight()-118,canvas.getWidth(),canvas.getHeight());
		/*		Rect r1=new Rect();
				r1.set(0,canvas.getHeight()-118,canvas.getWidth(),canvas.getHeight()-120);
				Paint p1=new Paint();
				p1.setColor(Color.BLACK);
				canvas.drawRect(r1, p1);*/

				Rect r = new Rect();
				if(touchy==0)
				{
					startx1=(canvas.getWidth()/2)-75;
					startx2=(canvas.getWidth()/2)+75;
					r.set((canvas.getWidth()/2)-75, canvas.getHeight()-120,(canvas.getWidth()/2)+75,canvas.getHeight()-73);
				}
				if(touchy!=0)
				{
					int tempx=(int)touchy;
					startx1=tempx-75;
					startx2=tempx+75;
					if(startx1<0||startx2>canvas.getWidth())
					{
					if(startx1<0)
					{	
						startx1=0;
						startx2=150;
				     r.set(0, canvas.getHeight()-120,150,canvas.getHeight()-73);
					}
					if(startx2>canvas.getWidth())
					{
						startx1=canvas.getWidth()-150;
						startx2=canvas.getWidth();
						r.set(canvas.getWidth()-150, canvas.getHeight()-120,canvas.getWidth(),canvas.getHeight()-73);
					}
					}
					else
					{
						r.set(startx1, canvas.getHeight()-120,startx2,canvas.getHeight()-73);
					}
				}
				Paint p = new Paint();
				p.setColor(Color.BLACK);
				canvas.drawRect(r, p);
				
				Rect r_score=new Rect();
				r_score.set(0, (canvas.getHeight()/2)-400,canvas.getWidth()/2 , (canvas.getHeight()/2)-350);
				Paint p_score=new Paint();
				p_score.setColor(Color.argb(175, 255, 255, 0));
				canvas.drawRect(r_score, p_score);
				
				
			Paint scorepaint=new Paint();
			 scorepaint.setColor(Color.RED);
			 scorepaint.setTextAlign(Align.CENTER);
			 scorepaint.setTextSize(64);
			 scorepaint.setTypeface(tf1);
			 canvas.drawText("score: "+score,150,(canvas.getHeight()/2)-350,scorepaint);
				
				
				int h1=canvas.getHeight()-120;
			    int h2=canvas.getHeight()-73;
			    int var3=(int)(cursory+(bmp.getHeight()/2));
			    if(var3>=h1&&var3<=h2)
			    {
			    	System.out.println("going in the range******************");
			    	int var4=(int)(cursorx+(bmp.getWidth()/2));
			    	if((var4>=startx2-5&&var4<=startx2+5)||(var4>=startx1-5&&var4<=startx1+5))
			    	{
			    		scaledx=-scaledx;
			    	}
			    }
			 
				if(cursorx>=startx1&&cursorx<=startx2)
				{
					//System.out.println("going in bmp");
					int var1=(int)(cursory+bmp.getHeight());
					int lim=(canvas.getHeight()-120);
					//System.out.println((int)(cursory+bmp.getHeight())+"************"+(canvas.getHeight()-120));
					if(var1>=lim&&var1<=lim+10&&scaledy<0)
					{
						score++;
						scaledy=-scaledy;
					}
					
					int var2=(int)cursory;
					int lim2=r.bottom;
					if(var2>=lim2-10&&var2<=lim2&&scaledy>0)
					{
						scaledy=-scaledy;
					}
				}
				
				
				int var5=(int)(cursory1+(bmp1.getHeight()/2));
			    if(var5>=h1&&var5<=h2)
			    {
			    	System.out.println("going in the range******************");
			    	int var6=(int)(cursorx1+(bmp1.getWidth()/2));
			    	if((var6>=startx2-5&&var6<=startx2+5)||(var6>=startx1-5&&var6<=startx1+5))
			    	{
			    		scaledx1=-scaledx1;
			    	}
			    }

				if(cursorx1>=startx1&&cursorx1<=startx2)
				{
					//System.out.println("going in bmp1");
					int var1=(int)(cursory1+bmp1.getHeight());
					int lim=(canvas.getHeight()-120);
					if(var1>=lim&&var1<=lim+10&&scaledy1<0)
					{
						score++;
						scaledy1=-scaledy1;
					}
					int var2=(int)cursory1;
					int lim2=r.bottom;
					if(var2>=lim2-10&&var2<=lim2&&scaledy1>0)
					{
						scaledy1=-scaledy1;
					}
				}

				int var7=(int)(cursory2+(bmp2.getHeight()/2));
			    if(var7>=h1&&var7<=h2)
			    {
			    	System.out.println("going in the range******************");
			    	int var8=(int)(cursorx2+(bmp2.getWidth()/2));
			    	if((var8>=startx2-5&&var8<=startx2+5)||(var8>=startx1-5&&var8<=startx1+5))
			    	{
			    		scaledx2=-scaledx2;
			    	}
			    }
				if(cursorx2>=startx1&&cursorx<=startx2)
				{
					System.out.println("Entering yellow region");
					//System.out.println("going in bmp2");
					int var1=(int)(cursory2+bmp2.getHeight());
					int lim=(canvas.getHeight()-120);
					if(var1>=lim&&var1<=lim+10&&scaledy2<0)
					{
						score++;
						scaledy2=-scaledy2;
					}
					
					int var2=(int)cursory2;
					int lim2=r.bottom;
					if(var2>=lim2-10&&var2<=lim2&&scaledy2>0)
					{
						scaledy2=-scaledy2;
					}
				}

				int var9=(int)(cursory3+(bmp3.getHeight()/2));
			    if(var9>=h1&&var9<=h2)
			    {
			    	System.out.println("going in the range******************");
			    	int var10=(int)(cursorx3+(bmp3.getWidth()/2));
			    	if((var10>=startx2-5&&var10<=startx2+5)||(var10>=startx1-5&&var10<=startx1+5))
			    	{
			    		scaledx3=-scaledx3;
			    	}
			    }
				if(cursorx3>=startx1&&cursorx3<=startx2)
				{
					//System.out.println("going in bmp3");
					int var1=(int)(cursory3+bmp3.getHeight());
					int lim=(canvas.getHeight()-120);
					if(var1>=lim&&var1<=lim+10&&scaledy3<0)
					{
						score++;
						scaledy3=-scaledy3;
					}
					
					int var2=(int)cursory3;
					int lim2=r.bottom;
					if(var2>=lim2-10&&var2<=lim2&&scaledy3>0)
					{
						scaledy3=-scaledy3;
					}
				}
					
				
				
				
				// for first ball
				if (cursory >= (canvas.getHeight() - bmp.getHeight())
						|| cursory <= 0)
				{
					if((cursory >= (canvas.getHeight() - bmp.getHeight()))&&reduced)
					{
					  score--;
					  reduced=false;
					}
					else if(cursory<=0)
						reduced=true;
					
					scaledy = -scaledy;
				}
				if (cursorx >= (canvas.getWidth() - bmp.getWidth())
						|| cursorx <= 0)
					scaledx = -scaledx;

				anix += scaledx;
				aniy += scaledy;

				// for second ball
				if (cursory1 >= (canvas.getHeight() - bmp.getHeight())
						|| cursory1 <= 0)
				{if((cursory1 >= (canvas.getHeight() - bmp.getHeight()))&&reduced1)
				{
					  score--;
					  reduced1=false;
				}
					else if(cursory1<=0)
						reduced1=true;
				
					scaledy1 = -scaledy1;
				}
				if (cursorx1 >= (canvas.getWidth() - bmp.getWidth())
						|| cursorx1 <= 0)
					scaledx1 = -scaledx1;

				anix1 += scaledx1;
				aniy1 += scaledy1;

				// for third ball
				if (cursory2 >= (canvas.getHeight() - bmp.getHeight())
						|| cursory2 <= 0)
				{
					if((cursory2 >= (canvas.getHeight() - bmp.getHeight()))&&reduced2)
					{
						  score--;
						  reduced2=false;
					}
						else if(cursory2<=0)
							reduced2=true;
					
					scaledy2 = -scaledy2;
				}
				if (cursorx2 >= (canvas.getWidth() - bmp.getWidth())
						|| cursorx2 <= 0)
					scaledx2 = -scaledx2;

				anix2 += scaledx2;
				aniy2 += scaledy2;

				// for fourth ball
				if (cursory3 >= (canvas.getHeight() - bmp.getHeight())
						|| cursory3 <= 0)
				{
					if((cursory3>= (canvas.getHeight() - bmp.getHeight()))&&reduced3)
					{
						  score--;
						  reduced3=false;
					}
						else if(cursory3<=0)
							reduced3=true;
					
					scaledy3 = -scaledy3;
				}
				if (cursorx3 >= (canvas.getWidth() - bmp.getWidth())
						|| cursorx3 <= 0)
					scaledx3 = -scaledx3;

				anix3 += scaledx3;
				aniy3 += scaledy3;
				
				
				holder.unlockCanvasAndPost(canvas);

			}

		}

		public void pause() {
			isRunning = false;
			while (true) {
				try {
					mythread.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
			mythread = null;
		}

		public void resume() {
			isRunning = true;

			mythread = new Thread(this);
			mythread.start();
		}

	}

}
