package com.moony.mvvm_pattern_notepad.viewModels

import android.graphics.Color
import androidx.lifecycle.*
import com.moony.mvvm_pattern_notepad.RecordApplication
import com.moony.mvvm_pattern_notepad.R
import com.moony.mvvm_pattern_notepad.data.Subject
import com.moony.mvvm_pattern_notepad.data.SubjectRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SubjectViewModel @Inject constructor(
    private val subjectRepository: SubjectRepository
    ):ViewModel(){
    val currentSubject= MutableLiveData<Subject>()
    val colorList: List<String> =RecordApplication.getApplicationContext().resources.getStringArray(R.array.colors).toList()
    val allSubject=subjectRepository.getAllSubject().asLiveData()

    init {
        val color=colorList[0]
        val temp=Subject("",0.0F,"",color,Color.parseColor(color))
        currentSubject.value=temp
    }



    fun insertSubject(){
        viewModelScope.launch(Dispatchers.IO) {
            val done=launch {
                currentSubject.value?.let {
                    subjectRepository.insertSubject(it)
                }
            }
            done.join()
            initCurrentSubject()

        }

    }


    fun deleteSubject(subject: Subject){
        viewModelScope.launch(Dispatchers.IO) {
            subjectRepository.deleteSubject(subject)
        }

    }


    fun setColor(string: String){
        currentSubject.value?.let {
            val subject=Subject(it.name,it.importance,it.memo,string,Color.parseColor(string))
            currentSubject.value=subject
        }
    }

    fun initCurrentSubject(){
        //백그라운드 Thread 에서는 setValue 불가(Main Thread 에서 해야 하기 때문.) 따라서 PostValue 사용
        currentSubject.postValue(Subject("",2.5f,"",colorList[0],Color.parseColor(colorList[0])))
    }








}