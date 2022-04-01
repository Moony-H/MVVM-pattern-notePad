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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
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
        //보통 insert를 하고 fragment와 ViewModel을 종료하는 경우가 많다.
        //viewModelScope로 하면 viewModel이 Fragment와 함께 Destroy 되는 시점에 같이 멈춘다.
        //따라서 아주 가끔 저장이 되지 않는다.
        //그래서 viewModel과 상관 없는 Coroutine Scope를 사용한다.

        CoroutineScope(Dispatchers.IO).launch {
            _currentSubject.value?.let {
                subjectRepository.insertSubject(it)
            }

        }

    }

    fun setColor(string: String){
        _currentSubject.value?.let {
            val subject=Subject(it.name,it.importance,it.memo,string,Color.parseColor(string),it.progress_rate_max,it.progress_rate)
            _currentSubject.value=subject
        }
    }


}