package com.moony.mvvm_pattern_notepad.ui


import android.os.Bundle
import android.util.Log

import androidx.activity.viewModels

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.moony.mvvm_pattern_notepad.R
import com.moony.mvvm_pattern_notepad.Tag.FragmentTags
import com.moony.mvvm_pattern_notepad.databinding.ActivityMainBinding
import com.moony.mvvm_pattern_notepad.viewModels.NavigationViewModel
import com.moony.mvvm_pattern_notepad.viewModels.SubjectViewModel
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val subjectViewModel:NavigationViewModel by viewModels()
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var navFragment=supportFragmentManager.findFragmentByTag(FragmentTags.PAGE_NAV.fragment_tag)
        navFragment?:run{
            navFragment=NavigationFragment()
            supportFragmentManager.commit {
                replace(R.id.activity_main_fragment_container,navFragment!!,FragmentTags.PAGE_NAV.fragment_tag)
            }
        }





    }

}