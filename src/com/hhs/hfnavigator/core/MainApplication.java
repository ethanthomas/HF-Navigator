/*License

THE WORK (AS DEFINED BELOW) IS PROVIDED UNDER THE TERMS 
OF THIS CREATIVE COMMONS PUBLIC LICENSE ("CCPL" OR "LICENSE"). 
THE WORK IS PROTECTED BY COPYRIGHT AND/OR OTHER APPLICABLE LAW. 
ANY USE OF THE WORK OTHER THAN AS AUTHORIZED UNDER THIS LICENSE 
OR COPYRIGHT LAW IS PROHIBITED.

Creative Commons License

This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivs 3.0 Unported License; 
you may not use this work except in compliance with the License.

You may obtain a copy of the License in the LICENSE file, 
or at http://creativecommons.org/licenses/by-nc-nd/3.0/deed.en_US

BY EXERCISING ANY RIGHTS TO THE WORK PROVIDED HERE, 
YOU ACCEPT AND AGREE TO BE BOUND BY THE TERMS OF THIS LICENSE. 
TO THE EXTENT THIS LICENSE MAY BE CONSIDERED TO BE A CONTRACT, 
THE LICENSOR GRANTS YOU THE RIGHTS CONTAINED HERE IN CONSIDERATION 
OF YOUR ACCEPTANCE OF SUCH TERMS AND CONDITIONS.
*/

package com.hhs.hfnavigator.core;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseAnalytics;
import com.parse.ParseInstallation;
import com.parse.ParseUser;
import com.parse.PushService;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

public class MainApplication extends Application {
    private static MainApplication instance = new MainApplication();

    public MainApplication() {
        instance = this;
    }

    public static Context getContext() {
        return instance;
    }

    
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, "C9vun7KPxdDKBsVcuzwxlyRDEC83hekR9O6yeX3e",
        		"OoIEedGp2uIu0yWImnlqDTNunEPLaf0O6jTe4IgR");
		ParseAnalytics.trackAppOpened(getIntent());
		ParseUser.enableAutomaticUser();
		ParseACL defaultACL = new ParseACL();
		ParseACL.setDefaultACL(defaultACL, true);
	
		PushService.setDefaultPushCallback(this, Main.class);
		ParseInstallation.getCurrentInstallation().saveInBackground();
		PushService.subscribe(this, "Broadcast", Main.class);
    }

	private Intent getIntent() {
		// TODO Auto-generated method stub
		return null;
	}

  	
	
}