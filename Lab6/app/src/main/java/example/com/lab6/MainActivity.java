package example.com.lab6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends ListActivity implements DownloadWebpageTask.ResultHandler{

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button search = (Button)findViewById(R.id.search);
		search.setOnClickListener(new OnClickListener() {

			/* (non-Javadoc)
			 * @see android.view.View.OnClickListener#onClick(android.view.View)
			 */
			public void onClick(View v) {

				//TODO get the username to search for from the activity_main.xml view
				String artist = null;
				artist = ((TextView) findViewById(R.id.username)).getText().toString();
				Log.i("Artist Searched", artist);

				//TODO execute a new DownloadWebpageTask
				new DownloadWebpageTask(MainActivity.this).execute("https://itunes.apple.com/search?term="+artist+"&entity=song&limit=20%22");
			}
		});
	}


	@Override
	public void handleResult(String result) {
		int numRecords = 0;
		JSONObject response = null;
		try{
			response = new JSONObject(result);
			Log.i("Response is: ",response.toString());
		}catch(JSONException e){
			e.printStackTrace();
		}

		ArrayList<ItunesRecord> records = new ArrayList<>();
		try{
			numRecords = response.getInt("resultCount");
			JSONArray res = response.getJSONArray("results");
			for(int i=0; i<numRecords; i++){
				response = res.getJSONObject(i);
				ItunesRecord r = new ItunesRecord(response.getString("collectionName"),response.getString("trackName"));
				records.add(r);
				Log.i("Title: ", r.getSongTitle());
			}
		}catch(JSONException e){
			e.printStackTrace();
		}
		ItunesAdapter ia = new ItunesAdapter(this,R.layout.record_layout,records);
		setListAdapter(ia);
//		setListAdapter(new ItunesAdapter(this,R.layout.record_layout,records));
	}
}
