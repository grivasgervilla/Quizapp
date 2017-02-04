package com.griger.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

/**
 * Class that implements main app activity.
 */
public class MainActivity extends AppCompatActivity {
    /**
     * DB questions.
     */
    public static ArrayList<Question> questions;

    /**
     * DB Helper.
     */
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = DBHelper.getInstance(this);
        dbHelper.addQuestions();
        questions = dbHelper.getQuestions();
    }

    /**
     * Method that starts an activity where we show other quiz games.
     * @param view
     */
    public void goToCatalog(View view) {
        Intent intent = new Intent(this, GameCatalog.class);
        startActivity(intent);
    }

    /**
     * Method that starts a game activity.
     * @param view
     */
    public void startGame(View view) {
        Intent intent = new Intent(this, Game.class);
        startActivity(intent);
    }

    /**
     * Method that starts an activity where we show user's scores.
     * @param view
     */
    public void viewScores(View view) {
        Intent intent = new Intent(this, Scores.class);
        startActivity(intent);
    }
}
