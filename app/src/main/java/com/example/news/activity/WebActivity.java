package com.example.news.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.example.news.R;
import com.example.news.utils.NoHttpInstance;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.rest.OnResponseListener;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebActivity extends AppCompatActivity {

    @BindView(R.id.webview_activity_web)
    WebView webviewActivityWeb;
    @BindView(R.id.toolbar_activity_web)
    Toolbar toolbarActivityWeb;
    @BindView(R.id.progress_activity_web)
    ProgressBar progressActivityWeb;
    @BindView(R.id.iv_toolbar_activity_web)
    ImageView ivToolbarActivityWeb;
    @BindView(R.id.collapse_toolbar_activity_web)
    CollapsingToolbarLayout collapseToolbarActivityWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);
        //初始化toolbar
        initToolbar();
        //初始化可折叠的toolbar
        String url = initCollapseToolbar();
        //初始化webview
        initWebview(url);

    }

    /**
     * 初始化webview
     * @param url
     */
    private void initWebview(String url) {
        WebSettings settings = webviewActivityWeb.getSettings();
        //settings.setSupportZoom(true);
        settings.setJavaScriptEnabled(true);//javascript  js脚本，web前端的脚本语言  html5 响应式布局 pc  移动段


        webviewActivityWeb.loadUrl(url);
        webviewActivityWeb.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);

                collapseToolbarActivityWeb.setTitle(title);

            }
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

    /**
     * 初始化可折叠的toolbar
     * @return
     */
    private String initCollapseToolbar() {
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        String img_url = intent.getStringExtra("img_url");
        Glide.with(this).load(img_url).into(ivToolbarActivityWeb);

        Request<Bitmap> imageRequest = NoHttp.createImageRequest(img_url);
        NoHttpInstance.getInstance().add(0, imageRequest, new OnResponseListener<Bitmap>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<Bitmap> response) {

                int vibrantColor = Palette.from(response.get()).generate().getVibrantColor(getResources().getColor(R.color.colorPrimary));

                collapseToolbarActivityWeb.setContentScrimColor(vibrantColor);


            }

            @Override
            public void onFailed(int what, Response<Bitmap> response) {

            }

            @Override
            public void onFinish(int what) {

            }
        });
        return url;
    }

    /**
     * 初始化toolbar
     */
    private void initToolbar() {
        setSupportActionBar(toolbarActivityWeb);//把actionbar替换成toolbar

        ActionBar supportActionBar = getSupportActionBar();

        if (supportActionBar != null) {

            supportActionBar.setHomeButtonEnabled(true);
            supportActionBar.setDisplayHomeAsUpEnabled(true);

        }
    }

    /*@Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
