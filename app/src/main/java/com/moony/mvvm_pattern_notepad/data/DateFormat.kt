package com.moony.mvvm_pattern_notepad.data

class DateFormat {
    companion object{
        fun getDateFormat(year:Int,month:Int,day:Int):String{
            var monthString=month.toString()
            var dayString=day.toString()
            if (monthString.length<2)
                monthString= "0$monthString"
            if (dayString.length<2)
                dayString= "0$dayString"

            return "$year-$monthString-$dayString"
        }
    }
}