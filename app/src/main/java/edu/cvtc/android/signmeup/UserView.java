package edu.cvtc.android.signmeup;

import android.content.Context;
import android.support.annotation.IdRes;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Greg on 5/18/2017.
 */

public class UserView extends LinearLayout {

    private TextView userTextView;

    private User user;

    private OnUserChangeListener onUserChangeListener;

    public UserView(final Context context, final User user) {

        super(context);

        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.users_view, this, true);

        userTextView = (TextView) findViewById(R.id.usersInfoTextView);

        setUser(user);

    }

    public User getUser() { return user; }

    public void setUser(final User user) {

        this.user = user;

        userTextView.setText(user.getFirstName() + " " + user.getLastName() + " " + user.getPhone() + " " + user.getEmail());

        requestLayout();

    }

    public void setOnUserChangeListener(final OnUserChangeListener onUserChangeListener) {

        this.onUserChangeListener = onUserChangeListener;

    }


    public void onCheckedChanged(TextView firstName, TextView lastName, TextView phone, TextView email, @IdRes int checkedId) {

        switch (checkedId) {

            case R.id.userFirstNameEditText:
                user.setFirstName(User.FIRST_NAME);
                notifyOnUserChangeListener();
                break;
            case R.id.userLastNameEditText:
                user.setLastName(User.LAST_NAME);
                notifyOnUserChangeListener();
                break;
            case R.id.userPhoneEditText:
                user.setPhone(User.PHONE);
                notifyOnUserChangeListener();
                break;
            case R.id.userEmailEditText:
                user.setEmail(User.EMAIL);
                notifyOnUserChangeListener();
                break;

        }

    }

    private void notifyOnUserChangeListener() {

        if(onUserChangeListener != null) {

            onUserChangeListener.onUserChanged(this,user);

        }

    }

    public interface OnUserChangeListener {

        void onUserChanged(UserView view, User user);

    }

}
