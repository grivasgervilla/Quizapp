package com.griger.quizapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.style.SubscriptSpan;

import java.util.Scanner;

/**
 * Created by pc on 31/01/2017.
 */
public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "preguntas";
    public static final int DB_CURRENT_VERSIOÑ = 2;
    protected SQLiteDatabase db;
    protected Context context;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_CURRENT_VERSIOÑ);
        this.context = context;
        this.open();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        System.out.println("DEBUG: Creando la BD.");
        StringBuilder sb = new StringBuilder();

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

    public void list(){
        System.out.println("DEBUG: Obteniendo todas las columnas de la DB.");
        Cursor c = this.db.rawQuery("SELECT * FROM preguntas", null);
        System.out.println("DEBUG: Veamos los nombres de las columnas:");
        String[] nombres = c.getColumnNames();

        for (String n : nombres){
            System.out.println(n);
        }

        System.out.println("El número de filas que hayyaya:" + c.getCount());
        c.moveToFirst();
        System.out.println(c.getString(c.getColumnIndex("question")));
    }

    public void addQuestions() {
        this.db.execSQL("DELETE FROM " + DB_NAME + ";");

        Scanner sc = new Scanner(this.context.getResources().openRawResource(R.raw.questions));
        String[] columnNames = sc.nextLine().split("\\|");

        while(sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] values = line.split("\\|");
            ContentValues valuesDB = new ContentValues();

            for (int i = 0; i < 6; i++)
                valuesDB.put(columnNames[i], values[i]);

            if (values.length == 8) {
                valuesDB.put(columnNames[6], values[6]);
                valuesDB.put(columnNames[7], values[7]);
            }

            db.insert(DB_NAME, null, valuesDB);
        }
    }


}
