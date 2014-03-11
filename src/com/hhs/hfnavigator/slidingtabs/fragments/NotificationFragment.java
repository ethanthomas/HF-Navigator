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

package com.hhs.hfnavigator.slidingtabs.fragments;

import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.hhs.hfnavigator.R;


public class NotificationFragment extends SherlockFragment {
	TextView textview;
	NodeList nodelist;
	ProgressDialog pDialog;
	String URL = "http://www.harbingernews.net/notification_center.xml";
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.notificationfragment, container,
				false);

       
		
		textview = (TextView) v.findViewById(R.id.text);

		if(CheckNetwork.isInternetAvailable(getActivity())) 
        {
			new DownloadXML().execute(URL);

        }   
       else
        {
    	   textview.setText("Could not retrieve notifications, please check your internet connection");
     	  textview.setTextSize(20);
     	  

        } 
       
		return v;
	}

	

	private class DownloadXML extends AsyncTask<String, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog((getActivity()));
			pDialog.setTitle("Loading Notifications");
			pDialog.setMessage("Loading...");
			pDialog.setIndeterminate(false);
			pDialog.show();
		}

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
				nodelist = doc.getElementsByTagName("notification");

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

					
					
					textview.setText(textview.getText() + "   "
							+ getNode("content", eElement) + "\n" + "\n");
					textview.setText(textview.getText() + " "
							+ getNode("time", eElement) + "\n" + "\n");
				}
			}
			pDialog.dismiss();
		}
	}

	private static String getNode(String sTag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(sTag).item(0)
				.getChildNodes();
		Node nValue = (Node) nlList.item(0);
		return nValue.getNodeValue();
	}
}