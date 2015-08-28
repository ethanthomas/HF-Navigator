package com.hhs.hfnavigator.slidingtabs.hhs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.hhs.hfnavigator.R;
import com.pnikosis.materialishprogress.ProgressWheel;

public class CalendarFragment extends Fragment {

    ProgressWheel progressWheel;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_webview, null);

        progressWheel = (ProgressWheel) root.findViewById(R.id.webViewProgress);

        swipeRefreshLayout = (SwipeRefreshLayout) root.findViewById(R.id.swipe);
        swipeRefreshLayout.setEnabled(false);
        final WebView webView = (WebView) root.findViewById(R.id.webView);
        if (webView != null) {
            webView.setWebViewClient(new WebViewClient() {

                public void onPageFinished(WebView view, String url) {
                    progressWheel.stopSpinning();
                }
            });            webView.loadUrl("http://www.harbingernews.net/calendar");
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
            webView.getSettings().setBuiltInZoomControls(true);
            webView.getSettings().setDisplayZoomControls(false);

        }

        progressWheel.spin();

        return root;

    }
}
