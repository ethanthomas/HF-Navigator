package com.hhs.hfnavigator.teacherdirectory;

import android.util.Log;

import com.hhs.hfnavigator.constants.Colors;

import java.util.ArrayList;

/**
 * Represents an Item in our application. Each item has a name, id, full size image url and
 * thumbnail url.
 */
public class TeacherItem {

    public static ArrayList<TeacherItem> directoryList = TeacherDirectoryFragment.directoryList;

    public static TeacherItem getItem(int id) {
        for (int i = 0; i < TeacherDirectoryFragment.directoryList.size(); i++) {
            if (directoryList.get(i).getId() == id) {
                return directoryList.get(i);
            }
        }
        return null;
    }

    private final String mName, mPosition, mEmail;
    private int mColor = Colors.randomMaterialColor();

    public TeacherItem(String name, String position, String email) {
        mName = name;
        mPosition = position;
        mEmail = email;
    }

    public int getId() {
        return mName.hashCode();
    }

    public String getName() {
        return mName;
    }

    public String getPosition() {
        return mPosition;
    }

    public String getEmail() {
        return mEmail;
    }

//    public void setRandomColor(int position) {
//        int color = Colors.randomMaterialColor();
//        if (position == 0)
//            mColor = color;
//        else if (color != directoryList.get(position - 1).getColor()
//               && color != directoryList.get(position + 1).getColor())
//            mColor = Colors.randomMaterialColor();
//    }

    public int getColor() {
        return mColor;
    }

    public String getLetter() {

        String[] array = mName.split(" ");

        if (array != null) {
            if (array.length == 2 && array[1] != null)
                return array[1].substring(0, 1);
            else if (array.length == 3 && array[2] != null)
                return array[2].substring(0, 1);
            else if (array.length == 4 && array[3] != null)
                return array[3].substring(0, 1);
            else
                Log.d("Letter", " error");
        }

        return "A";
    }
}
