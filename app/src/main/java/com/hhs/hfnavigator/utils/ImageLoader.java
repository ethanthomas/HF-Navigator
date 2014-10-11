package com.hhs.hfnavigator.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by user on 9/6/14.
 */
public class ImageLoader {

    public static void load(URL url, String urlString, Bitmap bitmap) {

        try {
            url = new URL(urlString);
            bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());

        } catch (MalformedURLException m) {

        } catch (IOException i) {

        }

    }

}
