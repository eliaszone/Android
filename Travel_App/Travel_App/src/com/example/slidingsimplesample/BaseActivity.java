package com.example.slidingsimplesample;
 
import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
 

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
 
public class BaseActivity extends SlidingFragmentActivity {
 
    protected ListFragment mFrag;
 
    public BaseActivity() {
    }
 
    @SuppressLint("NewApi")
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
 
        // set the Behind View
        setBehindContentView(R.layout.menu_frame);
        if (savedInstanceState == null) {
            FragmentTransaction t = this.getSupportFragmentManager().beginTransaction();
            mFrag = new MenuListFragment();
            t.replace(R.id.menu_frame, mFrag);
            t.commit();
        } else {
            mFrag = (ListFragment)this.getSupportFragmentManager().findFragmentById(R.id.menu_frame);
        }
 
        // customize the SlidingMenu
        SlidingMenu sm = getSlidingMenu();
        sm.setShadowWidthRes(R.dimen.shadow_width);
        sm.setShadowDrawable(R.drawable.shadow);
        sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        sm.setFadeDegree(0.35f);
        sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
 
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setIcon(new Drawable() {
			
			@Override
			public void setColorFilter(ColorFilter cf) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setAlpha(int alpha) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public int getOpacity() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public void draw(Canvas canvas) {
				// TODO Auto-generated method stub
				
			}
		});
    }
 
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
            toggle();
            return true;
         
        }
        return super.onOptionsItemSelected(item);
    }
 
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
     
 
    public void fragmentReplace(int reqNewFragmentIndex) {
          
        Fragment newFragment = null;
  
        newFragment = getFragment(reqNewFragmentIndex);
  
        final FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();
  
        transaction.replace(R.id.fragment_mainContainer, newFragment);
  
        getSlidingMenu().showContent();
        transaction.commit();
    }
     
    private Fragment getFragment(int idx) {
        Fragment newFragment = null;
  
        switch (idx) {
        case 0:
            newFragment = new Fragment_Message();
            break;
        case 1:
            newFragment = new Fragment_Browse();
            break;
        case 2:
            newFragment = new Fragment_Travel();
            break;
        case 3:
            newFragment = new Fragment_Friends();
            break;
        case 4:
            newFragment = new Fragment_Settings();
            break;
        case 5:
            newFragment = new Fragment_Feedback();
            break;
        default:
            break;
        }
  
        return newFragment;
    }
}