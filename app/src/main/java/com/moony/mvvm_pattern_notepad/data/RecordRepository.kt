package com.moony.mvvm_pattern_notepad.data


import javax.inject.Inject

class RecordRepository @Inject constructor(private val recordDao:RecordDao) {
    fun getRecordByDate(date:String):List<Record>{
        return recordDao.findRecordsByDate(date)
    }

    fun getRecordBySubjectName(name:String):List<Record>{
        return recordDao.findRecordsBySubject(name)
    }

    fun insertRecord(record: Record){
        recordDao.insert(record)
    }

    fun deleteOneRecordRecord(record:Record){
        recordDao.deleteOneRecord(record)
    }
    fun deleteAllSubjectRecordBySubjectName(name: String){
        recordDao.deleteAllSubjectRecordBySubjectName(name)
    }

}