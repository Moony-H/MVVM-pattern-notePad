package com.moony.mvvm_pattern_notepad.data

import android.graphics.Color

data class Subject(
        val name:String,
        val importance:Int,
        val memo:String,
        val color: Color
)
