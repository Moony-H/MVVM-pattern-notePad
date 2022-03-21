package com.moony.mvvm_pattern_notepad.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SubjectDao {

    @Insert
    fun insert(subject:Subject)

    @Delete
    fun delete(subject: Subject)

    @Query("SELECT * FROM Record")
    fun findSubjects():List<Subject>
}