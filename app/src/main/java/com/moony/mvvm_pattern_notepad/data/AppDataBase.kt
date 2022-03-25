package com.moony.mvvm_pattern_notepad.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Subject::class, Record::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun subjectDao():SubjectDao
    abstract fun RecordDao():RecordDao
    companion object{
        @Volatile private var instance:AppDataBase?=null

        @Synchronized
        fun getInstance(context: Context):AppDataBase{
            return instance?: synchronized(this){
                val temp=buildDatabase(context)
                instance=temp
                temp

            }


        }

        private fun buildDatabase(context: Context):AppDataBase{
            return Room.databaseBuilder(
                context.applicationContext,
                AppDataBase::class.java,
                "app-database"
            ).build()


        }
    }
}