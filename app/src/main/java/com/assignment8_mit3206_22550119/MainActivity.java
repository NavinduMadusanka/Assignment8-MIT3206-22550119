package com.assignment8_mit3206_22550119;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity  {
    Button b1;
    Button btninternal;
    EditText ed1;

    private WebView wv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1=(Button)findViewById(R.id.button);
        ed1=(EditText)findViewById(R.id.editText);
        btninternal=(Button)findViewById(R.id.btninternal);

        wv1=(WebView)findViewById(R.id.webView);
        wv1.setWebViewClient(new MyBrowser());

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = ed1.getText().toString();

                wv1.getSettings().setLoadsImagesAutomatically(true);
                wv1.getSettings().setJavaScriptEnabled(true);
                wv1.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
                wv1.loadUrl(url);
            }
        });

        btninternal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String base = Environment.getExternalStorageDirectory().getAbsolutePath()
                        .toString();
                String imagePath = "file:///"+ base + "/logo.png";
                String html = "<!DOCTYPE html>";
                html += "<head><title>Loading files from SDCard</title></head>";
                html += "<body><img src=\""+ imagePath + "\" />/body>";
                html += "</html>";
                wv1.loadDataWithBaseURL("", html, "text/html","UTF-8", null);
            }
        });



    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }


    }


}