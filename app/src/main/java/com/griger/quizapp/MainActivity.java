package com.griger.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.println("DEBUG: Creando DBHelper.");
        dbHelper = new DBHelper(this);

        dbHelper.list();
    }

    public void goToCatalog(View view) {
        Intent intent = new Intent(this, GameCatalog.class);
        startActivity(intent);
    }
}
