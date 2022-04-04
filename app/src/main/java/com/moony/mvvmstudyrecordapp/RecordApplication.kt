package com.moony.mvvmstudyrecordapp

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class RecordApplication : Application() {

    init{
        instance = this
    }

    companion object {
        lateinit var instance: RecordApplication
        fun getApplicationContext() : Context {
            return instance.applicationContext
        }
    }

}