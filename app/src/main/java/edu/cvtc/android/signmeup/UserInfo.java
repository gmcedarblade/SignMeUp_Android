package edu.cvtc.android.signmeup;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class UserInfo extends AppCompatActivity implements View.OnClickListener{

    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText phoneNumberEditText;
    private EditText emailAddressEditText;

    private CheckBox iOSDevelopment;
    private CheckBox androidDevelopment;
    private CheckBox phpDevelopment;

    private Button signUpButton;

    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String PHONE = "phone";
    private static final String EMAIL = "email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        firstNameEditText = (EditText) findViewById(R.id.userFirstNameEditText);
        lastNameEditText =  (EditText) findViewById(R.id.userLastNameEditText);
        phoneNumberEditText = (EditText) findViewById(R.id.userPhoneEditText);
        emailAddressEditText = (EditText) findViewById(R.id.userEmailEditText);

        iOSDevelopment = (CheckBox) findViewById(R.id.iOSDevelopmentEvent);
        androidDevelopment = (CheckBox) findViewById(R.id.androidDevelopmentEvent);
        phpDevelopment = (CheckBox) findViewById(R.id.phpDevelopmentEvent);

        signUpButton = (Button) findViewById(R.id.signMeUpButton);

        signUpButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        final String firstName = firstNameEditText.getText().toString();
        final String lastName = lastNameEditText.getText().toString();
        final String phone = phoneNumberEditText.getText().toString();
        final String email = emailAddressEditText.getText().toString();

        final boolean iOSDev = iOSDevelopment.isChecked();
        final boolean androidDev = androidDevelopment.isChecked();
        final boolean phpDev = phpDevelopment.isChecked();

//        boolean checked = ((CheckBox) view).isChecked();


        if (firstName != null && !firstName.isEmpty()
                && lastName != null && !lastName.isEmpty()
                && phone != null && !phone.isEmpty()
                && email != null && !email.isEmpty()
                && (iOSDev == true || androidDev == true || phpDev == true)) {

            final Intent intent = new Intent(getApplicationContext(), SignUpConformation.class);

            intent.putExtra("firstName", firstName);
            intent.putExtra("lastName", lastName);
            intent.putExtra("phone", phone);
            intent.putExtra("email", email);

            switch (view.getId()) {
                case R.id.iOSDevelopmentEvent:
                    if (true) {
                        intent.putExtra("iOSDevelopment", iOSDev);
                    } else {
                        //do nothing
                    }
                    break;
                case R.id.androidDevelopmentEvent:
                    if (true) {
                        intent.putExtra("androidDevelopment", androidDev);
                    } else {
                        // do nothing
                    }
                    break;
                case R.id.phpDevelopmentEvent:
                    if (true) {
                        intent.putExtra("phpDevelopment", phpDev);
                    } else {
                        // do nothing
                    }
                    break;
                default:
                    break;
            }

            startActivity(intent);

        } else {

            Toast.makeText(getApplicationContext(), "All fields must be completed...", Toast.LENGTH_SHORT).show();

        }

        hideSoftKeyboard();

    }


    @Override
    protected void onPause() {

        super.onPause();

        final SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        preferences.edit().putString(FIRST_NAME, firstNameEditText.getText().toString()).commit();
        preferences.edit().putString(LAST_NAME, lastNameEditText.getText().toString()).commit();
        preferences.edit().putString(PHONE, phoneNumberEditText.getText().toString()).commit();
        preferences.edit().putString(EMAIL, emailAddressEditText.getText().toString()).commit();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);

        outState.putString(FIRST_NAME, firstNameEditText.getText().toString());
        outState.putString(LAST_NAME, lastNameEditText.getText().toString());
        outState.putString(PHONE, phoneNumberEditText.getText().toString());
        outState.putString(EMAIL, emailAddressEditText.getText().toString());

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {

        super.onRestoreInstanceState(savedInstanceState);

        final String firstNameText = savedInstanceState.getString(FIRST_NAME, "");
        firstNameEditText.setText(firstNameText);
        final String lastNameText = savedInstanceState.getString(LAST_NAME, "");
        lastNameEditText.setText(lastNameText);
        final String phoneText = savedInstanceState.getString(PHONE, "");
        phoneNumberEditText.setText(phoneText);
        final String emailText = savedInstanceState.getString(EMAIL, "");
        emailAddressEditText.setText(emailText);

    }

    private void hideSoftKeyboard() {

        final View view = getCurrentFocus();
        if (view != null) {

            final InputMethodManager keyboard = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            keyboard.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

}
