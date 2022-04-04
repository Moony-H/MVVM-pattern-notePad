package com.moony.mvvmstudyrecordapp.data

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

    @Query("SELECT * FROM Record WHERE date=:date ORDER BY start_time_h")
    fun getRecordsByDate(date:String): Flow<List<Record>>

    @Query("SELECT * FROM Record WHERE subject_name=:name ORDER BY start_time_h")
    fun getRecordsBySubjectName(name:String):Flow<List<Record>>

    @Query("SELECT * FROM Record")
    suspend fun getAllRecord():List<Record>

    @Query("DELETE FROM Record")
    suspend fun deleteAllRecord()

}