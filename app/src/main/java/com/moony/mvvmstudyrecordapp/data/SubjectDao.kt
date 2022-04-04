package com.moony.mvvmstudyrecordapp.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface SubjectDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(subject:Subject)

    @Delete
    suspend fun delete(subject: Subject)

    @Query("SELECT * FROM Subject")
    fun getAll():Flow<List<Subject>>

    @Update
    suspend fun updateSubject(subject: Subject)

    @Query("SELECT * FROM Subject WHERE name=:name")
    suspend fun findSubjectByName(name:String):Subject

}