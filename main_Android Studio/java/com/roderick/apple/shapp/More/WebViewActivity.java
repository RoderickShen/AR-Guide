package com.roderick.apple.shapp.More;

/**
 * Created by dell on 2018/3/30.
 */

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.roderick.apple.shapp.R;

public class WebViewActivity extends Activity{
    private String url="";
    private WebView webView;
    private ProgressDialog dialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.webview_layout);
        Intent intent1=getIntent();//上一个返回的界面
        //  Uri uri = Uri.parse(url);
        // Intent intent=new Intent(Intent.ACTION_VIEW,uri);
        //startActivity(intent);
        init();
    }

    private void init() {
        webView=(WebView)findViewById(R.id.webView);
        //webView加载本地资源
        webView.loadUrl("file:android_asset");
        url=getIntent().getStringExtra("content_url");
        //webView加载web资源
        webView.loadUrl(url);
        //覆盖webView默认通过第三方或者系统浏览器打开的行为，使得在webview中打开
        webView.setWebViewClient(new WebViewClient());
        //启用支持JavaScript
        WebSettings settings=webView.getSettings();
        settings.setJavaScriptEnabled(true);
        //WebView加载页面优先使用缓存加载
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        WebSettings webSettings = webView.getSettings();
        //webview
        webSettings.setJavaScriptEnabled(true);
        //启用数据库
        webSettings.setDatabaseEnabled(true);
        //设置定位的数据库路径
        String dir = this.getApplicationContext().getDir("database", Context.MODE_PRIVATE).getPath();
        webSettings.setGeolocationDatabasePath(dir);
        //启用地理定位
        webSettings.setGeolocationEnabled(true);
        //开启DomStorage缓存
        webSettings.setDomStorageEnabled(true);
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedIcon(WebView view, Bitmap icon) {
                super.onReceivedIcon(view, icon);
            }

            @Override
            public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
                callback.invoke(origin, true, false);
                super.onGeolocationPermissionsShowPrompt(origin, callback);
            }

            //设置进度框
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                //newProgress 1-100之间的整数
                if(newProgress==100)
                {
                    //网页加载完毕,关闭ProgressDialog
                    closeDialog();
                }
                else
                {
                    //网页正在加载,打开ProgressDialog
                    openDialog(newProgress);
                }
            }
            private void closeDialog() {
                if(dialog!=null&&dialog.isShowing())
                {
                    dialog.dismiss();
                    dialog=null;
                }
            }
            private void openDialog(int newProgress){
                if(dialog==null)
                {
                    dialog=new ProgressDialog(WebViewActivity.this);
                    dialog.setTitle("正在加载");
                    dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    dialog.setProgress(newProgress);
                    dialog.show();
                }
                else
                {
                    dialog.setProgress(newProgress);
                }
            }
        });
    }
    //改写物理按键——返回逻辑
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK)
        {
            if(webView.canGoBack())
            {
                webView.goBack();//能返回上一界面则返回上一界面
                return true;
            }
            else
            {
                finish();//退出程序
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}

