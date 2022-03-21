package com.moony.mvvm_pattern_notepad.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RecordDao {
    @Insert
    fun insert(record: Record)

    @Delete
    fun delete(record: Record)

    @Query("SELECT * FROM Record WHERE date=:date")
    fun findRecordsByDate(date:String):List<Record>

    @Query("SELECT * FROM Record WHERE subject_name=:name")
    fun findRecordsBySubject(name:String):List<Record>



}