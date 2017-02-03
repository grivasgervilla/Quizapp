package com.griger.quizapp;

/**
 * Created by pc on 02/02/2017.
 */

public class Question {
    private String question;
    private String correctAnswer;
    private String wrongAnswer1;
    private String wrongAnswer2;
    private String wrongAnswer3;
    private String res = null;
    private QuestionType type;
    private ResourceType resType;

    public Question(String question, String correctAnswer, String wrongAnswer1, String wrongAnswer2, String wrongAnswer3, QuestionType type) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.wrongAnswer1 = wrongAnswer1;
        this.wrongAnswer2 = wrongAnswer2;
        this.wrongAnswer3 = wrongAnswer3;
        this.type = type;

        this.resType = ResourceType.EMPTY;
    }

    public Question(String question, String correctAnswer, String wrongAnswer1, String wrongAnswer2, String wrongAnswer3, QuestionType type, String res, ResourceType resType) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.wrongAnswer1 = wrongAnswer1;
        this.wrongAnswer2 = wrongAnswer2;
        this.wrongAnswer3 = wrongAnswer3;
        this.type = type;
        this.res = res;
        this.resType = resType;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public QuestionType getType() {
        return type;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }

    public String getWrongAnswer1() {
        return wrongAnswer1;
    }

    public void setWrongAnswer1(String wrongAnswer1) {
        this.wrongAnswer1 = wrongAnswer1;
    }

    public String getWrongAnswer2() {
        return wrongAnswer2;
    }

    public void setWrongAnswer2(String wrongAnswer2) {
        this.wrongAnswer2 = wrongAnswer2;
    }

    public String getWrongAnswer3() {
        return wrongAnswer3;
    }

    public void setWrongAnswer3(String wrongAnswer3) {
        this.wrongAnswer3 = wrongAnswer3;
    }

    public ResourceType getResType() {
        return resType;
    }

    public void setResType(ResourceType resType) {
        this.resType = resType;
    }

    @Override
    public String toString() {
        String description = "";
        description += "Question: " + question + "\n";
        description += "Correct Answer: " + correctAnswer + "\n";
        description += "Wrong Answer 1:" + wrongAnswer1 + "\n";
        description += "Wrong Answer 2:" + wrongAnswer2 + "\n";
        description += "Wrong Answer 3:" + wrongAnswer3 + "\n";
        description += "Question Type:" + type + "\n";

        if (res != null){
            description += "Resource: " + res + "\n";
            description += "Resource Type: " + resType;
        }

        return description;
    }
}
