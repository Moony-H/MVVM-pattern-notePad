package com.moony.mvvmstudyrecordapp.data.dummy

import android.graphics.Color
import com.moony.mvvmstudyrecordapp.R
import com.moony.mvvmstudyrecordapp.RecordApplication
import com.moony.mvvmstudyrecordapp.data.Subject

class Dummy_Subject {
    val list= mutableListOf<Subject>()
    val colorList: List<String> =
        RecordApplication.getApplicationContext().resources.getStringArray(R.array.colors).toList()
    init {
        list.add(Subject(
            "알고리즘",
            5F,
            "문제 해결 능력 기르기",
            colorList[0],
            Color.parseColor(colorList[0]),
        100,
        0
        ))
        list.add(Subject(
            "OS",
            5F,
            "운영체제 구조 알기",
            colorList[1],
            Color.parseColor(colorList[1]),
            514,
            216
        ))
        list.add(Subject(
            "코틀린",
            4.5F,
            "코틀린 기본기 다지기",
            colorList[2],
            Color.parseColor(colorList[2]),
            700,
            54
        ))
        list.add(Subject(
            "코루틴",
            4.0F,
            "비동기 프로그래밍 공부하기",
            colorList[3],
            Color.parseColor(colorList[3]),
            300,
            104
        ))
        list.add(Subject(
            "JAVA",
            5F,
            "자바 기본기 다지기",
            colorList[4],
            Color.parseColor(colorList[4]),
            554,
            318
        ))
    }
}