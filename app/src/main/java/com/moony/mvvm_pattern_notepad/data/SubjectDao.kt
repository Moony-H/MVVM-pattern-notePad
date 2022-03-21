package com.moony.mvvm_pattern_notepad.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface SubjectDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(subject:Subject)

    @Delete
    fun delete(subject: Subject)

    @Query("SELECT * FROM Subject")
    fun getAll():List<Subject>
}