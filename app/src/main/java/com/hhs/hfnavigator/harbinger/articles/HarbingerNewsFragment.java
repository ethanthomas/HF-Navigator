package com.hhs.hfnavigator.harbinger.articles;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.hhs.hfnavigator.R;
import com.hhs.hfnavigator.utils.CheckNetwork;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HarbingerNewsFragment extends Fragment {

    private static String url = "http://www.harbingernews.net/articles.json";

    // JSON Node names
    private static final String TAG_ARTICLES = "articles";
    private static final String TAG_TITLE = "title";
    private static final String TAG_BODY = "body";
    private static final String TAG_AUTHOR = "author";
    ListView listview;
    SwipeRefreshLayout swipeRefreshLayout;

    public static ArrayList<ArticleItem> articlesList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_articles, null);

        listview = (ListView) root.findViewById(R.id.articlelist);
        swipeRefreshLayout = (SwipeRefreshLayout) root.findViewById(R.id.swipeRefreshLayout);

        // Listview on item click listener
        listview.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                String title = ((TextView) view.findViewById(R.id.title))
                        .getText().toString();
                String body = ((TextView) view.findViewById(R.id.body))
                        .getText().toString();
                String author = ((TextView) view.findViewById(R.id.author))
                        .getText().toString();

                Intent in = new Intent(getActivity(),
                        SingleArticleActivity.class);
                in.putExtra(TAG_TITLE, title);
                in.putExtra(TAG_BODY, body);
                in.putExtra(TAG_AUTHOR, author);

                startActivity(in);
            }
        });

        final Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                swipeRefreshLayout.setRefreshing(false);
            }
        }, 1500);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (CheckNetwork.isInternetAvailable(getActivity())) {
                    new GetArticles().execute();

                    final Handler handler = new Handler();

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            swipeRefreshLayout.setRefreshing(false);
                        }
                    }, 1500);

                } else {

                    swipeRefreshLayout.setRefreshing(false);
                }


            }
        });

        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_red_light,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light);

        swipeRefreshLayout.setRefreshing(true);
        new GetArticles().execute();

        return root;
    }

    /**
     * Async task class to get json by making HTTP call
     */
    private class GetArticles extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }

        @Override
        protected Void doInBackground(Void... arg0) {

            ServiceHandler sh = new ServiceHandler();
            JSONArray articles;
            articlesList = new ArrayList<ArticleItem>();

            String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);

            if (jsonStr != null) {
                try {

                    JSONObject jsonObj = new JSONObject(jsonStr);

                    articles = jsonObj.getJSONArray(TAG_ARTICLES);

                    for (int i = 0; i < articles.length(); i++) {

                        JSONObject a = articles.getJSONObject(i);

                        String title = a.getString(TAG_TITLE);
                        String body = a.getString(TAG_BODY);
                        String author = a.getString(TAG_AUTHOR);

                        body = body.replaceAll("&nbsp;", " ");
                        body = body.replaceAll("&#39;", "\'");
                        body = body.replaceAll("&quot;", "\"");
                        body = body.replaceAll("&ldquo;", "\"");
                        body = body.replaceAll("&rdquo;", "\"");
                        body = body.replaceAll("&rsquo;", "\'");
                        body = body.replaceAll("&lsquo;", "\'");
                        body = body.replaceAll("~", "");

                        if (title != null || body != null || author != null) {

                            articlesList.add(new ArticleItem(title, author, body));
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            listview.setAdapter(new Adapter());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                getActivity().finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private class Adapter extends BaseAdapter {

        @Override
        public int getCount() {
            return articlesList.size();
        }

        @Override
        public ArticleItem getItem(int position) {
            return articlesList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return getItem(position).getId();
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = getActivity().getLayoutInflater().inflate(R.layout.list_item_harbinger, viewGroup, false);
            }

            final ArticleItem articleItem = getItem(position);

            TextView title = (TextView) view.findViewById(R.id.title);
            TextView author = (TextView) view.findViewById(R.id.author);
            TextView body = (TextView) view.findViewById(R.id.body);

            title.setText(articleItem.getTitle());
            author.setText(articleItem.getAuthor());
            body.setText(articleItem.getBody());
            return view;
        }
    }

}