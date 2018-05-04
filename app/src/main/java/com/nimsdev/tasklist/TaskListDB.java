package com.nimsdev.tasklist;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class TaskListDB {

    //DB constants
    public static final String DB_NAME = "tasklist.db";
    public static final int DB_VERSION = 1;

    // list table constants

    public static final String LIST_TABLE = "list";

    public static final String LIST_ID = "_id";
    public static final int LIST_ID_COL = 0;

    public static final String LIST_NAME = "list_name";
    public static final int LIST_NAME_COL = 1;

    // task table constants
    public static final String TASK_TABLE = "task";

    public static final String TASK_ID = "_id";
    public static final int TASK_ID_COL = 0;

    public static final String TASK_LIST_ID = "list_id";
    public static final int TASK_LIST_ID_COL = 1;

    public static final String TASK_NAME = "task_name";
    public static final int TASK_NAME_COL = 2;

    public static final String TASK_NOTES = "notes";
    public static final int TASK_NOTES_COL = 3;

    public static final String TASK_COMPLETED = "date_completed";
    public static final int TASK_COMPLETED_COL = 4;

    public static final String TASK_HIDDEN = "hidden";
    public static final int TASK_HIDDEN_COL = 5;

    // CREATE and DROP TABLE statements
    public static final String CREATE_LIST_TABLE =
            "CREATE TABLTE " + LIST_TABLE + " (" +
                    LIST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    LIST_NAME + " TEXT NOT NULL UNIQUE);";

    public static final String CREATE_TASK_TABLE =
            "CREATE TABLTE " + TASK_TABLE + " (" +
                    TASK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    TASK_LIST_ID + " INTEGER NOT NULL, " +
                    TASK_NAME + " TEXT NOT NULL" +
                    TASK_NOTES + " TEXT" +
                    TASK_COMPLETED + " TEXT" +
                    TASK_HIDDEN + " TEXT);";

    public static final String DROP_LIST_TABLE =
            "DROP TABLE IF EXISTS " + LIST_TABLE;

    public static final String DROP_TASK_TABLE =
            "DROP TABLE IF EXISTS " + TASK_TABLE;


    private static class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context, String name,
                        SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_LIST_TABLE);
            db.execSQL(CREATE_TASK_TABLE);

            // insert default lists
            db.execSQL("INSERT INTO list VALUES (1, 'Personal')");
            db.execSQL("INSERT INTO list VALUES (2, 'Business')");

            // insert sample tasks
            db.execSQL("INSERT INTO task VALUES (1, 1, 'Pay bills', " +
                    "'Rent\nPhone\nInternet', '0', '0')");

            db.execSQL("INSERT INTO task VALUES (2, 1, 'Get hair cut', " +
                    "'', '0', '0')");

        }

        @Override
        public void onUpgrade(SQLiteDatabase db,
                              int oldVersion, int newVersion) {
            Log.d("Task list", "Upgrading db from version "
            + oldVersion + "to " + newVersion);

            db.execSQL(TaskListDB.DROP_LIST_TABLE);
            db.execSQL(TaskListDB.DROP_TASK_TABLE);
            onCreate(db);
        }
    }

    // database object and database helper object
    private SQLiteDatabase db;
    private DBHelper dbHelper;

    // constructor
    public TaskListDB(Context context) {
        this.dbHelper = new DBHelper(context, DB_NAME, null, DB_VERSION);
    }

    //private methods
    private void openReadableDB() {
        db = dbHelper.getReadableDatabase();
    }

    //private methods
    private void openWriteableDB() {
        db = dbHelper.getWritableDatabase();
    }
    
    private void closeDB() {
        if (db != null) db.close();
    }
    
    public ArrayList<Task> getTasks(String listName) {
        String where = TASK_LIST_ID + "= ? AND " + TASK_HIDDEN + "!='1'";
        int listID = getList(listName).getId();
        String[] whereArgs = { Integer.toString(listID) };
        
        this.openReadableDB();
        Cursor cursor = db.query(TASK_TABLE, null,
                where, whereArgs,
                null, null, null);
        ArrayList<Task> tasks = new ArrayList<Task>();
        while (cursor.moveToNext()) {
            tasks.add(getTaskFromCursor(cursor));
        }

        if (cursor != null) cursor.close();
        this.closeDB();

        return tasks;
    }

    private Task getTaskFromCursor(Cursor cursor) {
    }

    private List getList(String listName) {
        return null;
    }

}
