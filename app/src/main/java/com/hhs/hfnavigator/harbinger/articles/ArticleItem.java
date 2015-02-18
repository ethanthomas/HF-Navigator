package com.hhs.hfnavigator.harbinger.articles;

import com.hhs.hfnavigator.slidingtabs.home.NotificationFragment;

import java.util.ArrayList;

/**
 * Created by user on 2/13/15.
 */
public class ArticleItem {

    String title, author, body;


    public static ArticleItem getItem(int id) {
        ArrayList<ArticleItem> articles = HarbingerNewsFragment.articlesList;
        for (int i = 0; i < articles.size() - 1; i++) {
            if (articles.get(i).getId() == id) {
                return articles.get(i);
            }
        }
        return null;
    }

    public ArticleItem(String title, String author, String body) {
        this.title = title;
        this.author = author;
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getBody() {
        return body;
    }

    public int getId() {
        return title.hashCode();
    }

}
