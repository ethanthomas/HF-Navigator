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

package com.hhs.hfnavigator.slidingtabs.hhs;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.hhs.hfnavigator.R;
import com.hhs.hfnavigator.utils.CheckNetwork;
import com.pnikosis.materialishprogress.ProgressWheel;

public class LibFragment extends Fragment {


    SwipeRefreshLayout swipeRefreshLayout;
    ProgressWheel progressWheel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_webview, null);

        progressWheel = (ProgressWheel) root.findViewById(R.id.webViewProgress);

        swipeRefreshLayout = (SwipeRefreshLayout) root.findViewById(R.id.swipe);
        swipeRefreshLayout.setEnabled(false);
        progressWheel.spin();
        final WebView webView = (WebView) root.findViewById(R.id.webView);
        if (webView != null) {
            webView.setWebViewClient(new WebViewClient() {

                public void onPageFinished(WebView view, String url) {
                    progressWheel.stopSpinning();
                }
            });
            webView.loadUrl("https://hscsd.follettdestiny.com/common/welcome.jsp?context=saas30_3133764");
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
            webView.getSettings().setBuiltInZoomControls(true);
            webView.getSettings().setDisplayZoomControls(false);

        }
        return root;
    }

}
