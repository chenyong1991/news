package com.example.news.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.example.news.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebActivity extends AppCompatActivity {

    @BindView(R.id.webview_activity_web)
    WebView webviewActivityWeb;
    @BindView(R.id.toolbar_activity_web)
    Toolbar toolbarActivityWeb;
    @BindView(R.id.progress_activity_web)
    ProgressBar progressActivityWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);

        setSupportActionBar(toolbarActivityWeb);//把actionbar替换成toolbar

        ActionBar supportActionBar = getSupportActionBar();

        if (supportActionBar != null) {

            supportActionBar.setHomeButtonEnabled(true);
            supportActionBar.setDisplayHomeAsUpEnabled(true);

        }


        Intent intent = getIntent();
        String url = intent.getStringExtra("url");

        //config  setting设置类
        WebSettings settings = webviewActivityWeb.getSettings();
        //settings.setSupportZoom(true);
        settings.setJavaScriptEnabled(true);//javascript  js脚本，web前端的脚本语言  html5 响应式布局 pc  移动段


        webviewActivityWeb.loadUrl(url);
        webviewActivityWeb.setWebChromeClient(new WebChromeClient() {

        });
        webviewActivityWeb.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressActivityWeb.setVisibility(View.INVISIBLE);
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}
