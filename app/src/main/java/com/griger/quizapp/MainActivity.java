package com.griger.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static ArrayList<Question> questions;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(this);
        dbHelper.addQuestions();
        questions = dbHelper.getQuestions();
    }

    public void goToCatalog(View view) {
        Intent intent = new Intent(this, GameCatalog.class);
        startActivity(intent);
    }

    public void startGame(View view) {
        Intent intent = new Intent(this, Game.class);
        startActivity(intent);
    }

    public void viewScores(View view) {
        Intent intent = new Intent(this, Scores.class);
        startActivity(intent);
    }
}
