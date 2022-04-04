package com.moony.mvvmstudyrecordapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Subject(
        @PrimaryKey
        var name:String,
        var importance:Float,
        var memo:String,
        var color: String,
        var color_int:Int,
        var progress_rate_max:Int,
        var progress_rate:Int
){

}
