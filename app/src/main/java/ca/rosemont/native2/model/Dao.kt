package ca.rosemont.native2.model

import android.content.ContentValues

class Dao(val helper: DatabaseHelper_Dictionnary) {
    fun insertWord(word : Word) {
        val db = helper.writableDatabase
        db.insert(DatabaseHelper_Dictionnary.DATABASE_DICTIONARY_TABLE,null,ContentValues().apply {
            put(DatabaseHelper_Dictionnary.DATABASE_DICTIONARY_COLUMN_ID,word.id)
            put(DatabaseHelper_Dictionnary.DATABASE_DICTIONARY_COLUMN_FRENCH,word.wordFr)
            put(DatabaseHelper_Dictionnary.DATABASE_DICTIONARY_COLUMN_ENGLISH,word.wordEn)
            put(DatabaseHelper_Dictionnary.DATABASE_DICTIONARY_COLUMN_DIFFICULT,word.difficult)
        })
        db.close()
    }
    fun removeWord(word : Word) {
        val db = helper.writableDatabase
        db.delete(DatabaseHelper_Dictionnary.DATABASE_DICTIONARY_TABLE, "${DatabaseHelper_Dictionnary.DATABASE_DICTIONARY_COLUMN_FRENCH} = ? AND ${DatabaseHelper_Dictionnary.DATABASE_DICTIONARY_COLUMN_ENGLISH} = ? AND ${DatabaseHelper_Dictionnary.DATABASE_DICTIONARY_COLUMN_DIFFICULT} = ?", arrayOf(word.wordFr,word.wordEn, word.difficult.toString()))
        db.close()
    }
    fun getAllWord():List<Word>{
        val wordList = mutableListOf<Word>()
        val db = helper.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM ${DatabaseHelper_Dictionnary.DATABASE_DICTIONARY_TABLE}",null)
        while (cursor.moveToNext()){
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper_Dictionnary.DATABASE_DICTIONARY_COLUMN_ID))
            val fr = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper_Dictionnary.DATABASE_DICTIONARY_COLUMN_FRENCH))
            val en = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper_Dictionnary.DATABASE_DICTIONARY_COLUMN_ENGLISH))
            val difficult = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper_Dictionnary.DATABASE_DICTIONARY_COLUMN_DIFFICULT))
            wordList.add(Word(id,fr,en,difficult.toByte()))
        }
        cursor.close()
        db.close()
        return wordList
    }
    fun getSize():Int {
        val db = helper.readableDatabase
        val cursor = db.rawQuery("SELECT COUNT(*) FROM ${DatabaseHelper_Dictionnary.DATABASE_DICTIONARY_TABLE}", null)
        cursor.moveToFirst()
        val size = cursor.getInt(0)
        cursor.close()
        db.close()
        return size
    }
}