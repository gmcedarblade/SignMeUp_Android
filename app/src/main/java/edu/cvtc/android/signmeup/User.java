package edu.cvtc.android.signmeup;

/**
 * Created by Greg on 5/17/2017.
 */

public class User {

    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String PHONE = "phone";
    public static final String EMAIL = "email";

    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private long id;

    public User() {

        firstName = "";
        lastName = "";
        phone = "";
        email = "";


    }

    public User(final String firstName, final String lastName, final String phone, final String email) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;

    }

    public User(final String firstName, final String lastName, final String phone, final String email, final long id) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }


}
