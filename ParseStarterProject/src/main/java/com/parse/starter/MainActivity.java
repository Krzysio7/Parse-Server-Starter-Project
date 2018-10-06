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
import android.view.View;
import android.widget.Switch;

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

import java.util.HashMap;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

  /*      ParseObject score = new ParseObject("Score");
        score.put("username", "Nick");
        score.put("score", 45);
        score.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null){
                    Log.i("Success","Score was saved");
                }
                else{
                    e.printStackTrace();
                }
            }
        });*/
/*
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Score");
query.getInBackground("TMt6x02kOc", new GetCallback<ParseObject>() {
    @Override
    public void done(ParseObject object, ParseException e) {
        if(e == null && object != null){



            Log.i("username",object.getString("username"));
            Log.i("score",Integer.toString(object.getInt("score")));
        }
    }
});
*/

        ParseObject tweet = new ParseObject("Tweet");
        tweet.put("username", "Ken");
        tweet.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Log.i("success", "Successfully added tweet");

                }
            }
        });

ParseQuery<ParseObject> query = ParseQuery.getQuery("Tweet");
query.getInBackground("7qMOJDnxq7", new GetCallback<ParseObject>() {
    @Override
    public void done(ParseObject object, ParseException e) {
        if(e == null && object != null){
            Log.i("username",object.getString("username"));
            object.put("username","Dupa");
            object.saveInBackground();
            Log.i("username",object.getString("username"));
        }
    }
});

        ParseAnalytics.trackAppOpenedInBackground(getIntent());
    }

}