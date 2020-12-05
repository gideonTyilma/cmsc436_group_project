package com.example.habitandhobby

import android.app.Application
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteOpenHelper

@Database(entities = [Goal::class, CurrentActivity::class],
    version=1)
abstract class goalsDB: RoomDatabase() {

    companion object {
        fun get(application: Application):goalsDB{
            return Room.databaseBuilder(application,goalsDB::class.java,"database").build()
        }
    }

    abstract fun dataDao(): dataDao


}