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

/**
 * Activity that implements game mechanics.
 */
public class Game extends Activity implements View.OnClickListener {
    //Interface elements

    /**
     * Where we place the wording.
     */
    private TextView textView;

    /**
     * Buttons where we place answer options.
     */
    private ArrayList<Button> buttons = new ArrayList<>();

    /**
     * Button to play question sound (JIC).
     */
    private ImageButton soundBtt;

    /**
     * Where we place question image (JIC).
     */
    private ImageView imageView;

    //Auxiliar elements

    /**
     * current game question.
     */
    private Question currentQuestion;

    /**
     * question iterator.
     */
    private ListIterator<Question> it;

    /**
     * tell the user that he had got right.
     */
    private Toast correctAnswerToast;

    /**
     * media players.
     */
    private MediaPlayer rightMp, wrongMp, soundPlayer;

    /**
     * tell the user that he had got wrong.
     */
    private Dialog wrongDialog;

    /**
     * current game player score.
     */
    private int score = 0;

    /**
     * Right game answers.
     */
    private ArrayList<String> answers = new ArrayList<>();

    /**
     * had user got right or wrong on this question?
     */
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

        Collections.shuffle(MainActivity.questions);//randomize question appearance.
        it = MainActivity.questions.listIterator();

        loadNextQuestion(); //load first question.

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
                                correctAnswerToast.setText("La respuesta correcta era: " + currentQuestion.getCorrectAnswer());
                                correctAnswerToast.show(); //Show correct answer.
                                finish();
                            }
                        })
                .create();

        //If the user doesn't touch one dialog's button thet it doesn't dissapear.
        wrongDialog.setCanceledOnTouchOutside(false);
    }

    /**
     * Method that loads question data into the game screen.
     * @param q the question.
     */
    private void uploadQuestionInformation (Question q){
        //Questions don't have sound or image by default.
        soundBtt.setVisibility(View.GONE);
        imageView.setVisibility(View.GONE);

        Collections.shuffle(buttons); //randomize answer options order.

        textView.setText(q.getQuestion());
        buttons.get(0).setText(q.getCorrectAnswer());
        buttons.get(1).setText(q.getWrongAnswer1());
        buttons.get(2).setText(q.getWrongAnswer2());
        buttons.get(3).setText(q.getWrongAnswer3());

        if (q.getResType() == ResourceType.IMAGE){ //the question have an image.
            imageView.setVisibility(View.VISIBLE);
            imageView.setImageResource(this.getResources().getIdentifier(q.getRes(), "drawable", this.getPackageName()));
        }
        else if (q.getResType() == ResourceType.SOUND) { //the question hava a sound.
            soundBtt.setVisibility(View.VISIBLE);
            soundPlayer = MediaPlayer.create(this, this.getResources().getIdentifier(q.getRes(), "raw", this.getPackageName()));
        }
    }

    /**
     * Method that iterate over game questions.
     */
    private void loadNextQuestion() {
        if (it.hasNext()){
            currentQuestion = it.next();
            uploadQuestionInformation(currentQuestion);
        }
        else{ //if there aren't more questions then go to results screen.
            Intent intent = new Intent(this, Results.class);
            intent.putExtra("SCORE", score);
            intent.putExtra("ANSWERS", answers);
            intent.putExtra("GOT_RIGHT", gotRight);
            Scores.addScore(score); //save player score.
            finish();
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == buttons.get(0).getId()){ //Player choose right answer.
            if (soundPlayer != null)
                stopSound(); //stop question sound.
            rightMp.start(); //play right answer sound.

            answers.add(currentQuestion.getCorrectAnswer());
            gotRight.add(true);
            score++;

            loadNextQuestion();

            correctAnswerToast.show(); //tell player that he had got right.
        }
        else if (view.getId() == buttons.get(1).getId() || view.getId() == buttons.get(2).getId() || view.getId() == buttons.get(3).getId()){ //user choose a wrong answer.
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

    /**
     * Method that stop question sound.
     */
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
        if (soundPlayer != null)
            soundPlayer.release();
    }
}