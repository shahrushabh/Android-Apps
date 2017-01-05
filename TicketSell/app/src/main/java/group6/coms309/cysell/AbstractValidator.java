package group6.coms309.cysell;


import java.util.Dictionary;
import java.util.Hashtable;

public abstract class AbstractValidator {

    private Dictionary<String, String> errors = new Hashtable<>();

    public abstract void validate(Form form);

    public boolean hasErrors() {
        return errors != null && !errors.isEmpty();
    }

}