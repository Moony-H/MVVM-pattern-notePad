package com.moony.mvvm_pattern_notepad.viewModels

import android.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moony.mvvm_pattern_notepad.R
import com.moony.mvvm_pattern_notepad.RecordApplication
import com.moony.mvvm_pattern_notepad.data.Subject
import com.moony.mvvm_pattern_notepad.data.SubjectRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SubjectAddViewModel @Inject constructor(
    private val subjectRepository:SubjectRepository
    ):ViewModel() {

    private val _currentSubject= MutableLiveData<Subject>()
    val currentSubject: LiveData<Subject>
        get()=_currentSubject

    val colorList: List<String> =
        RecordApplication.getApplicationContext().resources.getStringArray(R.array.colors).toList()


    init {
        val color=colorList[0]
        val temp=Subject("",0.0F,"",color, Color.parseColor(color),100,0)
        _currentSubject.value=temp
    }

    fun insertSubject(){

        viewModelScope.launch(Dispatchers.IO) {
            val done=launch {
                _currentSubject.value?.let {
                    subjectRepository.insertSubject(it)
                }
            }
            //완료를 기다리기
            done.join()

        }

    }

    fun setColor(string: String){
        _currentSubject.value?.let {
            val subject=Subject(it.name,it.importance,it.memo,string,Color.parseColor(string),it.progress_rate_max,it.progress_rate)
            _currentSubject.value=subject
        }
    }


}