package com.hhs.hfnavigator.slidingtabs.hhs;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hhs.hfnavigator.R;
import com.hhs.hfnavigator.customViews.TouchImageView;
import com.hhs.hfnavigator.utils.CheckNetwork;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class CalendarFragment extends Fragment {


    SwipeRefreshLayout swipeRefreshLayout;
    TouchImageView imageView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.calendar_fragment, null);

        imageView = (TouchImageView) root.findViewById(R.id.cal);

        swipeRefreshLayout = (SwipeRefreshLayout) root.findViewById(R.id.swipe);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (CheckNetwork.isInternetAvailable(getActivity())) {

                    new Get().execute();

                } else {

                    swipeRefreshLayout.setRefreshing(false);
                }


            }
        });

        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_red_light,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light);

        new Get().execute();

        return root;

    }


    private class Get extends AsyncTask<Void, Void, Void> {

        Bitmap bmp;
        URL url;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            swipeRefreshLayout.setRefreshing(true);

        }

        protected Void doInBackground(Void... arg0) {

            try {
                url = new URL("http://www.harbingernews.net/calendar");
                bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());

            } catch (MalformedURLException m) {

            } catch (IOException i) {

            }

            return null;
        }

        @Override
        public void onPostExecute(Void result) {
            super.onPostExecute(result);

            swipeRefreshLayout.setRefreshing(false);

            imageView.setImageBitmap(bmp);

            swipeRefreshLayout.setEnabled(false);
        }

    }


}