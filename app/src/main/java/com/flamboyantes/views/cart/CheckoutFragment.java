package com.flamboyantes.views.cart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.flamboyantes.R;
import com.flamboyantes.util.BaseFragment;
import com.flamboyantes.util.Singleton;

public class CheckoutFragment extends BaseFragment {

    private View view;
    private WebView webView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_checkout_webview, container, false);

        Singleton.getInstance().setContext(getContext());

        initViews();
        initListeners();

        return view;
    }

    public void initViews(){
        webView = view.findViewById(R.id.webView);
        webView.setWebViewClient(new MyWebViewClient());


    }

    public void initListeners(){
        String url = Singleton.getInstance().getName();
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
    }

    private class MyWebViewClient extends WebViewClient
    {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url)
        {
            view.loadUrl(url);
            return true;
        }
    }
}
