package com.moony.mvvm_pattern_notepad.data

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

abstract class SubjectDataBase : RoomDatabase() {
    abstract fun subjectDao():SubjectDao
    companion object{
        @Volatile private var instance:SubjectDataBase?=null

        @Synchronized
        fun getInstance(context: Context):SubjectDataBase?{
            if(instance==null){
                synchronized(SubjectDataBase::class){
                    instance= Room.databaseBuilder(
                        context.applicationContext,
                        SubjectDataBase::class.java,
                        "record-database"
                    ).build()


                }
            }
            return instance!!

        }
    }
}