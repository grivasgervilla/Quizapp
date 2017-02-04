package com.griger.quizapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class that implements our DBHelper. (Singleton)
 */
public class DBHelper extends SQLiteOpenHelper {
    /**
     * singleton instance.
     */
    private static DBHelper INSTANCE = null;

    /**
     * DB name.
     */
    public static final String DB_NAME = "preguntas";

    /**
     * DB version.
     */
    public static final int DB_CURRENT_VERSION = 3;

    /**
     * SQLite DB.
     */
    protected SQLiteDatabase db;

    /**
     * DBHelper context.
     */
    protected Context context;

    /**
     * Method that returns the singleton instance.
     * @param context Context that requires the instance.
     * @return singleton instance.
     */
    public static DBHelper getInstance(Context context) {
        if (INSTANCE == null)
            INSTANCE = new DBHelper(context);

        return INSTANCE;
    }

    /**
     * Class constructor.
     * @param context Contexts that requires the DB.
     */
    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_CURRENT_VERSION);
        this.context = context;
        this.open();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        System.out.println("DEBUG: Creando la BD.");
        StringBuilder sb = new StringBuilder();

        //Read and execute every SQL sentence from raw file.
        Scanner sc = new Scanner(this.context.getResources().openRawResource(R.raw.database));
        while(sc.hasNextLine()) {
            sb.append(sc.nextLine());
            sb.append('\n');
            if (sb.indexOf(";") != -1) {
                System.out.println(sb.toString());
                sqLiteDatabase.execSQL(sb.toString());
                sb.delete(0, sb.capacity());
            }
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        System.out.println("DEBUG: Actualizando la base de datos desde la versión" + i + "a la versión" + i1);
    }

    public void open() {
        System.out.println("DEBUG: Abriendo la base de datos.");
        this.db = this.getWritableDatabase();
    }

    public void close() {
        System.out.println("DEBUG: Cerrando la base de datos.");
        this.db.close();
    }

    /**
     * Method that load data from a CSV formatted file into the DB.
     */
    public void addQuestions() {
        this.db.execSQL("DELETE FROM " + DB_NAME + ";");

        Scanner sc = new Scanner(this.context.getResources().openRawResource(R.raw.questions));
        //Row fields are separated by |.
        String[] columnNames = sc.nextLine().split("\\|");

        while(sc.hasNextLine()) {
            //Read each line and create values to insert.
            String line = sc.nextLine();
            String[] values = line.split("\\|");
            ContentValues valuesDB = new ContentValues();

            for (int i = 0; i < 6; i++)
                valuesDB.put(columnNames[i], values[i]);

            if (values.length == 8) {
                valuesDB.put(columnNames[6], values[6]);
                valuesDB.put(columnNames[7], values[7]);
            }

            //Insert new values.
            db.insert(DB_NAME, null, valuesDB);
        }
    }

    /**
     * Method that returns DB contents with a good format.
     * @return DB contents in Question format.
     */
    public ArrayList<Question> getQuestions() {
        ArrayList<Question> questions = new ArrayList<>();
        //Get
        Cursor c = this.db.rawQuery("SELECT * FROM preguntas", null);

        String question, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3, typeString, res, resType;
        QuestionType type;

        while (c.moveToNext()){
            question = c.getString(c.getColumnIndex("question"));
            correctAnswer = c.getString(c.getColumnIndex("correctAnswer"));
            wrongAnswer1 = c.getString(c.getColumnIndex("wrongAnswer1"));
            wrongAnswer2 = c.getString(c.getColumnIndex("wrongAnswer2"));
            wrongAnswer3 = c.getString(c.getColumnIndex("wrongAnswer3"));
            typeString = c.getString(c.getColumnIndex("type"));
            res = c.getString(c.getColumnIndex("res"));
            resType = c.getString(c.getColumnIndex("resType"));

            switch (typeString){
                case "G":
                    type = QuestionType.GENRES;
                    break;
                case "C":
                    type = QuestionType.COMPOSERS;
                    break;
                case "I":
                    type = QuestionType.INSTUMENTS;
                    break;
                case "H":
                    type = QuestionType.HISTORY;
                    break;
                default:
                    type = QuestionType.BASIC;
                    break;
            }

            if (resType.equals("N"))
                questions.add(new Question(question, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3, type));
            else if (resType.equals("I"))
                questions.add(new Question(question, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3, type, res, ResourceType.IMAGE));
            else if (resType.equals("S"))
                questions.add(new Question(question, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3, type, res, ResourceType.SOUND));

        }

        return questions;
    }



}
