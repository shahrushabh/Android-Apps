package group6.coms309.cysell;

/**
 * Created by Taylor on 10/7/2015.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;


//My arrayadapter class that generates the listView
public class TaylorArrayAdapter extends ArrayAdapter<Listing> {

    public TaylorArrayAdapter(Context context, int resource, List<Listing> items) {
        super(context, resource, items);
    }

    //This will generate the list. It is a built in thing that is called every time
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = convertView;

        if (rowView == null) {
            LayoutInflater inflater;
            inflater = LayoutInflater.from(getContext());
            rowView = inflater.inflate(R.layout.list_view_setup, null);//set it to my custom view
        }

        Listing pos = getItem(position);

        //Go through my items and generate
        if (pos != null) {
            TextView sportSel= (TextView) rowView.findViewById(R.id.txtSport);
            TextView opponentSel= (TextView) rowView.findViewById(R.id.txtOpponent);
            TextView sectionSel= (TextView) rowView.findViewById(R.id.txtSection);
            TextView priceSel= (TextView) rowView.findViewById(R.id.txtPrice);
            TextView dateSel = (TextView) rowView.findViewById(R.id.txtDate);
            TextView bidSel = (TextView) rowView.findViewById(R.id.txtBiddable);

            //Checks to see if each one is empty, and if it is then fill it with data
            if (sportSel != null) {
                sportSel.setText("Sport: " + pos.getSport());
            }

            if (opponentSel != null) {
                opponentSel.setText("Game vs " + pos.getOpponent());
            }

            if (sectionSel != null) {
                if(sectionSel.getText() != "student" || sectionSel.getText() != "Student")
                    sectionSel.setText("Section: " + pos.getSection() + " Seat: " + pos.getSeat());
            }else{
                sectionSel.setText(pos.getSection() + "section ticket.");
            }

            if (priceSel != null) {
                priceSel.setText("Price: $" + pos.getPrice());
            }

            if( dateSel != null){
                dateSel.setText("Date: " + pos.getDate());
            }

                if( bidSel != null){
                    if(pos.getBiddable() == "1"){
                        bidSel.setText("Bidding Enabled");
                    }else{
                        bidSel.setText("No bids");
                    }
            }
        }

        return rowView;
    }
}