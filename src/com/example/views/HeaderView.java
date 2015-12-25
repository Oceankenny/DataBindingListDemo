package com.example.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.listdemo.R;

/**
 * 
 * @author Haiyang
 *
 */
public class HeaderView extends CustomView {
	private TextView nameView;
	private ImageView iconView;
	
	public HeaderView(Context context) {
		super(context);

		inflate(context, R.layout.header, this);
		
		nameView = (TextView)findViewById(R.id.name);
		iconView = (ImageView)findViewById(R.id.icon);
	}

	public void setName(String name) {
		nameView.setText(name);
	}

	public void setIcon(Bitmap icon) {
		iconView.setImageBitmap(icon);
	}

	public void setIconClickListener(OnClickListener listener) {
		this.iconView.setOnClickListener(listener);
	}

	public void setNameClickListener(OnClickListener listener) {
		this.nameView.setOnClickListener(listener);
	}
}
