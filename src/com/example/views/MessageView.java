package com.example.views;

import android.content.Context;
import android.widget.TextView;

import com.example.listdemo.R;

/**
 * 
 * @author Haiyang
 *
 */
public class MessageView extends CustomView {
	private TextView textView;
	
    public MessageView(Context context) {
        super(context);
        inflate(context, R.layout.message, this);
        textView = (TextView)findViewById(R.id.body);
    }

    public void setMessage(String msg) {
    	textView.setText(msg);
    }
}
