package com.griger.quizapp;

import android.app.Activity;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

/**
 * Created by pc on 28/01/2017.
 */

public class GameCatalog extends Activity implements View.OnClickListener {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("El numero:" + MainActivity.numero);
        MainActivity.numero = 41192;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_catalog);

        webView = (WebView) findViewById(R.id.webview);
        Button game1Btt = (Button) findViewById(R.id.game1Btt);
        game1Btt.setOnClickListener(this);

        WebSettings webViewSettings = webView.getSettings();
        webViewSettings.setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url){
                view.loadUrl(url);
                return true;
            }
        });
    }

    @Override
    public void onClick(View v){
        if (v.getId() == R.id.game1Btt){
            webView.loadUrl("http://lol.disney.com/games/quiz-games");
        }

    }
}
