package com.moony.mvvmstudyrecordapp.viewModels

import android.util.Log
import androidx.lifecycle.*
import com.moony.mvvmstudyrecordapp.data.Record
import com.moony.mvvmstudyrecordapp.data.RecordRepository
import com.moony.mvvmstudyrecordapp.data.Subject
import com.moony.mvvmstudyrecordapp.data.SubjectRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
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


    private var _selectedSubject= MutableLiveData(Subject("",0.0F,"","",0,0,0))//
    val selectedSubject:LiveData<Subject>
        get()=_selectedSubject


    private var _selectedSubjectDetail= _selectedSubject.switchMap {
        Log.d("data","switch")
        subjectRepository.getSubjectByName(it.name)
    }
    val selectedSubjectDetail:LiveData<Subject>
        get()=_selectedSubjectDetail


    private var _selectedSubjectRecord =_selectedSubject.switchMap {
        recordRepository.getRecordBySubjectName(it.name).asLiveData()
    }
    val selectedSubjectRecord:LiveData<List<Record>>
        get()=_selectedSubjectRecord


    fun deleteSubject(){
        CoroutineScope(Dispatchers.IO).launch {
            _selectedSubject.value?.let { subjectRepository.deleteSubject(it) }

        }

    }

    fun setSelectedSubject(subject: Subject){
        _selectedSubject.value=subject
    }


}