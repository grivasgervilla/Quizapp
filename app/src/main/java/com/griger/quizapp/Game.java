package com.griger.quizapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class Game extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question);
    }

    @Override
    public void onClick(View view) {

    }
}