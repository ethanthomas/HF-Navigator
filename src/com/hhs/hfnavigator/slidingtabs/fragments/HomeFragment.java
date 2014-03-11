package com.hhs.hfnavigator.slidingtabs.fragments;

import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.hhs.hfnavigator.R;

public class HomeFragment extends SherlockFragment {

	TextView textview;
	NodeList nodelist;
	String URL = "http://www.harbingernews.net/days.xml";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_1, container, false);

		textview = (TextView) v.findViewById(R.id.text);

		if (CheckNetwork.isInternetAvailable(getActivity())) {
			new DownloadXML().execute(URL);

		} else {
			textview.setText("Check your internet connection");
			textview.setTextSize(20);

		}

		return v;
	}

	private class DownloadXML extends AsyncTask<String, Void, Void> {

		@Override
		protected Void doInBackground(String... Url) {
			try {
				URL url = new URL(Url[0]);
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

			for (int temp = 0; temp < nodelist.getLength(); temp++) {
				Node nNode = nodelist.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;

					textview.setText(textview.getText() + "  "
							+ getNode("aorb", eElement) + "\n" + "\n");

				}
			}
		}
	}

	// getNode function
	private static String getNode(String sTag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(sTag).item(0)
				.getChildNodes();
		Node nValue = (Node) nlList.item(0);
		return nValue.getNodeValue();
	}

	
	
}
