package com.hhs.hfnavigator.teacherdirectory;

import android.animation.Animator;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hhs.hfnavigator.R;

public class TeacherActivity extends ActionBarActivity {

    public static final String EXTRA_PARAM_ID = "detail:_id";
    int color;

    private TextView name, position, email;
    private LinearLayout layout;

    CardView cardView;
    Toolbar toolbar;
    TeacherItem mTeacherItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        toolbar = (Toolbar) findViewById(R.id.teacher_toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        name = (TextView) findViewById(R.id.name_full);
        position = (TextView) findViewById(R.id.position_full);
        email = (TextView) findViewById(R.id.email_full);
        cardView = (CardView) findViewById(R.id.email_teacher);
        layout = (LinearLayout) findViewById(R.id.colorLayout);

        mTeacherItem = TeacherItem.getItem(getIntent().getIntExtra(EXTRA_PARAM_ID, 0));
        color = getIntent().getIntExtra("ColorInt", 0);

        loadItem();

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri data = Uri.parse("mailto:" + mTeacherItem.getEmail() + "?subject=" + "&body=");
                intent.setData(data);
                startActivity(intent);
            }
        });

    }

    private void loadItem() {
        name.setText(mTeacherItem.getName());
        position.setText(mTeacherItem.getPosition());
        email.setText(mTeacherItem.getEmail());
        toolbar.setBackgroundColor(color);
        layout.setBackgroundColor(color);
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


    public void reveal(final View v, int color) {

        Animator reveal;

        int cx = (v.getLeft() + v.getRight()) / 2;
        int cy = (v.getTop() + v.getBottom()) / 2;
        float radius = Math.max(v.getWidth(), v.getHeight()) * 2.0f;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            v.setBackgroundColor(color);
            v.setVisibility(View.VISIBLE);
            reveal = ViewAnimationUtils.createCircularReveal(
                    v, cx, cy, 0, radius);
            reveal.setInterpolator(new AccelerateInterpolator(2.0f));

            reveal.setDuration(700);
            reveal.start();
        }
    }

}
