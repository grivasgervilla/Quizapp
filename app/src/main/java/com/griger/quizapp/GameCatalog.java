package com.griger.quizapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

/**
 * Activity that shows other quizz games.
 */
public class GameCatalog extends Activity implements View.OnClickListener {
    /**
     * Where we load game web.
     */
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_catalog);

        webView = (WebView) findViewById(R.id.webview);
        Button game1Btt = (Button) findViewById(R.id.game1Btt);
        game1Btt.setOnClickListener(this);

        WebSettings webViewSettings = webView.getSettings();
        webViewSettings.setJavaScriptEnabled(true); //enable JabaScript int the WebView.

        //Open web in the current WebView, no in device web browser.
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
