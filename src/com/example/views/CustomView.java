package com.example.views;
import com.example.binder.IBinder;

import android.content.Context;
import android.widget.RelativeLayout;

/**
 * 
 * @author Haiyang
 *
 */
public class CustomView extends RelativeLayout {
	protected final static boolean DEBUG = true;
	protected final String TAG = "ListDemo|View";
	
	private IBinder mBinder;
	
	public CustomView(Context context) {
		super(context);
	}

	public void setBinder(IBinder binder) {
		this.mBinder = binder;
	}

	public IBinder getBinder() {
		return mBinder;
	}
}
