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

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.actionbarsherlock.app.SherlockFragment;
import com.hhs.hfnavigator.R;
import com.hhs.hfnavigator.links.Cont;
import com.hhs.hfnavigator.links.Khan;
import com.hhs.hfnavigator.links.Ritter;

public class LinksFragment extends SherlockFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.links, container, false);

		Button cont = (Button) v.findViewById(R.id.button1);
		Button jackson = (Button) v.findViewById(R.id.button2);
		Button khan = (Button) v.findViewById(R.id.button3);
		Button ritter = (Button) v.findViewById(R.id.button4);

		cont.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				Intent i = new Intent(getActivity(), Cont.class);
				startActivity(i);
			}
		});

		ritter.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				Intent i = new Intent(getActivity(), Ritter.class);
				startActivity(i);
			}
		});

		khan.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				Intent i = new Intent(getActivity(), Khan.class);
				startActivity(i);
			}
		});

		jackson.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				goToUrl("http://www.schoolrack.com/jacksonc ");
			}

			private void goToUrl(String url) {
				Uri uriUrl = Uri.parse(url);
				Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
				startActivity(launchBrowser);
			}
		});

		return v;

	}

}