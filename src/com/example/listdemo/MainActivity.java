package com.example.listdemo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ListView;

import com.example.listdata.MockData;
import com.example.listdata.PhotoAttach;
import com.example.listdemo.MainActivity.JsonData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 
 * @author Haiyang
 *
 */
public class MainActivity extends Activity {
	private static final int MOCK_SIZE = 20;

	private ProgressDialog progDlg;
	private final static String fileName = "mockdata.txt";
	
	ListView listView;
	ArrayList<MockData> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progDlg = new ProgressDialog(this);
        progDlg.setMessage("Data is loading...");
        progDlg.show();
        
        new DataThread().start();

        listView = (ListView)findViewById(R.id.list);
        
    }

    private ArrayList<MockData> initData() {
    	ArrayList<MockData> data = new ArrayList<MockData>();
    	for(int i = 0; i < MOCK_SIZE; i++) {
    		MockData dataItem = new MockData();
    		dataItem.setUserLogoUrl(BitmapFactory.decodeResource(getResources(), R.drawable.icon1));
    		dataItem.setUserName("Mocker_" + i);
    		dataItem.setMessage("This is message for " + i);
    		
    		data.add(dataItem);
    	}

    	return data;
    }

    private ArrayList<MockData> initDataFromJson(String json) {
    	ArrayList<MockData> data = new ArrayList<MockData>();
    	
        Type listType = new TypeToken<LinkedList<JsonData>>(){}.getType();
		Gson gson = new Gson();
		LinkedList<JsonData> users = gson.fromJson(json, listType);
		for (Iterator<JsonData> iterator = users.iterator(); iterator.hasNext();) {
			JsonData obj = (JsonData) iterator.next();
			
			MockData dataItem = new MockData();
			dataItem.setUserLogoUrl(BitmapFactory.decodeFile(obj.icon));
			dataItem.setUserName(obj.name);
			dataItem.setMessage(obj.message);
			
			PhotoAttach attachment = new PhotoAttach();
			attachment.addBitmap(BitmapFactory.decodeFile(obj.attachment));
			dataItem.setAttachment(attachment);
			
			data.add(dataItem);
		}

    	return data;
    }

    private String getJson(String fileName) {
    	StringBuilder stringBuilder = new StringBuilder();
    		try {
    			BufferedReader bf = new BufferedReader(new InputStreamReader(getAssets().open(fileName), "UTF-8"));
    			String line;
    			while ((line = bf.readLine()) != null) {
    				stringBuilder.append(line);
    			}
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    		return stringBuilder.toString();
    }


    class DataThread extends Thread {
        @Override
        public void run() {
            //String json = getJson(fileName);
            //data = initDataFromJson(json);
            data = initData();
            dataHandler.sendMessage(dataHandler.obtainMessage());
        }

    }

    @SuppressLint("HandlerLeak")
	private Handler dataHandler = new Handler() {
    	@Override
        public void handleMessage(android.os.Message msg) {
            if (progDlg != null) {
            	progDlg.dismiss();
            }

            CustomListAdapter adapt = new CustomListAdapter(getApplicationContext(), data);
            listView.setAdapter(adapt);
        }
    };

    public static class JsonData {
    	public String name;
    	public String icon;
    	public String message;
    	public String attachment;
    	
    }
}
