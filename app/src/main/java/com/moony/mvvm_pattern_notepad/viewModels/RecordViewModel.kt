package com.moony.mvvm_pattern_notepad.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.moony.mvvm_pattern_notepad.data.Record
import com.moony.mvvm_pattern_notepad.data.RecordRepository
import com.moony.mvvm_pattern_notepad.data.Subject
import com.moony.mvvm_pattern_notepad.data.SubjectRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecordViewModel @Inject constructor(
    private val subjectRepository: SubjectRepository,
    private val recordRepository: RecordRepository
): ViewModel() {

    private val _allSubject=subjectRepository.getAllSubject().asLiveData()
    val allSubject:LiveData<List<Subject>>
        get()=_allSubject

    private var _selectedSubject=Subject("",0.0F,"","",0,0,0)
    val selectedSubject:Subject
        get()=_selectedSubject

    private val _currentRecord=MutableLiveData<Record>()
    val currentRecord:LiveData<Record>
        get()=_currentRecord


    fun setSelectedSubject(subject:Subject){
        _selectedSubject=subject
    }

    fun insertRecord(){
        CoroutineScope(Dispatchers.IO).launch {
            _currentRecord.value?.let {
                recordRepository.insertRecord(it)
            }
        }
    }
}