package com.moony.mvvm_pattern_notepad.ui


import android.os.Bundle

import androidx.activity.viewModels

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.moony.mvvm_pattern_notepad.R
import com.moony.mvvm_pattern_notepad.databinding.ActivityMainBinding
import com.moony.mvvm_pattern_notepad.viewModels.SubjectViewModel
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val subjectViewModel:SubjectViewModel by viewModels()
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.commit {
            add(R.id.activity_main_fragment_container,NavigationFragment())
            addToBackStack("NavigationFragment")
        }


    }

}