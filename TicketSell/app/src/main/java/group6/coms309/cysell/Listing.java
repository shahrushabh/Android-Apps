package group6.coms309.cysell;

/**
 * Created by Rushabh on 10/11/2015.
 */
public class Listing {
    // The opponent for this listing.
    private String op;
    // The price of the listing.
    private String pr;
    // The date of the listing.
    private String dt;
    // The section that contains the given seat for this listing.
    private String sc;
    // The seat of this listing.
    private String st;
    // The type of sport.
    private String sp;
    // The boolean to tell whether ticket is biddable.
    private String bd;
    // Detailed discription of this lising.
    private String ds;
    /**
     *
     * @param Opponent : The opponent for this listing.
     * @param price : The price of the listing.
     * @param date : The date of the listing. Date Format must be exactly YYYY-MM-DD
     * @param section : The section that contains the given seat for this listing.
     * @param seat : The seat of this listing.
     * @param event : The type of sport this is.
     * @param discription : Detailed discription of this event.
     * @param biddable : Decides whether the ticket is biddable. Accepted values are '0' or '1' ONLY.
     */
    public Listing(String Opponent, String price, String date, String section, String seat, String event, String discription, String biddable)
    {
        op = Opponent;
        pr = price;
        dt = date;
        sc = section;
        st = seat;
        sp = event;
        ds = discription;
        bd = biddable;
    }

    // Get the opponent for this listing.
    public String getOpponent()
    {
        return op;
    }
    // Get the price for this listing.
    public String getPrice()
    {
        return pr;
    }
    // Get the date for this listing.
    public String getDate()
    {
        return dt;
    }
    // The section that contains the given seat for this listing.
    public String getSection()
    {
        return sc;
    }
    // // The seat of this listing.
    public String getSeat()
    {
        return st;
    }
    // Get the type of event
    public String getSport()
    {
        return sp;
    }
    // Get the status whether biddable or not.
    public String getBiddable()
    {
        return bd;
    }
    // Get discription of this event.
    public String getDescription() { return ds; }
}