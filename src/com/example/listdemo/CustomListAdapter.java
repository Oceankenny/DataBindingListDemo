package com.example.listdemo;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.binder.AttachmentBinder;
import com.example.binder.HeaderBinder;
import com.example.binder.IBinder;
import com.example.binder.MessageBinder;
import com.example.listdata.MockData;
import com.example.views.AttachmentView;
import com.example.views.HeaderView;
import com.example.views.MessageView;
import com.example.views.Separator;

import java.util.ArrayList;

/**
 *
 * @author Haiyang
 *
 */

public class CustomListAdapter extends BaseAdapter {
    private final static String TAG = "ListDemo";
    private final static boolean DEBUG = true;

    private final static int TYPE_HEADER = 0;
    private final static int TYPE_MESSAGE = TYPE_HEADER + 1;
    private final static int TYPE_ATTACHMENT = TYPE_HEADER + 2;
    private final static int TYPE_SEPARATOR = TYPE_HEADER + 3;
    private final static int SUB_STORY_NUM = TYPE_SEPARATOR + 1;
    
    private Context mContext;

    private ArrayList<MockData> mData = new ArrayList<MockData>();

    public CustomListAdapter(Context context, ArrayList<MockData> data) {
        this.mContext = context;
        this.mData = data;
    }

    public void addData(MockData data) {
    	this.mData.add(data);
    	this.notifyDataSetChanged();
    }

    public void removeData(MockData data) {
    	this.mData.remove(data);
    	this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
    	if(DEBUG) Log.d(TAG, "getCount");
        return mData.size() * SUB_STORY_NUM;
    }

    @Override
    public Object getItem(int position) {
    	if(DEBUG) Log.d(TAG, "getItem, position: " + position);
    	if (position % SUB_STORY_NUM != TYPE_SEPARATOR) {
    		return mData.get(position/SUB_STORY_NUM);
    	} else {
    		return null;
    	}
    }

    @Override
    public long getItemId(int position) {
    	if(DEBUG) Log.d(TAG, "getItemId, position: " + position);
        return position/SUB_STORY_NUM;
    }

    @Override
    public int getViewTypeCount() {
    	if(DEBUG) Log.d(TAG, "getViewTypeCount");
        return SUB_STORY_NUM;
    }
    
    @Override
    public int getItemViewType(int position) {
    	if(DEBUG) Log.d(TAG, "getItemViewType, position: " + position);
        int ret = -1;
        switch (position % SUB_STORY_NUM) {
            case 0: ret = TYPE_HEADER; break;
            case 1: ret = TYPE_MESSAGE; break;
            case 2: ret = TYPE_ATTACHMENT; break;
            case 3: ret = TYPE_SEPARATOR; break;
            default: ret = -1; break;
        }
        return ret;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
    	if(DEBUG) Log.d(TAG, "getView, position: " + position);
        int itemViewType = getItemViewType(position);
        if (itemViewType == -1) {
        	if(DEBUG) Log.w(TAG, "get view failed. position: " + position);
            return null;
        }

        MockData data = (MockData)getItem(position);
        IBinder binder = null;
        
        if (convertView == null) {
        	if(DEBUG) Log.d(TAG, "convertView is null");
	        switch (itemViewType) {
	            case TYPE_HEADER: 
	            	convertView = new HeaderView(this.mContext);
	            	binder = new HeaderBinder();
	            	break;
	            case TYPE_MESSAGE:
	            	convertView = new MessageView(this.mContext);
	            	binder = new MessageBinder();
	            	break;
	            case TYPE_ATTACHMENT:
	            	convertView = new AttachmentView(this.mContext);
	            	binder = new AttachmentBinder();
	            	break;
	            case TYPE_SEPARATOR:
	            	convertView = new Separator(this.mContext);
	            default:
	            	Log.w(TAG,"invalid view type");
	            	break;
	        }
        } else {   
	        binder = (IBinder)convertView.getTag();
        }
        
        //call binder to bind data and view
        if (binder instanceof HeaderBinder) {
				((HeaderBinder) binder).bind((HeaderView)convertView, data);
            	((HeaderBinder) binder).prepare();
        } else if (binder instanceof MessageBinder) {
				((MessageBinder) binder).bind((MessageView)convertView, data);
            	((MessageBinder) binder).prepare();
        } else if (binder instanceof AttachmentBinder) {    	
				((AttachmentBinder) binder).bind((AttachmentView)convertView, data);
            	((AttachmentBinder) binder).prepare();
        } else {
        	Log.w(TAG, "invalid instance");
        }

        convertView.setTag(binder);
        return convertView;
    }
}
