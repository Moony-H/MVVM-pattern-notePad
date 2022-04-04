package com.moony.mvvmstudyrecordapp.data.dummy

import com.moony.mvvmstudyrecordapp.data.DateFormat
import com.moony.mvvmstudyrecordapp.data.Record

class Dummy_Records {
    val list= mutableListOf<Record>()
    init {
        for(i in 1..20){
            list.add(Record("수학","",DateFormat.getDateFormat(2022,4,9),"14","00","16","00","공부함"))
        }
    }
}
