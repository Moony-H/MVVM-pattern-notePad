package com.moony.mvvm_pattern_notepad.data.dummy

import com.moony.mvvm_pattern_notepad.R
import com.moony.mvvm_pattern_notepad.RecordApplication
import com.moony.mvvm_pattern_notepad.data.DateFormat
import com.moony.mvvm_pattern_notepad.data.Record

class Dummy_Records {
    val list= mutableListOf<Record>()
    init {
        for(i in 1..20){
            list.add(Record("수학","",DateFormat.getDateFormat(2022,4,9),"14","00","16","00","공부함"))
        }
    }
}
