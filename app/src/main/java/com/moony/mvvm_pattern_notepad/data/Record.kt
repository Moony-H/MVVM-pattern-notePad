package com.moony.mvvm_pattern_notepad.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Record(
        val subject_name:String,
        val subject_color:String,
        val date:String,
        val start_time:String,
        val end_time:String,
        val memo:String

){
        @PrimaryKey(autoGenerate = true) var id:Int=0
}