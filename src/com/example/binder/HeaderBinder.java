package com.example.binder;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.example.listdata.MockData;
import com.example.views.HeaderView;
/**
 * 
 * @author Haiyang
 *
 */
public class HeaderBinder extends Binder<HeaderView, MockData> {

	@Override
	public void prepare() {
		if (mView != null) {
			mView.setIconClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Log.d("ListDemo", "on click");
					Toast.makeText(mView.getContext(), "Icon is clicked!", Toast.LENGTH_SHORT).show();
				}
				
			});

			mView.setNameClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Toast.makeText(mView.getContext(), "Name is clicked!", Toast.LENGTH_SHORT).show();
				}
			});
		}
	}

	@Override
	public void bind(HeaderView view, MockData data) {
		super.bind(view, data);

	    mView.setName(mData.getUserName());
	    mView.setIcon(mData.getUserLogoUrl());
	}

}
