package com.griger.quizapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;
import java.util.Vector;

public class Game extends Activity implements View.OnClickListener {
    //Interface elements
    private TextView textView;
    private ArrayList<Button> buttons = new ArrayList<>();
    private ImageButton soundBtt;
    private ImageView imageView;

    //Auz
    private Question currentQuestion;
    private ListIterator<Question> it;
    private Toast correctAnswerToast;
    private MediaPlayer rightMp, wrongMp, soundPlayer;
    private Dialog wrongDialog;
    private int score = 0;
    private ArrayList<String> answers = new ArrayList<>();
    private ArrayList<Boolean> gotRight = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question);

        textView = (TextView) findViewById(R.id.questionTv);

        buttons.add((Button) findViewById(R.id.answer1Btt));
        buttons.add((Button) findViewById(R.id.answer2Btt));
        buttons.add((Button) findViewById(R.id.answer3Btt));
        buttons.add((Button) findViewById(R.id.answer4Btt));

        soundBtt = (ImageButton) findViewById(R.id.soundBtt);
        imageView = (ImageView) findViewById(R.id.imageIv);

        Collections.shuffle(MainActivity.questions);
        it = MainActivity.questions.listIterator();

        loadNextQuestion();

        for (Button b : buttons)
            b.setOnClickListener(this);

        soundBtt.setOnClickListener(this);

        correctAnswerToast = Toast.makeText(this, R.string.correctAnswer, Toast.LENGTH_SHORT);

        rightMp = MediaPlayer.create(this, R.raw.right);
        wrongMp = MediaPlayer.create(this, R.raw.wrong);

        wrongDialog = new AlertDialog.Builder(this)
                .setTitle(R.string.wrongAnswer)
                .setPositiveButton(R.string.next,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                loadNextQuestion();
                            }
                        })
                .setNegativeButton(R.string.exit,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                //almacenar nueva puntuacion TODO
                                correctAnswerToast.setText("La respeusta correcta era: " + currentQuestion.getCorrectAnswer());
                                correctAnswerToast.show();
                                finish();
                            }
                        })
                .create();

        wrongDialog.setCanceledOnTouchOutside(false);
    }

    private void uploadQuestionInformation (Question q){
        soundBtt.setVisibility(View.GONE);
        imageView.setVisibility(View.GONE);

        Collections.shuffle(buttons);

        textView.setText(q.getQuestion());
        buttons.get(0).setText(q.getCorrectAnswer());
        buttons.get(1).setText(q.getWrongAnswer1());
        buttons.get(2).setText(q.getWrongAnswer2());
        buttons.get(3).setText(q.getWrongAnswer3());

        if (q.getResType() == ResourceType.EMPTY){

        }
        else if (q.getResType() == ResourceType.IMAGE){
            imageView.setVisibility(View.VISIBLE);
            imageView.setImageResource(this.getResources().getIdentifier(q.getRes(), "drawable", this.getPackageName()));
        }
        else {
            soundBtt.setVisibility(View.VISIBLE);
            soundPlayer = MediaPlayer.create(this, this.getResources().getIdentifier(q.getRes(), "raw", this.getPackageName()));
        }
    }

    private void loadNextQuestion() {
        if (it.hasNext()){
            currentQuestion = it.next();
            uploadQuestionInformation(currentQuestion);
        }
        else{
            Intent intent = new Intent(this, Results.class);
            intent.putExtra("SCORE", score);
            intent.putExtra("ANSWERS", answers);
            intent.putExtra("GOT_RIGHT", gotRight);
            Scores.addScore(score);
            finish();
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == buttons.get(0).getId()){
            if (soundPlayer != null)
                stopSound();
            rightMp.start();

            answers.add(currentQuestion.getCorrectAnswer());
            gotRight.add(true);
            score++;

            loadNextQuestion();

            correctAnswerToast.show();
        }
        else if (view.getId() == buttons.get(1).getId() || view.getId() == buttons.get(2).getId() || view.getId() == buttons.get(3).getId()){
            if (soundPlayer != null)
                stopSound();
            wrongMp.start();

            answers.add(currentQuestion.getCorrectAnswer());
            gotRight.add(false);

            wrongDialog.show();
        }
        else if (view.getId() == soundBtt.getId()){
            soundPlayer.start();
        }
    }

    private void stopSound(){
        soundPlayer.stop();
        try {
            soundPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        rightMp.release();
        wrongMp.release();
    }
}