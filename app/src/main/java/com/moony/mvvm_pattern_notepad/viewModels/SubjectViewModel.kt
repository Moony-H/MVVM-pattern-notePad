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
    val colorList: List<String> =RecordApplication.getApplicationContext().resources.getStringArray(R.array.colors).toList()

    init {
        val color=colorList[0]
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
            for(i in temp){
                Log.d("subject is", i.name)
                Log.d("subject color is","${i.color}")
            }
        }
    }

    fun setColor(string: String){
        currentValue.value?.let {
            val subject=Subject(it.name,it.importance,it.memo,string,Color.parseColor(string))
            currentValue.value=subject
        }



    }



}