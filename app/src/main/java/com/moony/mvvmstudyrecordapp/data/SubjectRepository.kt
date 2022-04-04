package com.moony.mvvmstudyrecordapp.data

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

    suspend fun updateSubject(subject: Subject){
        subjectDao.updateSubject(subject)
    }


}