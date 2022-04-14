package com.moony.mvvmstudyrecordapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.moony.mvvmstudyrecordapp.data.RecordRepository
import com.moony.mvvmstudyrecordapp.data.SubjectRepository
import com.moony.mvvmstudyrecordapp.data.dummy.Dummy_Records
import com.moony.mvvmstudyrecordapp.data.dummy.Dummy_Subject
import com.moony.mvvmstudyrecordapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class TestActivity:AppCompatActivity() {
    @Inject
    lateinit var recordRepository: RecordRepository
    @Inject
    lateinit var subjectRepository: SubjectRepository

    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        CoroutineScope(Dispatchers.IO).launch {
            //Dummy_Subject().list.forEach {
            //    subjectRepository.insertSubject(it)
            //}

            //Dummy_Records().list.forEach {
            //    recordRepository.insertRecord(it)
            //}
            recordRepository.deleteAllRecord()

        }


    }

}