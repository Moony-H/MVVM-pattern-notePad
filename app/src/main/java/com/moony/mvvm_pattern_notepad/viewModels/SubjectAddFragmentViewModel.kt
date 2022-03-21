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
        currentValue.value?.color=
            ApplicationContext.getApplicationContext().resources.getStringArray(R.array.colors)[0]
        currentValue.value?.name=""
        currentValue.value?.importance= 0.0F
        currentValue.value?.memo=""
        currentValue.value?.color_int=Color.parseColor(currentValue.value?.color)
    }


}