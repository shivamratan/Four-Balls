package com.ratanapps.fourballs;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

public class AnimateActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
    
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
        new Thread(new Runnable() {
            
        	public void run() {
				// TODO Auto-generated method stub

                for(int i=0;i<3;i++)
                {
                    if(i==2)
                    {
                        Intent intent=new Intent(AnimateActivity.this,surfacegfx.class);
                        AnimateActivity.this.startActivity(intent);
                    }

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
				
					
				}

            
        }).start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
    
    
}