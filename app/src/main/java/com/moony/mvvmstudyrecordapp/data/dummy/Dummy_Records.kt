package com.moony.mvvmstudyrecordapp.data.dummy


import com.moony.mvvmstudyrecordapp.data.Record
import com.moony.mvvmstudyrecordapp.util.DateConverter

class Dummy_Records {
    val list= mutableListOf<Record>()
    val subjects=Dummy_Subject().list
    init {
        subjects.forEachIndexed { index, subject ->
            var time=0
            for(i in 0+index..4+index){
                time++
                list.add(
                    Record(
                        subject.name,
                        subject.color,
                        "2022-04-0${i%5+1}",
                        "1${time}",
                        "00",
                        "1${time+1}",
                        "00",
                        "${subject.name} 공부함"
                    )
                )
            }

        }





    }
}
