package com.moony.mvvm_pattern_notepad.data

import android.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Subject(
        @PrimaryKey
        var name:String,
        var importance:Float,
        var memo:String,
        var color: String,
        var color_int:Int

){

}
