package com.hhs.hfnavigator.core;

import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParsePush;
import com.parse.ParseUser;

public class Application extends android.app.Application {

    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, "",
                "");
        ParseInstallation.getCurrentInstallation().saveInBackground();
        ParseUser.enableAutomaticUser();
        ParsePush.subscribeInBackground("Broadcast");
    }
}