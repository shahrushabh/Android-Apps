package group6.coms309.cysell;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends Activity {

    // ArrayList to store the listings fetched.
    private ArrayList<Listing> listingArray;
    // Database connector instance for creating connection.
    private ListView ticketListView;
    // Custom Adapter
    private TaylorArrayAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();
        DatabaseConnecter databaseConnect = new DatabaseConnecter(getApplicationContext());
        listingArray = databaseConnect.getTickets();
        ticketListView = (ListView) findViewById(R.id.lstViewTickets);
        customAdapter = new TaylorArrayAdapter(this, R.layout.list_view_setup, listingArray);//My custom adapter
        ticketListView.setAdapter(customAdapter);

        //When you click on each item it should load that listing in a full page
        ticketListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {//needs to handle clicks from the user

                Intent i = new Intent(getApplicationContext(), purchaseScreen.class);

                //Need to bundle all the data together to send off
                Bundle extras = new Bundle();
                extras.putString("opponent", listingArray.get(position).getOpponent());
                extras.putString("price", listingArray.get(position).getPrice());
                extras.putString("date", listingArray.get(position).getDate());
                extras.putString("section", listingArray.get(position).getSection());
                extras.putString("seat", listingArray.get(position).getSeat());
                extras.putString("sport", listingArray.get(position).getSport());
                extras.putString("biddable", listingArray.get(position).getBiddable());
                i.putExtras(extras);

                startActivity(i);

                Toast.makeText(getApplicationContext(), Long.toString(id), Toast.LENGTH_SHORT).show();
            }
        });
        pDialog.hide();
    }

    @Override
    protected void onResume() { //Resume..
        super.onResume();
    }

    @Override
    protected void onDestroy() {//And destroy
        super.onDestroy();
    }


    private void LoadListings()
    {
        //Update User Credentials
        DatabaseConnecter databaseConnect = new DatabaseConnecter(getApplicationContext());
        ArrayList<Listing> tempList = databaseConnect.getTickets();
        ProgressDialog pDialog = new ProgressDialog(this);          // Shows a in progress dialog briefly.
        pDialog.setMessage("Loading...");
        pDialog.show();
        for(Listing l: tempList)
        {
            if(!listingArray.contains(l)) {
                listingArray.add(l);
            }
        }
        ticketListView = (ListView) findViewById(R.id.lstViewTickets);
        customAdapter = new TaylorArrayAdapter(this, R.layout.list_view_setup, listingArray);//My custom adapter
        ticketListView.setAdapter(customAdapter);
        pDialog.hide();
    }

    public void imgbtnSearch(View view) {
        //TODO create search algo
        //Haven't decided on how were going to search yet. By ticket/game/price?
    }

    public void ticketPageLoad(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void addNewScreen(View view) {
        //TODO Load New Screen page
        Intent intent = new Intent(this, CreateListingActivity.class);
        startActivity(intent);
    }

    public void messagesLoad(View view) {
        //TODO Load message page
        //Intent intent = new Intent(this, Messages.class);
        //startActivity(intent);
    }

    public void drop_sort(View view){
        //TODO need to create sort logic
        //need to create a drop down window where user can select from a few predefined options
    }

    //Refreshing will clear the table and generate data again.
    public void refresh_the_page (View view){
        LoadListings();
    }



    //Google automated code.
    //Leaving this for now, may delete.

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
