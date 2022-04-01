package com.moony.mvvm_pattern_notepad.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RecordDao {
    @Insert
    suspend fun insert(record: Record)

    @Delete
    suspend fun deleteOneRecord(record: Record)

    @Query("DELETE FROM Record WHERE subject_name=:name")
    suspend fun deleteAllSubjectRecordBySubjectName(name:String)

    @Query("SELECT * FROM Record WHERE date=:date")
    fun findRecordsByDate(date:String): Flow<List<Record>>

    @Query("SELECT * FROM Record WHERE subject_name=:name")
    fun findRecordsBySubjectName(name:String):Flow<List<Record>>

    @Query("SELECT * FROM Record")
    fun getAllRecord():List<Record>

}