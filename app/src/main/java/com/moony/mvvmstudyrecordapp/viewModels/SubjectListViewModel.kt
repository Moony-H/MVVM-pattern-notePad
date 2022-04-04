package com.moony.mvvmstudyrecordapp.viewModels

import androidx.lifecycle.*
import com.moony.mvvmstudyrecordapp.data.Record
import com.moony.mvvmstudyrecordapp.data.RecordRepository
import com.moony.mvvmstudyrecordapp.data.Subject
import com.moony.mvvmstudyrecordapp.data.SubjectRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
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


    private var _selectedSubject=Subject("",0.0F,"","",0,0,0)
    val selectedSubject:Subject
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
        _selectedSubject=subject
        viewModelScope.launch(Dispatchers.IO){
            _selectedSubjectRecord=recordRepository.getRecordBySubjectName(_selectedSubject.name).asLiveData()
        }
    }

}