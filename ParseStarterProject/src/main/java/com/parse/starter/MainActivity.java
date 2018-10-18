/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.util.HashMap;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnKeyListener, View.OnClickListener {

    LoginOrSingUp currentText = LoginOrSingUp.LOGIN;
    private EditText username;
    private EditText password;
    private TextView loginOrSignUp;
    private Button loginButton;
    private ImageView instagramLogo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginButton = findViewById(R.id.loginButton);
        loginOrSignUp = findViewById(R.id.loginOrSignUpText);
        instagramLogo = findViewById(R.id.instImage);
        username = findViewById(R.id.login);
        password = findViewById(R.id.password);
        password.setOnKeyListener(this);
        loginOrSignUp.setOnClickListener(this);
        instagramLogo.setOnClickListener(this);



       /* ParseUser user = new ParseUser();

        user.setUsername("nick");
        user.setPassword("dupa");

        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Log.i("ok", "Success");

                } else {
                    e.printStackTrace();
                }
            }
        });*/


        ParseAnalytics.trackAppOpenedInBackground(getIntent());
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
            loginOrSignUpClicked(v);
        }

        return false;
    }

    public void loginOrSignUpClicked(View view) {


        if (!username.getText().toString().isEmpty() && !password.getText().toString().isEmpty() && currentText == LoginOrSingUp.LOGIN) {


            ParseUser.logInInBackground(username.getText().toString(), password.getText().toString(), new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException e) {
                    if (user != null) {
                        Toast.makeText(getApplicationContext(), "Successfully logged in", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "You entered bad credentials", Toast.LENGTH_LONG).show();
                    }
                }
            });

        } else {
            ParseUser user = new ParseUser();

            user.setUsername(username.getText().toString());
            user.setPassword(password.getText().toString());

            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        Toast.makeText(getApplicationContext(), "Successfully create user", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Oops....something went wrong", Toast.LENGTH_LONG).show();

                    }
                }
            });


        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.loginOrSignUpText) {
            if (currentText == LoginOrSingUp.LOGIN) {
                loginOrSignUp.setText(R.string.orLoginText);
                loginButton.setText(R.string.signUpText);
                currentText = LoginOrSingUp.SING_UP;
            } else {

                loginOrSignUp.setText(R.string.orSignUpText);
                loginButton.setText(R.string.loginText);
                currentText = LoginOrSingUp.LOGIN;
            }
        }else if(v.getId() == R.id.instImage || v.getId() == R.id.layoutMain){
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        }
    }

    public enum LoginOrSingUp {

        LOGIN,
        SING_UP

    }

}
