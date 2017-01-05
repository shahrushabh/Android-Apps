package group6.coms309.cysell;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class purchaseScreen extends AppCompatActivity {


    List<Listing> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_screen);

        //TODO import strings for game, location, date, time, seller name, quantity avail
        TextView game = (TextView) findViewById(R.id.textView12);
        TextView location = (TextView) findViewById(R.id.textView13);
        TextView date = (TextView) findViewById(R.id.textView18);
        TextView time = (TextView) findViewById(R.id.textView19);
        TextView sellerName = (TextView) findViewById(R.id.textView16);
        TextView qtyAvail = (TextView) findViewById(R.id.textView22);
        //TODO set variable values to imported strings


        //TODO link Contact button with messages activity
        //TextView textView = (TextView) findViewById(R.id.textView23);

        /**int price = 0;
         //retrieves int value from number picker
         Intent intent = getIntent();
         if (intent != null) {
         if (intent.getStringExtra("activity").equals("purchaseScreen")) {
         price = intent.getIntExtra("offer", 1);
         textView.setText(price + "");
         }
         }
         **/
        //sets asking price to offer submitted by user


        //takes user to submit offer screen when button is pressed
        /**final Button button = (Button) findViewById(R.id.button4);
         button.setOnClickListener(new View.OnClickListener() {


         public void onClick(View v) {
         Intent myIntent = new Intent(purchaseScreen.this, submitOfferScreen.class);
         purchaseScreen.this.startActivity(myIntent);
         }
         });
         **/
        int offer = 0; //pass this variable to other activities
        boolean offerOption = false; //TODO imported from another activity
        int askingPrice = 10; //TODO will also be imported
        final NumberPicker noPicker = (NumberPicker) findViewById(R.id.numberPicker2);
        if (offerOption == false) { //if seller turned accepting offers off
            TextView sellerDisabled = (TextView) findViewById(R.id.sellerDisabledOffers);
            sellerDisabled.setVisibility(sellerDisabled.VISIBLE); //show "Seller has disabled offers."
            noPicker.setValue(askingPrice);
            noPicker.setMinValue(askingPrice); //Don't allow buyer to make offer
            noPicker.setMaxValue(askingPrice);
            offer = askingPrice;
        }
        else {
            noPicker.setValue(askingPrice);
            noPicker.setMinValue(1); //change to seller's starting asking price
            noPicker.setMaxValue(askingPrice); //can't offer more than seller's asking price
            noPicker.setWrapSelectorWheel(false);
            offer = noPicker.getValue();
        }
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_purchase_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){
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