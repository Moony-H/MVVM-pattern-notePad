package com.moony.mvvm_pattern_notepad.data.dummy

import com.moony.mvvm_pattern_notepad.R
import com.moony.mvvm_pattern_notepad.RecordApplication
import com.moony.mvvm_pattern_notepad.data.Record

class Dummy_Records {
    val list= mutableListOf<Record>()
    init {
        val colors=RecordApplication.getApplicationContext().resources.getStringArray(R.array.colors).toList()
        for (i in colors.indices){
            val record=Record(i.toString(),colors[i], "2022/03/$i","00:0$i","23:0$i","$i 했음")
            list.add(record)
        }
    }
}