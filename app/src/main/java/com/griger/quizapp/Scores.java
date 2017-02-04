package com.griger.quizapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Vector;

/**
 * Activity that shows score of the player's games.
 */
public class Scores extends Activity {
    /**
     * Player's scores.
     */
    static private Vector<Integer> scores = new Vector<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scores);

        String scoresString = " ";

        for (int i = 0; i < scores.size(); i++)
            scoresString += "Partida " + (i+1) +": " + scores.get(i) + "\n";

        TextView scoreTV = (TextView) findViewById(R.id.scores);
        scoreTV.setText(scoresString);
    }

    static public void addScore (int newScore){
        scores.add(newScore);
    }
}
