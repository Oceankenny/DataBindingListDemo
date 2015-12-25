package com.example.listdata;

import java.util.ArrayList;

import android.graphics.Bitmap;

/**
 * 
 * @author Haiyang
 *
 */
public class PhotoAttach {
	private ArrayList<Bitmap> bitmaps = new ArrayList<Bitmap>();
	
	public void addBitmap(Bitmap bp) {
		bitmaps.add(bp);
	}

	public void removeBitmap(Bitmap bp) {
		bitmaps.remove(bp);
	}

	public Bitmap gtBitmap(int index) {
		if (!bitmaps.isEmpty()) {
			return bitmaps.get(index);
		} else {
			return null;
		}
	}

	public void clear() {
		bitmaps.clear();
		System.gc();
	}
}
