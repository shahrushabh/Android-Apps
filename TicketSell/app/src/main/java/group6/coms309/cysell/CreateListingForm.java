package group6.coms309.cysell;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

public class CreateListingForm implements Form {

    private String title;
    private String price;
    private String section;
    private String seat;
    private String date;
    private String game;
    private String description;
    private boolean offers;

    public CreateListingForm(String title, String price, String section, String seat, String date, String game, String description, boolean offers) {
        this.title = title;
        this.price = price;
        this.section = section;
        this.seat = seat;
        this.date = date;
        this.game = game;
        this.description = description;
        this.offers = offers;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public boolean isOffers() {
        return offers;
    }

    public void setOffers(boolean offers) {
        this.offers = offers;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        Bundle bundle = new Bundle();

        bundle.putString("title", title);
        bundle.putString("game", game);
        bundle.putString("description", description);
        bundle.putBoolean("acceptOffers", offers);
        bundle.putString("price", price);

        dest.writeBundle(bundle);
    }

    public static final Parcelable.Creator<CreateListingForm> CREATOR = new Creator<CreateListingForm>() {
        @Override
        public CreateListingForm createFromParcel(Parcel source) {
            Bundle bundle = source.readBundle();

            String title = bundle.getString("title");
            String game = bundle.getString("game");
            String price = bundle.getString("price");
            String section = bundle.getString("section");
            String seat = bundle.getString("seat");
            String date = bundle.getString("date");
            String description = bundle.getString("description");
            boolean acceptOffers = bundle.getBoolean("acceptOffers");

            return new CreateListingForm(title, price, section, seat, date,                  game, description, acceptOffers);
        }

        @Override
        public CreateListingForm[] newArray(int size) {
            return new CreateListingForm[size];
        }
    };
}