package com.example.mylibrary;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by ashwini.gupta on 11/11/17.
 */

public class MobikulPaymentBrowser extends WebViewClient {
    private final Context mContext;
    private final String mRedirectURL[];
    private final String TAG = "MobikulPaymentBrowser";
    private final MobikulPaymentCallback callback;
    private final AlertDialog.Builder builder;
    private ProgressDialog progress;

    public MobikulPaymentBrowser(Context context, MobikulPaymentCallback mobikulPayment, String... redirectURL){
        mContext = context;
        mRedirectURL = redirectURL;
        this.callback = mobikulPayment;
        builder = new AlertDialog.Builder(mContext);
        progress = ProgressDialog.show(mContext, null, context.getString(R.string.mobikul_loading), true);
        progress.setCanceledOnTouchOutside(false);
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (!contains(url, mRedirectURL)) {
            Log.d(TAG, "shouldOverrideUrlLoading: " + url);
            view.loadUrl(url);
            return true;
        }
        callback.result(url);
        return true;
    }

    private boolean contains(String container, String[] mRedirectURL) {
        for (String url : mRedirectURL){
            if(container.contains(url))
                return true;
        }
        return false;
    }

    @Override
    public void onReceivedSslError(WebView view, final SslErrorHandler handler, SslError error) {
        handler.proceed();
        builder.setMessage(R.string.ask_want_to_proceed);
        builder.setPositiveButton("continue", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                handler.proceed();
            }
        });
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                handler.cancel();
            }
        });
        final AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap facIcon) {
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        if (progress != null)
            progress.dismiss();
    }
}
