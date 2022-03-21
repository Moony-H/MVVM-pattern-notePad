package com.moony.mvvm_pattern_notepad.viewModels

import android.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.moony.mvvm_pattern_notepad.ApplicationContext
import com.moony.mvvm_pattern_notepad.R
import com.moony.mvvm_pattern_notepad.data.Subject



class SubjectAddFragmentViewModel:ViewModel() {
    val currentValue= MutableLiveData<Subject>()

    init {
        val color=ApplicationContext.getApplicationContext().resources.getStringArray(R.array.colors)[0]
        val temp=Subject("",0.0F,"",color,Color.parseColor(color))
        currentValue.value=temp

    }


}