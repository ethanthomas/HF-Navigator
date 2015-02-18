package com.hhs.hfnavigator.teacherdirectory;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.hhs.hfnavigator.R;
import com.hhs.hfnavigator.slidingtabs.home.NotificationActivity;
import com.hhs.hfnavigator.utils.ServiceHandler;
import com.pnikosis.materialishprogress.ProgressWheel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TeacherDirectoryFragment extends Fragment implements AdapterView.OnItemClickListener {

    public static ArrayList<TeacherItem> directoryList;
    public static ArrayList<TeacherItem> originalDirectoryList;

    SwipeRefreshLayout swipeRefreshLayout;
    Adapter adapter;
    ListView lv;
    Button search;
    EditText editText;

    ProgressWheel progressWheel;

    String URL = "http://www.harbingernews.net/teacher_directory.json";


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
        return v;
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

        TeacherItem teacherItem = (TeacherItem) adapterView.getItemAtPosition(position);

        Intent intent = new Intent(getActivity(), TeacherActivity.class);
        intent.putExtra(NotificationActivity.EXTRA_PARAM_ID, teacherItem.getId());
        intent.putExtra("ColorInt", teacherItem.getColor());
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.slide_in, R.anim.nothing);

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
            lv = (ListView) getView().findViewById(R.id.listview);
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
}
