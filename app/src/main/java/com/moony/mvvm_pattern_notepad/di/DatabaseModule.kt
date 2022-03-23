package com.moony.mvvm_pattern_notepad.di

import android.content.Context
import com.moony.mvvm_pattern_notepad.data.AppDataBase
import com.moony.mvvm_pattern_notepad.data.RecordDao
import com.moony.mvvm_pattern_notepad.data.SubjectDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context):AppDataBase{
        return AppDataBase.getInstance(context)
    }

    @Provides
    fun provideRecordDao(appDataBase:AppDataBase):RecordDao{
        return appDataBase.RecordDao()
    }

    @Provides
    fun provideSubjectDao(appDataBase: AppDataBase): SubjectDao {
        return appDataBase.subjectDao()
    }



}