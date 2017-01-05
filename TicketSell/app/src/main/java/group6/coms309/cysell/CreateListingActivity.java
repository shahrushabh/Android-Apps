package group6.coms309.cysell;

        import android.app.ProgressDialog;
        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.CheckBox;
        import android.widget.EditText;
        import android.widget.Spinner;
        import android.widget.Toast;

        import com.mobsandgeeks.saripaar.ValidationError;
        import com.mobsandgeeks.saripaar.Validator;
        import com.mobsandgeeks.saripaar.annotation.*;

        import java.util.List;

public class CreateListingActivity extends AppCompatActivity implements Validator.ValidationListener {

    @NotEmpty
    @DecimalMin(value = 1.00, message = "Must be > $1.00")
    @DecimalMax(value = 100000.00, message = "Must be < $100000.00")
    private EditText priceField;

    private EditText sectionField;

    private EditText seatField;

    private EditText descriptionField;

    private Spinner gameField;

    private CheckBox offersField;

    private Validator validator;

    private Spinner eventTypeField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        validator = new Validator(this);
        validator.setValidationListener(this);

        setContentView(R.layout.activity_create_listing);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.menu_create_listing, menu);
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

    public void submitForm(View button) {

        eventTypeField = (Spinner) findViewById(R.id.eventTypeFied);
        priceField = (EditText) findViewById(R.id.priceField);
        sectionField = (EditText) findViewById(R.id.sectionField);
        seatField = (EditText) findViewById(R.id.seatField);
        gameField = (Spinner) findViewById(R.id.gameField);
        descriptionField = (EditText) findViewById(R.id.descriptionField);
        offersField = (CheckBox) findViewById(R.id.offersField);
        validator.validate();
    }

    @Override
    public void onValidationSucceeded() {

        String event = eventTypeField.getSelectedItem().toString();
        String price = priceField.getText().toString();
        String section = sectionField.getText().toString();
        String seat = seatField.getText().toString();
        String game = gameField.getSelectedItem().toString();
        String description = descriptionField.getText().toString();
        boolean acceptOffers = offersField.isChecked();

        // Attempt to add listing to database.
        ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();
        // Date, Type of Sport will be changed based on user input.
        if(acceptOffers)
        {
            Listing listing = new Listing(game.toString(), price.toString(), "2011-11-19", section, seat, event, description, "1");
            DatabaseConnecter dbconnect = new DatabaseConnecter(getApplicationContext());
            dbconnect.insertListing(listing);
        }
        else
        {
            Listing listing = new Listing(game.toString(), price.toString(), "2011-11-19", section, seat, event, description, "0");
            DatabaseConnecter dbconnect = new DatabaseConnecter(getApplicationContext());
            dbconnect.insertListing(listing);
        }
        pDialog.hide();

        Intent confirmationActivity = new Intent(getBaseContext(), ConfirmCreateListingActivity.class);
        confirmationActivity.putExtra("createListingForm", new CreateListingForm(event, price, section, seat, "2011-11-19", game, description, acceptOffers));
        startActivity(confirmationActivity);
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }
}