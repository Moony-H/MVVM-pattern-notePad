package com.moony.mvvm_pattern_notepad.data


import javax.inject.Inject

class RecordRepository @Inject constructor(private val recordDao:RecordDao) {
    suspend fun getRecordByDate(date:String):List<Record>{
        return recordDao.findRecordsByDate(date)
    }

    fun getRecordBySubjectName(name:String):List<Record>{
        return recordDao.findRecordsBySubject(name)
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