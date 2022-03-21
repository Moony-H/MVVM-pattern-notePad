package com.moony.mvvm_pattern_notepad.viewModels

import android.graphics.Color
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.moony.mvvm_pattern_notepad.ApplicationContext
import com.moony.mvvm_pattern_notepad.R
import com.moony.mvvm_pattern_notepad.data.Subject
import com.moony.mvvm_pattern_notepad.data.SubjectDataBase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class SubjectAddFragmentViewModel:ViewModel() {
    val currentValue= MutableLiveData<Subject>()

    init {
        val color=ApplicationContext.getApplicationContext().resources.getStringArray(R.array.colors)[0]
        val temp=Subject("",0.0F,"",color,Color.parseColor(color))
        currentValue.value=temp

    }

    fun insertSubject(){
        CoroutineScope(Dispatchers.IO).launch {
            val db=SubjectDataBase.getInstance(ApplicationContext.getApplicationContext())!!
            currentValue.value?.let {
                Log.d("not","null")
                db.subjectDao().insert(it)
            }?:run{
                Log.d("is","null")
            }

        }
    }

    fun getAll(){
        CoroutineScope(Dispatchers.IO).launch{
            val db=SubjectDataBase.getInstance(ApplicationContext.getApplicationContext())!!
            val temp=db.subjectDao().getAll()
            Log.d("insert Complete","${temp.size}")
        }
    }


}