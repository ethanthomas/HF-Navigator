package com.hhs.hfnavigator.slidingtabs.home;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.hhs.hfnavigator.R;
import com.hhs.hfnavigator.utils.CheckNetwork;
import com.hhs.hfnavigator.utils.ServiceHandler;
import com.pnikosis.materialishprogress.ProgressWheel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import me.drakeet.materialdialog.MaterialDialog;

public class NotificationFragment extends Fragment implements AdapterView.OnItemClickListener {

    String URL = "http://www.harbingernews.net/notification_center.json";
    String URL2 = "http://www.harbingernews.net/days.xml";

    String TAG_CONTENT = "content";
    String TAG_TIME = "time";

    NodeList nodelist;

    SwipeRefreshLayout swipeRefreshLayout;
    ProgressWheel progressWheel;

    public static ArrayList<NotifcationItem> notificationList;

    TextView textview, internet, time;
    ListAdapter adapter;

    View header;
    ListView lv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle b) {

        View v = inflater.inflate(R.layout.listview, container, false);

        internet = (TextView) v.findViewById(R.id.noInternet);
        swipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipe);

        header = getActivity().getLayoutInflater().inflate(R.layout.header_day, null);

        progressWheel = (ProgressWheel) v.findViewById(R.id.notificationProgress);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                if (CheckNetwork.isInternetAvailable(getActivity())) {
                    internet.setVisibility(View.GONE);

                    new Get().execute();

                    lv.removeHeaderView(header);
                    lv.addHeaderView(header);

                } else {
                    lv.setAdapter(null);
                    lv.removeHeaderView(header);
                    swipeRefreshLayout.setRefreshing(false);
                    internet.setVisibility(View.VISIBLE);
                }

            }
        });

        swipeRefreshLayout.setColorSchemeResources(
                R.color.grey_blue_950,
                R.color.material_blue_grey_950,
                R.color.green_800);

        lv = (ListView) v.findViewById(R.id.listview);

        lv.setOnItemClickListener(this);

        lv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                int topRowVerticalPosition =
                        (lv == null || lv.getChildCount() == 0) ?
                                0 : lv.getChildAt(0).getTop();
                swipeRefreshLayout.setEnabled(firstVisibleItem == 0 && topRowVerticalPosition >= 0);
            }
        });

        if (CheckNetwork.isInternetAvailable(getActivity())) {

            internet.setVisibility(View.GONE);

            lv.removeHeaderView(header);

            lv.addHeaderView(header);

            new Get().execute();
            progressWheel.spin();

        } else {

            lv.setAdapter(null);
            lv.removeHeaderView(header);
            swipeRefreshLayout.setRefreshing(false);

            internet.setVisibility(View.VISIBLE);
        }
        return v;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

        if (position != 0) {
            NotifcationItem notifcationItem = (NotifcationItem) adapterView.getItemAtPosition(position);

//            Intent intent = new Intent(getActivity(), NotificationActivity.class);
//            intent.putExtra("notification", notifcationItem.getName());
//            intent.putExtra("time", notifcationItem.getTime());

            final MaterialDialog dialog = new MaterialDialog(getActivity());
            dialog.setCanceledOnTouchOutside(true);
            dialog.setTitle(notifcationItem.getTime());
            dialog.setMessage(notifcationItem.getName());
            dialog.setPositiveButton("OK", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.show();

//            ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
//                    getActivity(),
//
//                    new Pair<View, String>(view.findViewById(R.id.content),
//                            NotificationActivity.VIEW_CONTENT)
//            );
//
//            ActivityCompat.startActivity(getActivity(), intent, activityOptions.toBundle());

        }
    }


    private class Get extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            swipeRefreshLayout.setRefreshing(true);
            notificationList = new ArrayList<NotifcationItem>();
        }

        protected Void doInBackground(Void... arg0) {
            ServiceHandler sh = new ServiceHandler();

            String jsonStr = sh.makeServiceCall(URL, ServiceHandler.GET);

            if (jsonStr != null)
                Log.d("JSON String = ", jsonStr);

            if (jsonStr != null) {

                try {

                    JSONArray array = new JSONArray(jsonStr);

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object = array.getJSONObject(i);
                        JSONObject jsonObject = object.getJSONObject("notification");
                        String content = jsonObject.getString(TAG_CONTENT);
                        String time = jsonObject.getString(TAG_TIME);
                        notificationList.add(new NotifcationItem(content, time));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get data from the url");
            }
            return null;
        }

        @Override
        public void onPostExecute(Void result) {
            super.onPostExecute(result);
            new DownloadXML().execute(URL2);
        }
    }

    private class DownloadXML extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... Url) {
            try {
                java.net.URL url = new URL(Url[0]);
                DocumentBuilderFactory dbf = DocumentBuilderFactory
                        .newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                // Download the XML file
                Document doc = db.parse(new InputSource(url.openStream()));
                doc.getDocumentElement().normalize();
                // Locate the Tag Name
                nodelist = doc.getElementsByTagName("day");

            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void args) {

            progressWheel.stopSpinning();
            if (nodelist != null || nodelist.getLength() != 0)
                for (int temp = 0; temp < nodelist.getLength(); temp++) {
                    Node nNode = nodelist.item(temp);
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        org.w3c.dom.Element eElement = (org.w3c.dom.Element) nNode;

                        lv = (ListView) getActivity().findViewById(R.id.listview);

                        adapter = new Adapter();

                        lv.setAdapter(adapter);

                        swipeRefreshLayout.setRefreshing(false);

                        textview = (TextView) getView().findViewById(R.id.abTv);
                        textview.setText(getNode("aorb", eElement).toString());

                        Log.d("", getNode("aorb", eElement));

//                        Home.reveal();
                    }
                }
        }
    }

    private static String getNode(String sTag, org.w3c.dom.Element eElement) {
        NodeList nlList = eElement.getElementsByTagName(sTag).item(0)
                .getChildNodes();
        Node nValue = (Node) nlList.item(0);
        return nValue.getNodeValue();
    }


    private class Adapter extends BaseAdapter {

        @Override
        public int getCount() {
            return notificationList.size();
        }

        @Override
        public NotifcationItem getItem(int position) {
            return notificationList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return getItem(position).getId();
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = getActivity().getLayoutInflater().inflate(R.layout.list_item_notification, viewGroup, false);
            }

            final NotifcationItem notifcationItem = getItem(position);

            // Set the TextView's contents
            TextView content = (TextView) view.findViewById(R.id.content);
            TextView time = (TextView) view.findViewById(R.id.time);

            content.setText(notifcationItem.getName());
            time.setText(notifcationItem.getTime());

            return view;
        }
    }

//    public void revealToggle(final View v) {
//
//        Animator reveal;
//
//        int cx = (v.getLeft() + v.getRight()) / 2;
//        int cy = (v.getTop() + v.getBottom()) / 2;
//        float radius = Math.max(v.getWidth(), v.getHeight()) * 2.0f;
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
//            if (v.getVisibility() == View.INVISIBLE || v.getVisibility() == View.GONE) {
//                v.setVisibility(View.VISIBLE);
//                reveal = ViewAnimationUtils.createCircularReveal(
//                        v, cx, cy, 0, radius);
//                reveal.setInterpolator(new AccelerateInterpolator());
//
//                reveal.setDuration(700);
//                reveal.start();
//            } else {
//                reveal = ViewAnimationUtils.createCircularReveal(
//                        v, cx, cy, radius, 0);
//                reveal.addListener(new AnimatorListenerAdapter() {
//                    @Override
//                    public void onAnimationEnd(Animator animation) {
//                        v.setVisibility(View.INVISIBLE);
//                    }
//                });
//
//                reveal.setInterpolator(new DecelerateInterpolator());
//
//                reveal.setDuration(700);
//                reveal.start();
//
//            }
//    }
}
