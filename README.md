# -PaymentWebview
It is a demo project of opening url in WebView and also it change urls the URL at dynomic time. It is basically
use for the opening Base URL of Payment Gateway and close when Webview when Redirect url meet.

## How to use?
1.download mylibrary folder and add its dependency in you app level build.gradle file.
``` java
   dependencies {
        compile project(':mylibrary')
   }
```
2. add PaymentWebView Layout in your abc.layout xml file
``` xml
   <com.example.mylibrary.MobikulPaymentLayout
        android:id="@+id/paymentWebView"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:logoVisible="true"
        app:orderVisible="true"
       />
```
3. Initilize <b>BASE_URL</b> and <b>Redirect_URL</b> in your Activity.
 ```
    MobikulPaymentLayout paymentWebView = (MobikulPaymentLayout) findViewById(R.id.paymentWebView);
    paymentWebView.initilize(BASE_URL, REDIRECT_URL,  "500$", context);
```  


## Licence

MIT License

Copyright (c) 2018 Webkul

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
