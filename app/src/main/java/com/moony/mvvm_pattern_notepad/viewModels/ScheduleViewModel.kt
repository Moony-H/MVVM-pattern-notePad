package com.moony.mvvm_pattern_notepad.viewModels

import androidx.lifecycle.*
import com.moony.mvvm_pattern_notepad.data.Record
import com.moony.mvvm_pattern_notepad.data.RecordRepository
import com.moony.mvvm_pattern_notepad.data.Subject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val recordRepository: RecordRepository
    ):ViewModel() {
    private var _currentRecord:LiveData<List<Record>>
    val currentRecord: LiveData<List<Record>>
        get()=_currentRecord

    private val tz= TimeZone.getTimeZone("Asia/Seoul")
    private val dataFormat= SimpleDateFormat("yyyy-mm-dd",)
    private val today:String
    init {
        dataFormat.timeZone=tz
        today=dataFormat.format(Date())
        _currentRecord=recordRepository.getRecordByDate(today).asLiveData()


    }

    fun setCurrentRecordByDate(date:String){
        _currentRecord=recordRepository.getRecordByDate(date).asLiveData()

    }
}