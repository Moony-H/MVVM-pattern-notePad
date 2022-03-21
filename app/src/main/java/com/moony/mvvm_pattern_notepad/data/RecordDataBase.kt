package com.moony.mvvm_pattern_notepad.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.InternalCoroutinesApi


@Database(entities = [Record::class],version=1)
abstract class RecordDataBase:RoomDatabase() {
    abstract fun recordDao():RecordDao
    companion object{
        @Volatile private var instance:RecordDataBase?=null

        @Synchronized
        fun getInstance(context: Context):RecordDataBase?{
            if(instance==null){
                synchronized(RecordDataBase::class){
                    instance= Room.databaseBuilder(
                        context.applicationContext,
                        RecordDataBase::class.java,
                        "record-database"
                    ).build()


                }
            }
            return instance

        }
    }
}