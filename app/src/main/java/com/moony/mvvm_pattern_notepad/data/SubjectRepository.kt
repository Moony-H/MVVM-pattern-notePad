package com.moony.mvvm_pattern_notepad.data

import android.app.Application
import androidx.lifecycle.LiveData
import dagger.hilt.android.qualifiers.ApplicationContext

class SubjectRepository(private val application:Application) {
    private val subjectDao:SubjectDao
    init {
        val db=SubjectDataBase.getInstance(application)!!
        subjectDao=db.subjectDao()
    }
    fun getAll():LiveData<List<Subject>>{
        return subjectDao.getAll()
    }
    fun insert(subject:Subject){
        subjectDao.insert(subject)
    }
    fun delete(subject: Subject){
        subjectDao.delete(subject)
    }


}