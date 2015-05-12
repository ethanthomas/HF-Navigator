package com.hhs.hfnavigator.teacherdirectory;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hhs.hfnavigator.R;
import com.hhs.hfnavigator.core.Home;
import com.hhs.hfnavigator.utils.ServiceHandler;
import com.pnikosis.materialishprogress.ProgressWheel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import at.markushi.ui.RevealColorView;

public class TeacherDirectoryFragment extends Fragment implements AdapterView.OnItemClickListener {

    public static ArrayList<TeacherItem> directoryList;
    public static ArrayList<TeacherItem> originalDirectoryList;

    SwipeRefreshLayout swipeRefreshLayout;
    Adapter adapter;
    public static ListView lv;
    Button search;
    EditText editText;

    ProgressWheel progressWheel;

    String URL = "http://www.harbingernews.net/teacher_directory.json";

    public static TextView name, position, email;
    public static LinearLayout layout, teacherParentLayout;
    public static RelativeLayout teacherMaxLayout;

    public static CardView cardView;

    public static RevealColorView reveal;

    static boolean isRevealed = false;

    public static TeacherItem item = null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle b) {

        View v = inflater.inflate(R.layout.fragment_teacher_directory, container, false);

        swipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipeRefreshLayout);
        lv = (ListView) v.findViewById(R.id.listview);
        search = (Button) v.findViewById(R.id.searchButton);
        editText = (EditText) v.findViewById(R.id.searchText);
        progressWheel = (ProgressWheel) v.findViewById(R.id.progressWheelTeacherDirectory);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Get().execute();
            }
        });

        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_red_light,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light);

        swipeRefreshLayout.setEnabled(false);
        lv.setOnItemClickListener(this);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                directoryList = originalDirectoryList;

                for (int i = 0; i < directoryList.size(); i++) {
                    if (!directoryList.get(i).getName().contains(editText.getText().toString())) {
                        directoryList.remove(i);
                    }
                }

                adapter.notifyDataSetChanged();
            }
        });

        new Get().execute();
        progressWheel.spin();


        reveal = (RevealColorView) v.findViewById(R.id.teacherReveal);

        name = (TextView) v.findViewById(R.id.name_full);
        position = (TextView) v.findViewById(R.id.position_full);
        email = (TextView) v.findViewById(R.id.email_full);
        cardView = (CardView) v.findViewById(R.id.email_teacher);
        layout = (LinearLayout) v.findViewById(R.id.colorLayout);

        teacherParentLayout = (LinearLayout) v.findViewById(R.id.teacherParentLayout);
        teacherMaxLayout = (RelativeLayout) v.findViewById(R.id.teacherMaxParentLayout);

//        cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                Uri data = Uri.parse("mailto:" + mTeacherItem.getEmail() + "?subject=" + "&body=");
//                intent.setData(data);
//                startActivity(intent);
//            }
//        });

        return v;
    }

    private void loadItem(final TeacherItem mTeacherItem) {
        Home.gradient.setVisibility(View.INVISIBLE);
        teacherMaxLayout.setVisibility(View.VISIBLE);
        name.setText(mTeacherItem.getName());
        position.setText(mTeacherItem.getPosition());
        email.setText(mTeacherItem.getEmail());
        Home.reveal.setBackgroundColor(mTeacherItem.getColor());

        isRevealed = true;
        ValueAnimator anim = ValueAnimator.ofFloat(0, 1);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float slideOffset = (Float) valueAnimator.getAnimatedValue();
                Home.mDrawerToggle.onDrawerSlide(Home.mDrawerLayout, slideOffset);
            }
        });
        anim.setInterpolator(new DecelerateInterpolator());
        anim.setDuration(500);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        anim.start();
