package com.example.listdata;

import android.graphics.Bitmap;

public class MockData {
	private String userName;
	private Bitmap bitmap;
	private String message;
	private Object attachment;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Bitmap getUserLogoUrl() {
		return bitmap;
	}

	public void setUserLogoUrl(Bitmap bitmap) {
		this.bitmap = bitmap;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getAttachment() {
		return attachment;
	}

	public void setAttachment(Object attachment) {
		this.attachment = attachment;
	}

}
