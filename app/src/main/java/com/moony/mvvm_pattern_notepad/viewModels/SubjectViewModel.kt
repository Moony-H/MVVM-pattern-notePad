package com.moony.mvvm_pattern_notepad.viewModels

import android.graphics.Color
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.moony.mvvm_pattern_notepad.RecordApplication
import com.moony.mvvm_pattern_notepad.R
import com.moony.mvvm_pattern_notepad.data.Subject
import com.moony.mvvm_pattern_notepad.data.SubjectRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SubjectViewModel @Inject constructor(
    private val subjectRepository: SubjectRepository
    ):ViewModel(){

    val currentValue= MutableLiveData<Subject>()

    init {
        val color=RecordApplication.getApplicationContext().resources.getStringArray(R.array.colors)[0]
        val temp=Subject("",0.0F,"",color,Color.parseColor(color))
        currentValue.value=temp

    }

    fun insertSubject(){
        CoroutineScope(Dispatchers.IO).launch {
            currentValue.value?.let {

                subjectRepository.insertSubject(it)
            }
        }
    }

    fun deleteSubject(subject: Subject){
        CoroutineScope(Dispatchers.IO).launch {
            subjectRepository.insertSubject(subject)
        }
    }

    fun getAll(){
        CoroutineScope(Dispatchers.IO).launch{
            val temp=subjectRepository.getAllSubject()
            Log.d("insert Complete","${temp.size}")
        }
    }


}