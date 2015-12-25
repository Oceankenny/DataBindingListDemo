package com.example.binder;

import com.example.binder.IBinder;

/**
 * 
 * @author Haiyang
 *
 * @param <V>
 * @param <T>
 */
public abstract class Binder<V, T> implements IBinder {
	protected final static boolean DEBUG = true;
	protected final String TAG = "ListDemo|Binder";
	
	protected V mView = null ;
	protected T mData = null;

	public abstract void prepare();

	public void bind(V view, T data) {
		this.mData = data;
		this.mView = view;
	}

	public void unbind() {
		if (mData != null) {
			this.mData = null;
		}
		
		if (mView != null) {
			this.mView = null;
		}
	}

}
