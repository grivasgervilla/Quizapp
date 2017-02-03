package com.griger.quizapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Vector;

/**
 * Created by pc on 03/02/2017.
 */

public class Scores extends Activity {
    static private Vector<Integer> scores = new Vector<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scores);

        String scoresString = " ";

        for (int score : scores)
            scoresString += score + "\n";

        TextView scoreTV = (TextView) findViewById(R.id.scores);
        scoreTV.setText(scoresString);
    }

    static public void addScore (int newScore){
        scores.add(newScore);
    }
}
