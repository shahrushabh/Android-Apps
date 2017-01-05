package group6.coms309.cysell;
/**
 * Created by Rushabh.
 */

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// Still needs to implement the AsyncTask. This will result in fetching the data faster than the traditional methods.
public class DatabaseConnecter{
    // The listing fetched from the database.
    private ArrayList<Listing> jsonListing;
    // The requestqueue for sending requests.
    private RequestQueue requestQueue;

    // Username is used to create connection between respective user to the database.
    private String user = "";
    // Password is used to create connection between respective user to the database.
    private String pass = "";

    /**
     * Constructor that takes credentials to be used for logging on to database.
     * @param context : use 'getApplicationContext()' for this parameter.
     */
    public DatabaseConnecter(Context context)
    {
        requestQueue =  Volley.newRequestQueue(context);
        jsonListing = new ArrayList<Listing>();
        login(user,pass);
    }

    /**
     * Attempt to login with given user inputs.
     * @param u - User Name
     * @param p - Password
     */
    public void login(final String u, final String p)
    {
        user = u;
        pass = p;
        String serverUrl = "http://proj-06-cs.iastate.edu/Users/user_login.php";
        StringRequest postRequest = new StringRequest(Request.Method.POST, serverUrl,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        error.printStackTrace();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", u);
                params.put("password", p);
                params.put("isNewUser", "0");
                return params;
            }
        };
        requestQueue.add(postRequest);
    }

    /**
     * Registration for new user. All fields are required.
     * @param fn - First Name
     * @param ln - Last Name
     * @param un - User Name
     * @param pw - Password
     * @param em - Email Address.
     */
    public void registerNewUser(final String fn, final String ln, final String un, final String pw, final String em)
    {
        String serverUrl = "http://proj-309-06.cs.iastate.edu/Users/user_login.php";
        StringRequest postRequest = new StringRequest(Request.Method.POST, serverUrl,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        error.printStackTrace();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<String, String>();
                params.put("firstname", fn);
                params.put("lastname", ln);
                params.put("username", un);
                params.put("password", pw);
                params.put("email", em);
                params.put("isNewUser", "1");
                return params;
            }
        };
        requestQueue.add(postRequest);
    }

    /**
     * Gets the listings of all the tickets using JSONArrayRequest.
     * @return : Returns an arraylist containg all the listings fetched from server.
     */
    public ArrayList<Listing> getTickets()
    {
        String serverUrl = "http://proj-309-06.cs.iastate.edu/Listings/db_query.php";
        jsonListing.clear();
        JsonArrayRequest req = new JsonArrayRequest(serverUrl,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            // Parsing json array response
                            // loop through each json object
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject ticket = response.getJSONObject(i);
                                jsonListing.add(new Listing(ticket.get("Opponent").toString(),
                                                             ticket.get("Price").toString(),
                                                             ticket.get("Date").toString(),
                                                             ticket.get("Section").toString(),
                                                             ticket.get("Seat").toString(),
                                                             ticket.get("Sport").toString(),
                                                             ticket.get("Description").toString(),
                                                             ticket.get("Biddable").toString()));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                }
        });
        requestQueue.add(req);
        return jsonListing;
    }

    /**
     * Makes an attempt to store given listing in the database. Will cause error if this partiular listing already exists!
     * @param newListing - The listing to be stored in the database.
     */
    public void insertListing(final Listing newListing)
    {
        String serverUrl = "http://proj-309-06.cs.iastate.edu/Listings/db_insert.php";
        StringRequest postRequest = new StringRequest(Request.Method.POST, serverUrl,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        error.printStackTrace();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Opponent", newListing.getOpponent());
                params.put("Price", newListing.getPrice());
                params.put("Date", newListing.getDate());
                params.put("Section", newListing.getSection());
                params.put("Seat", newListing.getSeat());
                params.put("Sport", newListing.getSport());
                params.put("Biddable", newListing.getBiddable());
                return params;
            }
        };
        requestQueue.add(postRequest);
    }
}
