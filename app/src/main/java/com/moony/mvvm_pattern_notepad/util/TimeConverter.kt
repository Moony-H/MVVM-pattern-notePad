package com.moony.mvvm_pattern_notepad.util

class TimeConverter {
    companion object{
        fun convertFullTimeFormat(s_h:String, s_m:String, e_h:String, e_m:String):String {
            return "$s_h:$s_m ~ $e_h:$e_m"

        }

        fun convertOneTime(h:String,m:String):String{
            return "$h:$m"
        }
    }
}