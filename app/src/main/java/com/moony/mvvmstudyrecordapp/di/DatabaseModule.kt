package com.moony.mvvmstudyrecordapp.di

import android.content.Context
import com.moony.mvvmstudyrecordapp.data.AppDataBase
import com.moony.mvvmstudyrecordapp.data.RecordDao
import com.moony.mvvmstudyrecordapp.data.SubjectDao
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