package com.moony.mvvmstudyrecordapp.data


import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecordRepository @Inject constructor(private val recordDao:RecordDao) {
    fun getRecordByDate(date:String):Flow<List<Record>>{
        return recordDao.getRecordsByDate(date)
    }

    fun getRecordBySubjectName(name:String): Flow<List<Record>> {
        return recordDao.getRecordsBySubjectName(name)
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
    suspend fun getAllRecord():List<Record>{
        return recordDao.getAllRecord()
    }

    suspend fun deleteAllRecord(){
        recordDao.deleteAllRecord()
    }

}