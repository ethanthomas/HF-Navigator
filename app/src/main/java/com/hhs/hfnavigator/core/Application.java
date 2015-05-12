package com.hhs.hfnavigator.core;

import android.content.Intent;

import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseUser;
import com.parse.PushService;

public class Application extends android.app.Application {

    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, "C9vun7KPxdDKBsVcuzwxlyRDEC83hekR9O6yeX3e",
                "OoIEedGp2uIu0yWImnlqDTNunEPLaf0O6jTe4IgR");
        ParseAnalytics.trackAppOpened(getIntent());
        ParseUser.enableAutomaticUser();
        PushService.setDefaultPushCallback(this, Home.class);
        PushService.subscribe(this, "Broadcast", Home.class);
    }

    private Intent getIntent() {
        return null;
    }
}