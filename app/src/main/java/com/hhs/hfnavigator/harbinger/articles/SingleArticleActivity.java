package com.hhs.hfnavigator.harbinger.articles;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.hhs.hfnavigator.R;

public class SingleArticleActivity extends ActionBarActivity {

    // JSON node keys
    private static final String TAG_TITLE = "title";
    private static final String TAG_BODY = "body";
    private static final String TAG_AUTHOR = "author";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_article);


        Toolbar toolbar = (Toolbar) findViewById(R.id.harbingerToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Harbinger News");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Window window = getWindow();
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            window.setStatusBarColor(getResources().getColor(android.R.color.transparent));

        Intent in = getIntent();
        // Get JSON values from previous intent
        String title = in.getStringExtra(TAG_TITLE);
        String author = in.getStringExtra(TAG_AUTHOR);
        String body = in.getStringExtra(TAG_BODY);

        // Displaying all values on the screen
        TextView title1 = (TextView) findViewById(R.id.title);
        TextView author1 = (TextView) findViewById(R.id.author);
        TextView body1 = (TextView) findViewById(R.id.body);

        title1.setText(title);
        author1.setText("by " + author);
        body1.setText(body);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);

    }
}
