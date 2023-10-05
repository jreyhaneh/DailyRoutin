package com.example.dailyroutin.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.example.dailyroutin.FirstFragment
import com.example.dailyroutin.RoutinAdapter

class DatabaseManager(private val context: Context) {

    private var dbHelper: DatabaseHelper? = null
    private var database: SQLiteDatabase? = null

    @Throws(SQLException::class)
    fun open(): DatabaseManager {
        dbHelper = DatabaseHelper(context, DatabaseHelper.DATABASE_TABLE, null, 1)
        database = dbHelper?.writableDatabase

        return this
    }

    fun close() {
        dbHelper!!.close()
    }

    fun insert(todo: String?, detail: String, isChecked: Boolean?): Long {
        val contentValues = ContentValues()
        contentValues.put(DatabaseHelper.TODO, todo)
        contentValues.put(DatabaseHelper.IS_CHECKED, isChecked)
        contentValues.put(DatabaseHelper.DETAIL, detail)
        return database!!.insert(DatabaseHelper.DATABASE_TABLE, null, contentValues)
    }

    fun fetch(): Cursor? {

        val columns =
            arrayOf(
                DatabaseHelper.TODO_ID,
                DatabaseHelper.TODO,
                DatabaseHelper.DETAIL,
                DatabaseHelper.IS_CHECKED
            )
        val cursor =
            database!!.query(DatabaseHelper.DATABASE_TABLE, columns, null, null, null, null, null)
        cursor?.moveToFirst()
        return cursor
    }

    fun update(id: Long, todo: String?, isChecked: Boolean?): Int {
        val contentValues = ContentValues()
        contentValues.put(DatabaseHelper.IS_CHECKED, isChecked)
        contentValues.put(DatabaseHelper.TODO, todo)
//        contentValues.put(DatabaseHelper.DETAIL, detail)
        val x = database!!.update(
            DatabaseHelper.DATABASE_TABLE,
            contentValues,
            DatabaseHelper.TODO_ID + "=" + id,
            null
        )
        return x
    }

    fun delete(id: Long) {
        database!!.delete(DatabaseHelper.DATABASE_TABLE, DatabaseHelper.TODO_ID + "=" + id, null)
    }
}