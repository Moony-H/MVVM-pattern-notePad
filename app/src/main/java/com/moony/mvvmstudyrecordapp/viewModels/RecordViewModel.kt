package com.moony.mvvmstudyrecordapp.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.moony.mvvmstudyrecordapp.data.Record
import com.moony.mvvmstudyrecordapp.data.RecordRepository
import com.moony.mvvmstudyrecordapp.data.Subject
import com.moony.mvvmstudyrecordapp.data.SubjectRepository
import com.moony.mvvmstudyrecordapp.util.DateConverter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
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


    init {
        initRecord()
    }

    fun setSelectedSubject(subject:Subject){
        _selectedSubject=subject
        initRecord()
    }



    fun insertRecord(){
        CoroutineScope(Dispatchers.IO).launch {
            _currentRecord.value?.let {
                recordRepository.insertRecord(it)
            }
        }
    }



    fun updateSubject(){
        CoroutineScope(Dispatchers.IO).launch {
            val done=launch {
                subjectRepository.updateSubject(_selectedSubject)
            }
            done.join()
            initSelectedSubject()

        }

    }

    fun getAllRecord(){
        CoroutineScope(Dispatchers.IO).launch {

            val done=async {
                recordRepository.getAllRecord()
            }
            Log.d("all record","${done.await()}")
        }

    }
    private fun initSelectedSubject(){
        _selectedSubject=Subject("",0.0F,"","",0,0,0)
    }

    private fun initRecord(){
        _currentRecord.value=Record(
            selectedSubject.name,
            _selectedSubject.color,
            DateConverter.getNowDate(),
            "00","00","00","00",""
        )
    }
}