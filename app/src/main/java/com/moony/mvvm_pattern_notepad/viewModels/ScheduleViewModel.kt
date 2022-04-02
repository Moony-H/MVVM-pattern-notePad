package com.moony.mvvm_pattern_notepad.viewModels

import android.util.Log
import androidx.lifecycle.*
import com.moony.mvvm_pattern_notepad.data.Record
import com.moony.mvvm_pattern_notepad.data.RecordRepository
import com.moony.mvvm_pattern_notepad.data.Subject
import com.moony.mvvm_pattern_notepad.util.DateConverter
import com.moony.mvvm_pattern_notepad.util.TimeConverter
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
    private var today=DateConverter.getNowDate()
    private var _currentRecord=MutableLiveData<List<Record>>()
    val currentRecord: LiveData<List<Record>>
        get()=_currentRecord
    init {
        setCurrentRecordByDate(today)
    }

    fun setCurrentRecordByDate(date:String){
        viewModelScope.launch(Dispatchers.IO) {
            _currentRecord.postValue(recordRepository.getRecordByDate(date))
        }
    }
}