package com.moony.mvvm_pattern_notepad.viewModels

import android.graphics.Color
import androidx.lifecycle.*
import com.moony.mvvm_pattern_notepad.RecordApplication
import com.moony.mvvm_pattern_notepad.R
import com.moony.mvvm_pattern_notepad.data.Record
import com.moony.mvvm_pattern_notepad.data.RecordRepository
import com.moony.mvvm_pattern_notepad.data.Subject
import com.moony.mvvm_pattern_notepad.data.SubjectRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SubjectListViewModel @Inject constructor(
    private val subjectRepository: SubjectRepository,
    private val recordRepository: RecordRepository
    ):ViewModel(){






    private val _allSubject=subjectRepository.getAllSubject().asLiveData()
    val allSubject:LiveData<List<Subject>>
        get()=_allSubject


    private val _selectedSubject=MutableLiveData<Subject>()
    val selectedSubject:LiveData<Subject>
        get()=_selectedSubject

    private var _selectedSubjectRecord:LiveData<List<Record>>?=null
    val selectedSubjectRecord:LiveData<List<Record>>?
        get()=_selectedSubjectRecord


    fun deleteSubject(subject: Subject){
        viewModelScope.launch(Dispatchers.IO) {
            val done=launch {
                subjectRepository.deleteSubject(subject)
            }

            //완료를 기다리기
            done.join()
        }

    }

    fun setSelectedSubject(subject: Subject){
        _selectedSubject.value=subject
        viewModelScope.launch(Dispatchers.IO){
            _selectedSubjectRecord=recordRepository.getRecordBySubjectName(_selectedSubject.value!!.name).asLiveData()
        }
    }

}