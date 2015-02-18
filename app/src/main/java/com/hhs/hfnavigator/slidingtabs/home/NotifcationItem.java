package com.hhs.hfnavigator.slidingtabs.home;

import java.util.ArrayList;

/**
 * Represents an Item in our application. Each item has a name, id, full size image url and
 * thumbnail url.
 */
public class NotifcationItem {

    public static NotifcationItem getItem(int id) {
        ArrayList<NotifcationItem> notifications = NotificationFragment.notificationList;
        for (int i = 0; i < NotificationFragment.notificationList.size() - 1; i++) {
            if (notifications.get(i).getId() == id) {
                return notifications.get(i);
            }
        }
        return null;
    }

    private final String mContent;
    private final String mTime;

    NotifcationItem(String content, String time) {
        mContent = content;
        mTime = time;
    }

    public int getId() {
        return mContent.hashCode();
    }

    public String getName() {
        return mContent;
    }

    public String getTime() {
        return mTime;
    }
}
