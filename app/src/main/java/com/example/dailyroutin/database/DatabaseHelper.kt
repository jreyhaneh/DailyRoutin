package com.example.dailyroutin.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context, databaseTable: String, nothing: Nothing?, i: Int) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {



    companion object {
        private const val DATABASE_NAME = "database.db"
        private const val DATABASE_VERSION = 1
        val DATABASE_TABLE = "TODO"
        val TODO_ID = "ID"
        val TODO = "todo"
        val IS_CHECKED = "isChecked"
        val DETAIL ="detail"
        val CREAT_DB_QUERY = "CREATE TABLE " + DATABASE_TABLE + " (" + TODO_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " + TODO + " TEXT NOT NULL, " + IS_CHECKED + " TEXT NOT NULL, " + DETAIL + " TEXT NOT NULL)"

    }


    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL(CREAT_DB_QUERY)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL(" DROP TABLE IF EXISTS $DATABASE_TABLE")
    }

}