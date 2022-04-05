package com.moony.mvvmstudyrecordapp.data

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface SubjectDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(subject:Subject)

    @Delete
    suspend fun delete(subject: Subject)

    @Query("SELECT * FROM Subject ORDER BY name ASC")
    fun getAllSubject():Flow<List<Subject>>

    @Update
    suspend fun updateSubject(subject: Subject)

    @Query("SELECT * FROM Subject WHERE name=:name")
    fun getSubjectByName(name:String): LiveData<Subject>

}