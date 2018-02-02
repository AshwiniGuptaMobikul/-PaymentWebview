package com.example.mylibrary;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by ashwini.gupta on 11/11/17.
 */

public class MobikulPaymentWebView extends WebView implements MobikulPaymentCallback {
    private String longURL, redirectURL = "";
    private MobikulPaymentCallback callback = this;
    private MobikulPaymentBrowser paymentBrowser;
    private Context mContext;
    public MobikulPaymentWebView(Context context) {
        super(context);
        mContext = context;
    }

    public MobikulPaymentWebView(Context context, AttributeSet attr){
        super(context,attr);
        mContext = context;
    }
    public void intialized(String loadURL, MobikulPaymentCallback callback, String... redirectURL){
        paymentBrowser = new MobikulPaymentBrowser(mContext, callback, redirectURL);
        WebSettings settings = this.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        this.getSettings().setLoadWithOverviewMode(false);
        this.loadUrl(loadURL);
    }
    @Override
    public void loadUrl(String url) {
        if(paymentBrowser == null){
            paymentBrowser = new MobikulPaymentBrowser(mContext, callback, redirectURL);
        }
        super.setWebViewClient(paymentBrowser);
        super.loadUrl(url);
    }

    public String getLongURL() {
        return longURL;
    }

    public void setLongURL(String longURL) {
        this.longURL = longURL;
    }

    public String getRedirectURL() {
        return redirectURL;
    }

    public void setRedirectURL(String redirectURL) {
        this.redirectURL = redirectURL;
    }

    @Override
    public void result(String result) {
        Log.d("TAG", "result: " + result);
    }

    public MobikulPaymentCallback getCallback() {
        return callback;
    }

    public void setCallback(MobikulPaymentCallback callback) {
        this.callback = callback;
    }
    @Override
    public void setWebViewClient(WebViewClient client){
        paymentBrowser = (MobikulPaymentBrowser) client;
    }
}
