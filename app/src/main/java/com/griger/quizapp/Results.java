package com.griger.quizapp;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;


import java.util.ArrayList;

/**
 * Activity that shows player game's results.
 */
public class Results extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results);

        //Get data from parent activity.
        int score = getIntent().getIntExtra("SCORE", 0);
        ArrayList<Boolean> gotRight = (ArrayList<Boolean>) getIntent().getExtras().getSerializable("GOT_RIGHT");
        ArrayList<String> answers = (ArrayList<String>) getIntent().getExtras().getSerializable("ANSWERS");

        TextView scoreTV = (TextView) findViewById(R.id.scoreTV);
        TextView answersTV = (TextView) findViewById(R.id.answerTV);

        String scoreString = "Aciertos: ";

        for (int i = 0; i < score; i++)
            scoreString += "&#9835;";

        //Show right game answer.
        scoreTV.setText(Html.fromHtml(scoreString));

        String redHTML = "<font color=\"red\">";
        String greenHTML = "<font color=\"green\">";
        String endHTML = "</font><br />";

        String answersString = "Respuesta correctas:" + endHTML;

        for (int i = 0; i < answers.size(); i++)
            if (gotRight.get(i))
                answersString += greenHTML + answers.get(i) + endHTML; //green if player got right.
            else
                answersString += redHTML + answers.get(i) + endHTML; //red if player got wrong.

        answersTV.setText(Html.fromHtml(answersString));

        System.out.println("Final de la partida:");
        System.out.println(answers);
        System.out.println(gotRight);
    }

    /**
     * Method that terminates this activity.
     * @param view
     */
    public void back(View view) {
        finish();
    }
}
