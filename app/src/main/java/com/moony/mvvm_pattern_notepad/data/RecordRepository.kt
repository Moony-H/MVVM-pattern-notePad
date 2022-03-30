package com.moony.mvvm_pattern_notepad.data


import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RecordRepository @Inject constructor(private val recordDao:RecordDao) {
    fun getRecordByDate(date:String):Flow<List<Record>>{
        return recordDao.findRecordsByDate(date)
    }

    fun getRecordBySubjectName(name:String): Flow<List<Record>> {
        return recordDao.findRecordsBySubjectName(name)
    }

    suspend fun insertRecord(record: Record){
        recordDao.insert(record)
    }

    suspend fun deleteOneRecordRecord(record:Record){
        recordDao.deleteOneRecord(record)
    }
    suspend fun deleteAllSubjectRecordBySubjectName(name: String){
        recordDao.deleteAllSubjectRecordBySubjectName(name)
    }

}