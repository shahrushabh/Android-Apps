package group6.coms309.cysell;

import org.apache.commons.lang3.StringUtils;

import java.util.Dictionary;

public class CreateListingFormValidator extends AbstractValidator {

    public CreateListingFormValidator() {
    }

    public void validate(Form form) {
        if (form == null || !(form instanceof CreateListingForm)) {
            return;
        }
        CreateListingForm createListingForm = (CreateListingForm) form;

        validateTitle(createListingForm.getTitle());
        validatePrice(createListingForm.getPrice());
        validateDescription(createListingForm.getDescription());
        validateGame(createListingForm.getGame());
        validateOffers(createListingForm.isOffers());
    }

    public void validateTitle(String title) {
        if (StringUtils.isBlank(title)) {
            // error
        }
    }

    public void validatePrice(String priceString) {
        if (StringUtils.isBlank(priceString)) {
            // error
        }
        double price;
        try {
            price = Double.parseDouble(priceString);
        } catch (NumberFormatException e) {
            // error
        }
    }

    public void validateDescription(String description) {
        // allow empty descriptions or no?
    }

    public void validateGame(String game) {

    }

    public void validateOffers(boolean offers) {

    }


}