package com.example.room_mvvm.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.room_mvvm.dao.NoteDAO
import com.example.room_mvvm.model.NoteModel

@Database(entities = [NoteModel::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun getNoteDAO() : NoteDAO

    companion object{
        @Volatile
        private var instance : NoteDatabase? = null

        fun getInstance(context: Context) : NoteDatabase{
            if (instance==null){
                instance = Room.databaseBuilder(context,NoteDatabase::class.java,"note_database").build()
            }
            return instance!!
        }


    }
}