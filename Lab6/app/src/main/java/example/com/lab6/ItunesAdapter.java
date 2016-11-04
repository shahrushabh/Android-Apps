package example.com.lab6;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * This class is used to represent the information in each row of the applications list view
 */
public class ItunesAdapter extends ArrayAdapter<ItunesRecord>{

	private Context context;
	private int layoutResourceId;
	private List<ItunesRecord> data = null;

	/**
	 * Constructor for a new ItunesAdapter
	 * @param context the current context 
	 * @param layoutResourceId the id to represent the layout
	 * @param data a list of information for each row in the list view
	 */
	public ItunesAdapter(Context context, int layoutResourceId, List<ItunesRecord> data) {
		super(context, layoutResourceId, data);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = data;
	}

	/* (non-Javadoc)
	 * @see android.widget.ArrayAdapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@SuppressLint("DefaultLocale")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		ItunesHolder holder = null;

		if (row == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);

			//create a new ItunesHolder and set it to the fields the row in the list view
			holder = new ItunesHolder();

			//TODO set the holder view id's
			holder.itunesSongTitle = (TextView) row.findViewById(R.id.song);
			holder.itunesAlbumName = (TextView) row.findViewById(R.id.album);

			row.setTag(holder);
		} else {
			holder = (ItunesHolder) row.getTag();
		}
		
		//get the current position from the list
		ItunesRecord itunesRecord = data.get(position);

		//TODO set the text for the row
		String album = "<b>Album: </b>" + itunesRecord.getAlbumTitle();
		String song = "<b>Song: </b>" + itunesRecord.getAlbumTitle();
		holder.itunesAlbumName.setText(Html.fromHtml(album));
		holder.itunesSongTitle.setText(Html.fromHtml(song));

		return row;
	}

	/**
	 * A class to represent the fields in the row layout
	 */
	static class ItunesHolder
	{
		TextView itunesAlbumName;
		TextView itunesSongTitle;
	}
}