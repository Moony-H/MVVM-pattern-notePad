package com.moony.mvvm_pattern_notepad

import android.app.Application
import android.content.Context

class ApplicationContext : Application() {

    init{
        instance = this
    }

    companion object {
        lateinit var instance: ApplicationContext
        fun getApplicationContext() : Context {
            return instance.applicationContext
        }
    }

}