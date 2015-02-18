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
import com.pnikosis.materialishprogress.ProgressWheel;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class CalendarFragment extends Fragment {


    TouchImageView imageView;
    ProgressWheel progressWheel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_calendar, null);

        imageView = (TouchImageView) root.findViewById(R.id.cal);

        progressWheel = (ProgressWheel) root.findViewById(R.id.calendarProgress);

        new Get().execute();
        progressWheel.spin();
        return root;

    }


    private class Get extends AsyncTask<Void, Void, Void> {

        Bitmap bmp;
        URL url;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
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

            imageView.setImageBitmap(bmp);

            progressWheel.stopSpinning();
        }

    }


}