//        layout.setBackgroundColor(color);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            getActivity().getWindow().setStatusBarColor(mTeacherItem.getColor());

        final int cx = (lv.getLeft() + lv.getRight()) / 2;
        final int cy = (lv.getTop() + lv.getBottom()) / 2;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                reveal.reveal(cx, cy, mTeacherItem.getColor(), 0, 340, null);
            }
        }, 200);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                teacherParentLayout.setVisibility(View.VISIBLE);
                teacherParentLayout.animate().alpha(1).translationY(-60).setInterpolator(new DecelerateInterpolator());
            }
        }, 600);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    getActivity().getWindow().setStatusBarColor(mTeacherItem.getColor());
                }
            }, 400);


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

    public static void hide() {
        Home.gradient.setVisibility(View.VISIBLE);
        isRevealed = false;
        Home.mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        ValueAnimator anim = ValueAnimator.ofFloat(1, 0);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float slideOffset = (Float) valueAnimator.getAnimatedValue();
                Home.mDrawerToggle.onDrawerSlide(Home.mDrawerLayout, slideOffset);
            }
        });
        anim.setInterpolator(new DecelerateInterpolator());
        anim.setDuration(500);
        anim.start();

        teacherParentLayout.animate().alpha(0).translationY(60);

        final int cx = (lv.getLeft() + lv.getRight()) / 2;
        final int cy = (lv.getTop() + lv.getBottom()) / 2;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                reveal.hide(cx, cy, android.R.color.transparent, 0, 340, new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        teacherParentLayout.setVisibility(View.INVISIBLE);
                        teacherParentLayout.setAlpha(0);
                        name.setText("");
                        position.setText("");
                        email.setText("");
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
            }
        }, 100);

        cardView.setOnClickListener(null);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

        TeacherItem teacherItem = (TeacherItem) adapterView.getItemAtPosition(position);
        item = teacherItem;
        loadItem(teacherItem);
        Home.mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

//        Intent intent = new Intent(getActivity(), TeacherActivity.class);
//        intent.putExtra(NotificationActivity.EXTRA_PARAM_ID, teacherItem.getId());
//        intent.putExtra("ColorInt", teacherItem.getColor());
//        startActivity(intent);
//        getActivity().overridePendingTransition(R.anim.nothing, R.anim.nothing);

    }

    private class Get extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            swipeRefreshLayout.setRefreshing(true);
            directoryList = new ArrayList<TeacherItem>();
        }

        protected Void doInBackground(Void... arg0) {

            ServiceHandler sh = new ServiceHandler();

            String jsonStr = sh.makeServiceCall(URL, ServiceHandler.GET);
            Log.d("JSON String = ", jsonStr);

            try {

                JSONArray array = new JSONArray(jsonStr);

                for (int i = 0; i < array.length(); i++) {

                    JSONObject jsonObject = array.getJSONObject(i);

                    String name = jsonObject.getString("name");
                    String position = jsonObject.getString("position");
                    String email = jsonObject.getString("email");

                    directoryList.add(new TeacherItem(name, position, email));
                }

            } catch (JSONException e) {
                e.printStackTrace();

            }

            return null;
        }

        @Override
        public void onPostExecute(Void result) {
            super.onPostExecute(result);

            originalDirectoryList = directoryList;
            lv = (ListView) getActivity().findViewById(R.id.listview);
            adapter = new Adapter();
            lv.setAdapter(adapter);

            progressWheel.stopSpinning();
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    private class Adapter extends BaseAdapter {

        @Override
        public int getCount() {
            return directoryList.size();
        }

        @Override
        public TeacherItem getItem(int position) {
            return directoryList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return getItem(position).getId();
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            if (view == null)
                view = getActivity().getLayoutInflater().inflate(R.layout.list_item_teacher_directory, viewGroup, false);

            TeacherItem teacherItem = getItem(position);

            TextView name = (TextView) view.findViewById(R.id.name);
            TextView letter = (TextView) view.findViewById(R.id.letter);

            name.setText(teacherItem.getName());

            letter.setText(teacherItem.getLetter());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                Drawable drawable = getResources().getDrawable(R.drawable.circle);
                drawable.setColorFilter(teacherItem.getColor(), PorterDuff.Mode.SRC_ATOP);
                letter.setBackground(drawable);
            } else if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN) {
                Drawable drawable = getResources().getDrawable(R.drawable.circle);
                drawable.setColorFilter(teacherItem.getColor(), PorterDuff.Mode.SRC_ATOP);
                letter.setBackgroundDrawable(drawable);
            }

            return view;
        }
    }

    public static boolean isRevealed() {
        return isRevealed;
    }
}