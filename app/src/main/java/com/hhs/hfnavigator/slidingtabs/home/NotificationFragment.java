/*License

THE WORK (AS DEFINED BELOW) IS PROVIDED UNDER THE TERMS
OF THIS CREATIVE COMMONS PUBLIC LICENSE ("CCPL" OR "LICENSE").
THE WORK IS PROTECTED BY COPYRIGHT AND/OR OTHER APPLICABLE LAW.
ANY USE OF THE WORK OTHER THAN AS AUTHORIZED UNDER THIS LICENSE
OR COPYRIGHT LAW IS PROHIBITED.

Creative Commons License

This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivs 3.0 Unported License;
you may not use this work except in compliance with the License.

You may obtain a copy of the License in the LICENSE file,
or at http://creativecommons.org/licenses/by-nc-nd/3.0/deed.en_US

BY EXERCISING ANY RIGHTS TO THE WORK PROVIDED HERE,
YOU ACCEPT AND AGREE TO BE BOUND BY THE TERMS OF THIS LICENSE.
TO THE EXTENT THIS LICENSE MAY BE CONSIDERED TO BE A CONTRACT,
THE LICENSOR GRANTS YOU THE RIGHTS CONTAINED HERE IN CONSIDERATION
OF YOUR ACCEPTANCE OF SUCH TERMS AND CONDITIONS.
*/

package com.hhs.hfnavigator.slidingtabs.home;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.hhs.hfnavigator.R;
import com.hhs.hfnavigator.utils.CheckNetwork;
import com.hhs.hfnavigator.utils.ServiceHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class NotificationFragment extends Fragment {

    String URL = "http://www.harbingernews.net/notification_center.json";
    String URL2 = "http://www.harbingernews.net/days.xml";

    String TAG_CONTENT = "content";
    String TAG_TIME = "time";

    NodeList nodelist;


    SwipeRefreshLayout swipeRefreshLayout;


    ArrayList<HashMap<String, String>> List;

    TextView textview, internet;
    ListAdapter adapter;

    View header;
    ListView lv;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle b) {

        View v = inflater.inflate(R.layout.listview_main, container, false);


        internet = (TextView) v.findViewById(R.id.noInternet);


        swipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipe);


        header = getActivity().getLayoutInflater().inflate(R.layout.a_b_header, null);

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

        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_red_light,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light);

        lv = (ListView) v.findViewById(R.id.listview);


//		/*
//		 * lv.setOnItemClickListener(new OnItemClickListener() {
//		 *
//		 * @Override public void onItemClick(AdapterView<?> parent, View view,
//		 * int position, long id) { String title = ((TextView)
//		 * view.findViewById(R.id.content)) .getText().toString(); String body =
//		 * ((TextView) view.findViewById(R.id.)) .getText().toString(); String
//		 * author = ((TextView) view.findViewById(R.id.author))
//		 * .getText().toString();
//		 *
//		 * Intent in = new Intent(getApplicationContext(),
//		 * SingleArticleActivity.class); in.putExtra("title", title);
//		 * in.putExtra("body", body); in.putExtra("author", author);
//		 *
//		 * startActivity(in);
//		 *
//		 * } });
//		 */
        if (CheckNetwork.isInternetAvailable(getActivity())) {

            internet.setVisibility(View.GONE);

            lv.removeHeaderView(header);

            lv.addHeaderView(header);

            new Get().execute();


        } else {

            lv.setAdapter(null);
            lv.removeHeaderView(header);
            swipeRefreshLayout.setRefreshing(false);

            internet.setVisibility(View.VISIBLE);


        }

        return v;
    }

    private class Get extends AsyncTask<Void, Void, Void> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            swipeRefreshLayout.setRefreshing(true);
            List = new ArrayList<HashMap<String, String>>();

        }

        protected Void doInBackground(Void... arg0) {
            ServiceHandler sh = new ServiceHandler();

            String jsonStr = sh.makeServiceCall(URL, ServiceHandler.GET);


            if (jsonStr != null)
                Log.e("nf", jsonStr);

            if (jsonStr != null) {

                try {

                    JSONArray array = new JSONArray(jsonStr);

                    for (int i = 0; i < array.length(); i++) {

                        JSONObject object = array.getJSONObject(i);

                        JSONObject jsonObject = object.getJSONObject("notification");


                        String content = jsonObject.getString(TAG_CONTENT);
                        String time = jsonObject.getString(TAG_TIME);

                        HashMap<String, String> articleMap = new HashMap<String, String>();

                        articleMap.put(TAG_CONTENT, content);
                        articleMap.put(TAG_TIME, time);

                        List.add(articleMap);

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


//            if (nodelist == null || nodelist.getLength() == 0)
            for (int temp = 0; temp < nodelist.getLength(); temp++) {
                Node nNode = nodelist.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    org.w3c.dom.Element eElement = (org.w3c.dom.Element) nNode;


                    lv = (ListView) getView().findViewById(R.id.listview);

                    adapter = new SimpleAdapter(getActivity(), List,
                            R.layout.listview_item, new String[]{TAG_CONTENT,
                            TAG_TIME}, new int[]{R.id.content, R.id.time}
                    );

                    lv.setAdapter(adapter);

                    swipeRefreshLayout.setRefreshing(false);


                    textview = (TextView) getView().findViewById(R.id.abTv);
                    textview.setText(getNode("aorb", eElement).toString());

                    Log.d("", getNode("aorb", eElement));


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

}
