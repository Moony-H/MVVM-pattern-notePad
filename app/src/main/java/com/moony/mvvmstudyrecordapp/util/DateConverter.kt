package com.moony.mvvmstudyrecordapp.util

import java.text.SimpleDateFormat
import java.util.*

class DateConverter {
    companion object{
        fun convertDateToString(year:Int,month:Int,Day:Int):String{
            val y=year.toString()
            var m=(month+1).toString()
            if(m.length<2){
                m="0$m"
            }
            var d=Day.toString()
            if(d.length<2){
                d="0$d"
            }
            return "$y-$m-$d"
        }

        fun getNowDate(): String {
            val dataFormat = SimpleDateFormat("yyyy-MM-dd", Locale.KOREA)
            return dataFormat.format(Date())
        }
    }
}