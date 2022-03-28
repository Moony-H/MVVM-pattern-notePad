package com.moony.mvvm_pattern_notepad.data

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class SubjectRepository @Inject constructor(private val subjectDao:SubjectDao) {

    fun getAllSubject(): Flow<List<Subject>> {
        return subjectDao.getAll()
    }
    suspend fun insertSubject(subject:Subject){
        subjectDao.insert(subject)
    }
    suspend fun deleteSubject(subject: Subject){
        subjectDao.delete(subject)
    }

    suspend fun searchSubjectByName(name:String){
        subjectDao.findSubjectByName(name)
    }


}