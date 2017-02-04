package com.griger.quizapp;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;


import java.util.ArrayList;

/**
 * Created by pc on 03/02/2017.
 */

public class Results extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results);

        int score = getIntent().getIntExtra("SCORE", 0);
        ArrayList<Boolean> gotRight = (ArrayList<Boolean>) getIntent().getExtras().getSerializable("GOT_RIGHT");
        ArrayList<String> answers = (ArrayList<String>) getIntent().getExtras().getSerializable("ANSWERS");

        TextView scoreTV = (TextView) findViewById(R.id.scoreTV);
        TextView answersTV = (TextView) findViewById(R.id.answerTV);

        String scoreString = "Aciertos: ";

        for (int i = 0; i < score; i++)
            scoreString += "&#9835;";

        scoreTV.setText(Html.fromHtml(scoreString));

        String redHTML = "<font color=\"red\">";
        String greenHTML = "<font color=\"green\">";
        String endHTML = "</font><br />";

        String answersString = "Respuesta correctas:" + endHTML;

        for (int i = 0; i < answers.size(); i++)
            if (gotRight.get(i))
                answersString += greenHTML + answers.get(i) + endHTML;
            else
                answersString += redHTML + answers.get(i) + endHTML;

        answersTV.setText(Html.fromHtml(answersString));

        System.out.println("Final de la partida:");
        System.out.println(answers);
        System.out.println(gotRight);
    }

    public void back(View view) {
        finish();
    }
}
