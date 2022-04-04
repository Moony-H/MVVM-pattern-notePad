package com.moony.mvvmstudyrecordapp.ui


import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.moony.mvvmstudyrecordapp.R
import com.moony.mvvmstudyrecordapp.Tag.FragmentTags
import com.moony.mvvmstudyrecordapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    //private val subjectViewModel:NavigationViewModel by viewModels()
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