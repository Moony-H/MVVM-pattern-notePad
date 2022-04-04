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

        //화면 회전 시 onCreate 가 다시 실행될 때, Fragment 의 존재 여부에 따라 생성할지 말지 결정.

        //프래그먼트 존재 여부 확인
        var navFragment=supportFragmentManager.findFragmentByTag(FragmentTags.PAGE_NAV.fragment_tag)
        navFragment?:run{
            //프래그먼트가 없으면 생성한 후 커밋.
            navFragment=NavigationFragment()
            supportFragmentManager.commit {
                replace(R.id.activity_main_fragment_container,navFragment!!,FragmentTags.PAGE_NAV.fragment_tag)
            }
        }

    }

}