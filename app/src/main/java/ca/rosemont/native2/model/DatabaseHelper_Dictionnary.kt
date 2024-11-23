package ca.rosemont.native2.model

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper_Dictionnary(context : Context) : SQLiteOpenHelper(context, DATABASE_NOM,null, DATABASE_VERSION) {
    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NOM = "Native2.db"
        const val DATABASE_DICTIONARY_TABLE = "Words"
        const val DATABASE_DICTIONARY_COLUMN_ID = "id"
        const val DATABASE_DICTIONARY_COLUMN_FRENCH = "wordFR"
        const val DATABASE_DICTIONARY_COLUMN_ENGLISH = "wordEN"
        const val DATABASE_DICTIONARY_COLUMN_DIFFICULT = "difficulty"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE = "CREATE TABLE $DATABASE_DICTIONARY_TABLE(" +
                "$DATABASE_DICTIONARY_COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$DATABASE_DICTIONARY_COLUMN_FRENCH TEXT NOT NULL," +
                "$DATABASE_DICTIONARY_COLUMN_ENGLISH TEXT NOT NULL, " +
                "$DATABASE_DICTIONARY_COLUMN_DIFFICULT BYTE NOT NULL);"
        db!!.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val DROP_TABLE = "DROP TABLE IF EXISTS $DATABASE_DICTIONARY_TABLE ;"
        db!!.execSQL(DROP_TABLE)
        onCreate(db)
    }
}