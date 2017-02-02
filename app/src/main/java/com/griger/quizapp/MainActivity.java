package com.griger.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public static int numero;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("SE LLAMA AL ONCREATE OIGA.");
        numero = 5;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.println("DEBUG: Creando DBHelper.");
        dbHelper = new DBHelper(this);
        dbHelper.addQuestions();
        dbHelper.list();
    }

    public void goToCatalog(View view) {
        Intent intent = new Intent(this, GameCatalog.class);
        startActivity(intent);
    }

    public void startGame(View view) {
        Intent intent = new Intent(this, Game.class);
        startActivity(intent);
    }
}
