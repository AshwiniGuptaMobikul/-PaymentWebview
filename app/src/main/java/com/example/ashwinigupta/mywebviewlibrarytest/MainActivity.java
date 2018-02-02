package com.example.ashwinigupta.mywebviewlibrarytest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.mylibrary.MobikulPaymentCallback;
import com.example.mylibrary.MobikulPaymentLayout;

public class MainActivity extends AppCompatActivity implements MobikulPaymentCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MobikulPaymentLayout paymentWebView = (MobikulPaymentLayout) findViewById(R.id.paymentWebView);
        paymentWebView.initilize("https://developer.android.com/training/custom-views/index.html", "custom-views/custom-drawing",  "500$", this);
    }

    @Override
    public void result(String result) {
        Log.d("TAG", "result: " + result);
    }


}
