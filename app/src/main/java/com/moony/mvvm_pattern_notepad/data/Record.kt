package com.moony.mvvm_pattern_notepad.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Record(
        val subject_name:String,
        val date:String,
        val purpose:String,
        val time:Int
){
        @PrimaryKey(autoGenerate = true) var id:Int=0
}