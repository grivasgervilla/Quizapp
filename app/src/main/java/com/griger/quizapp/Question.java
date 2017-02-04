package com.griger.quizapp;

/**
 * Class that store a question elements.
 */

public class Question {
    /**
     * question wording.
     */
    private String question;

    /**
     * question right answer.
     */
    private String correctAnswer;

    /**
     * a question wrong answer.
     */
    private String wrongAnswer1;

    /**
     * a question wrong answer.
     */
    private String wrongAnswer2;

    /**
     * a question wrong answer.
     */
    private String wrongAnswer3;

    /**
     * question resource (image or sound).
     */
    private String res = null;

    /**
     * question category
     */
    private QuestionType type;

    /**
     * question's resource type.
     */
    private ResourceType resType;

    /**
     * Class constructor.
     * @param question
     * @param correctAnswer
     * @param wrongAnswer1
     * @param wrongAnswer2
     * @param wrongAnswer3
     * @param type
     */
    public Question(String question, String correctAnswer, String wrongAnswer1, String wrongAnswer2, String wrongAnswer3, QuestionType type) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.wrongAnswer1 = wrongAnswer1;
        this.wrongAnswer2 = wrongAnswer2;
        this.wrongAnswer3 = wrongAnswer3;
        this.type = type;

        this.resType = ResourceType.EMPTY;
    }

    /**
     * Class constructor.
     * @param question
     * @param correctAnswer
     * @param wrongAnswer1
     * @param wrongAnswer2
     * @param wrongAnswer3
     * @param type
     * @param res
     * @param resType
     */
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

    public String getQuestion() {
        return question;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public String getWrongAnswer1() {
        return wrongAnswer1;
    }

    public String getWrongAnswer2() {
        return wrongAnswer2;
    }

    public String getWrongAnswer3() {
        return wrongAnswer3;
    }

    public ResourceType getResType() {
        return resType;
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
