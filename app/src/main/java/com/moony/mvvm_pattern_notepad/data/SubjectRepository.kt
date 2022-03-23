package com.moony.mvvm_pattern_notepad.data

import android.app.Application
import androidx.lifecycle.LiveData
import javax.inject.Inject


class SubjectRepository @Inject constructor(private val subjectDao:SubjectDao) {

    fun getAllSubject():List<Subject>{
        return subjectDao.getAll()
    }
    fun insertSubject(subject:Subject){
        subjectDao.insert(subject)
    }
    fun deleteSubject(subject: Subject){
        subjectDao.delete(subject)
    }

    fun searchSubjectByName(name:String){
        subjectDao.searchBySubjectName(name)
    }


}