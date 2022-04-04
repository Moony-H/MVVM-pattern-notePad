package com.moony.mvvmstudyrecordapp.util

import androidx.databinding.InverseMethod

object StringIntConverter {
    @InverseMethod("stringToInt")
    @JvmStatic fun intToString(
        value: Int
    ): String {

        return value.toString()
    }


    @JvmStatic fun stringToInt(
        value: String
    ): Int {
        if(value.isEmpty())
            return 0
        return value.toInt()
    }
}