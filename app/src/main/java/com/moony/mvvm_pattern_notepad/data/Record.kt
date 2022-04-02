package com.moony.mvvm_pattern_notepad.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Record(
        var subject_name:String="",
        var subject_color:String="",
        var date:String="",
        var start_time_h:String="",
        var start_time_m:String="",
        var end_time_h:String="",
        var end_time_m:String="",
        var memo:String=""

){
        @PrimaryKey(autoGenerate = true) var id:Int=0
}