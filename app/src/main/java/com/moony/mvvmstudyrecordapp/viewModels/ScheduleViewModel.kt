package com.moony.mvvmstudyrecordapp.viewModels

import androidx.lifecycle.*
import com.moony.mvvmstudyrecordapp.data.Record
import com.moony.mvvmstudyrecordapp.data.RecordRepository
import com.moony.mvvmstudyrecordapp.util.DateConverter
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val recordRepository: RecordRepository
    ):ViewModel() {
    private var today=DateConverter.getNowDate()



    private var _currentDate=MutableLiveData(today)
    val currentDate:LiveData<String>
        get() = _currentDate


    private var _currentRecord=_currentDate.switchMap { query->
        recordRepository.getRecordByDate(query).asLiveData()
    }
    val currentRecord: LiveData<List<Record>>
        get()=_currentRecord

    init {

    }



    fun setCurrentDate(date: String){
        _currentDate.value=date
    }
}