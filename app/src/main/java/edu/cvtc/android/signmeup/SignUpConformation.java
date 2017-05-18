package edu.cvtc.android.signmeup;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by Greg on 5/17/2017.
 */

public class SignUpConformation extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_sign_up_conformation);

        final TextView conformationTextView = (TextView) findViewById(R.id.conformationTextView);

        final Bundle bundle = getIntent().getExtras();

        if (null == bundle) {

            conformationTextView.setText("Error: Bundle was null...");

        } else {

            final String firstName = bundle.getString("firstName");
            final String lastName = bundle.getString("lastName");
            final String phone = bundle.getString("phone");
            final String email = bundle.getString("email");

            final String iOS = bundle.getString("iOSDevelopment");
            final String android = bundle.getString("androidDevelopment");
            final String php = bundle.getString("phpDevelopment");


            if (null == firstName || firstName.isEmpty()
                    || null == lastName || lastName.isEmpty()
                    || null == phone || phone.isEmpty()
                    || null == email || email.isEmpty()) {


                conformationTextView.setText("Error: Missing information...");

            } else {

                conformationTextView.setText("Hello " + firstName
                        + " " + lastName + "! Thank you for confirming "
                        + "you will be coming to my event(s). You will receive "
                        + "a conformation email at " + email + " shortly.");

            }

        }

    }

}
