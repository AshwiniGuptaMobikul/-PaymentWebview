package com.example.mylibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by ashwini.gupta on 14/11/17.
 */
/* MobikulPaymentLayout open a webview internally. request to load an URL and  send an response when an redirect url meet*/
public class MobikulPaymentLayout extends RelativeLayout {
    private Context mContext;
    private boolean logoVisible = true;
    private boolean orderVisible = true;
    private int normalPadding = 10;
    private Drawable logoIcon;
    private String orderTotal;
    private String loadURL = "";
    private String redirectURL = "";
    private MobikulPaymentCallback callback;
    private LinearLayout logoLayout;
    public String message;
    private ImageView logoImage;
    private TextView orderTotalView;

    public MobikulPaymentLayout(Context context) {
        super(context);
        mContext = context;
        logoImage = new ImageView(mContext);
        orderTotalView =  new TextView(mContext);
    }

    public MobikulPaymentLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        logoImage = new ImageView(mContext);
        logoLayout = new LinearLayout(mContext);
        orderTotalView =  new TextView(mContext);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.MobikulPaymentLayout,
                0, 0);

        try {
            logoVisible = a.getBoolean(R.styleable.MobikulPaymentLayout_logoVisible, false);
            orderVisible = a.getBoolean(R.styleable.MobikulPaymentLayout_orderVisible, false);
            logoLayout = new LinearLayout(mContext);
//            mTextPos = a.getInteger(R.styleable.MobikulPaymentLayout_logoIcon, 0);
        } finally {
            a.recycle();
        }
    }
    /* Initialize WebView and Logo DATA*/
    public void initilize(String loadURL, String redirectURL, String orderTotal, MobikulPaymentCallback callback){
        this.loadURL = loadURL;
        this.redirectURL = redirectURL;
        this.callback = callback;
        this.orderTotal = orderTotal;
        init();
    }

    private void init() {
        setLogoView();
        setPaymentWebView();
    }
/*Initialize Payment WebView */
    private void setPaymentWebView() {
        RelativeLayout.LayoutParams newParams = new RelativeLayout.LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT);
        MobikulPaymentWebView paymentView = new MobikulPaymentWebView(mContext);
        paymentView.intialized(loadURL, callback, redirectURL);
        newParams.addRule(RelativeLayout.BELOW, 1);
        paymentView.setLayoutParams(newParams);
        this.addView(paymentView);
    }

    /*Setup Logo Layout*/
    private void setLogoView() {
        logoLayout.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        logoLayout.setOrientation(LinearLayout.HORIZONTAL);
        logoLayout.setPadding(normalPadding, normalPadding, normalPadding, normalPadding);
        setLogoIcon(logoIcon);
        logoLayout.addView(logoImage);
        setOrderView();
        logoLayout.addView(orderTotalView);
        logoLayout.setId(1111);
        this.addView(logoLayout);
        setLogoLayoutVisibility();
    }
    private void setLogoLayoutVisibility(){
        if (orderVisible) {
            orderTotalView.setVisibility(VISIBLE);
        }else {
            orderTotalView.setVisibility(GONE);
        }
        if(logoVisible){
            logoImage.setVisibility(VISIBLE);
        }else{
            logoImage.setVisibility(GONE);
        }
        if(!orderVisible && !logoVisible){
            logoLayout.setVisibility(GONE);
        }else{
            logoLayout.setVisibility(VISIBLE);
        }
    }
    /*By Default set ic_launcher icon */
    public void setLogoIcon(Drawable logoIcon) {
        this.logoIcon = logoIcon;
        if(logoIcon == null )
            logoImage.setImageResource(R.mipmap.ic_launcher);
        else
            logoImage.setImageDrawable(logoIcon);
    }

    /*Progromatically change Visibilty of Order Text. By default it is visible*/
    public void setOrderVisible(boolean orderVisible) {
        this.orderVisible = orderVisible;
        setLogoLayoutVisibility();
    }
    /*Progarmatically change Visibility of Logo. By default it is visible*//*Initialize Payment WebView */
    public void setLogoVisible(boolean logoVisible) {
        this.logoVisible = logoVisible;
        setLogoLayoutVisibility();
    }
/* Programatically Set Padding to logoLayout*/
    public void setNormalPadding(int normalPadding) {
        this.normalPadding = normalPadding;
    }

    /*set order text*/
    private void setOrderView() {
        orderTotalView.setPadding(normalPadding, normalPadding, normalPadding, normalPadding);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        orderTotalView.setLayoutParams(params);
        orderTotalView.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER);
        if(orderTotal != null)
            orderTotalView.setText(String.format(mContext.getString(R.string.order_for), orderTotal));
        else
            orderTotalView.setVisibility(INVISIBLE);
    }
}
