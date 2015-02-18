package com.hhs.hfnavigator.slidingtabs.home;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Transition;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.hhs.hfnavigator.R;

public class NotificationActivity extends ActionBarActivity {

    // Extra name for the ID parameter
    public static final String EXTRA_PARAM_ID = "detail:_id";

    public static final String VIEW_CONTENT = "detail:header:content";

//    public static final String VIEW_TIME = "detail:header:time";

    private TextView content;
//    private TextView time;

    String notification, time;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        toolbar = (Toolbar) findViewById(R.id.not_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        mHeaderImageView = (ImageView) findViewById(R.id.imageview_header);
        content = (TextView) findViewById(R.id.content_full);

        notification = getIntent().getStringExtra("notification");
        time = getIntent().getStringExtra("time");

        getSupportActionBar().setTitle(time);

        content.setText(notification);
//        ViewCompat.setTransitionName(mHeaderImageView, VIEW_NAME_HEADER_IMAGE);
        ViewCompat.setTransitionName(content, VIEW_CONTENT);
//        ViewCompat.setTransitionName(time, VIEW_TIME);
        // END_INCLUDE(detail_set_view_name)

    }

    private boolean addTransitionListener() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && addTransitionListener()) {

            final Transition transition = getWindow().getSharedElementEnterTransition();

            if (transition != null) {
                // There is an entering shared element transition so add a listener to it
                transition.addListener(new Transition.TransitionListener() {
                    @Override
                    public void onTransitionEnd(Transition transition) {
                        // As the transition has ended, we can now load the full-size image
//                    loadFullSizeImage();

                        // Make sure we remove ourselves as a listener
                        transition.removeListener(this);
                    }

                    @Override
                    public void onTransitionStart(Transition transition) {
                        // No-op
                    }

                    @Override
                    public void onTransitionCancel(Transition transition) {
                        // Make sure we remove ourselves as a listener
                        transition.removeListener(this);
                    }

                    @Override
                    public void onTransitionPause(Transition transition) {
                        // No-op
                    }

                    @Override
                    public void onTransitionResume(Transition transition) {
                        // No-op
                    }
                });
                return true;
            }
        } else {
            // If we reach here then we have not added a listener
            return false;
        }

        return false;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
