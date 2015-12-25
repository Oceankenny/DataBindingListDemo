package com.example.binder;

import android.util.Log;

import com.example.listdata.MockData;
import com.example.views.MessageView;

/**
 * 
 * @author Haiyang
 *
 */
public class MessageBinder extends Binder<MessageView, MockData> {

	@Override
	public void prepare() {
		if (DEBUG) {
			Log.d(TAG, "message is prepared");
		}
		
	}

	@Override
	public void bind(MessageView view, MockData data) {
		super.bind(view, data);
		
		mView.setMessage(data.getMessage());
	}

}
