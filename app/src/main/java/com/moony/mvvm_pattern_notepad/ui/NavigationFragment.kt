package com.moony.mvvm_pattern_notepad.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.*
import com.moony.mvvm_pattern_notepad.R
import com.moony.mvvm_pattern_notepad.Tag.FragmentTags
import com.moony.mvvm_pattern_notepad.databinding.FragmentNavigationBinding
import com.moony.mvvm_pattern_notepad.viewModels.NavigationViewModel
import java.lang.IllegalArgumentException

class NavigationFragment:Fragment() {
    private var _binding:FragmentNavigationBinding?=null
    private val binding get()=_binding!!
    private val navViewModel:NavigationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding= FragmentNavigationBinding.inflate(inflater,container,false)

        binding.fragmentNavigationNavi.setOnItemSelectedListener {
            navViewModel.setCurrentPage(it.itemId)
            true
        }

        navViewModel.currentPageTag.observe(viewLifecycleOwner){ it ->
            binding.fragmentNavigationNavi.selectedItemId
            changeFragment(it)

        }


        return binding.root
    }

    override fun onDestroy() {

        super.onDestroy()
        _binding=null
    }





    private fun changeFragment(tags:FragmentTags){


        childFragmentManager.commit {
            var targetFragment=childFragmentManager.findFragmentByTag(tags.fragment_tag)

            targetFragment?:run{
                //프래그먼트가 단 한번도 생성 되지 않았을 경우
                targetFragment=ScheduleFragment()
                add(R.id.fragment_navigation_container,createFragment(tags),tags.fragment_tag)

            }
            show(targetFragment!!)

            FragmentTags.values()
                .filterNot { it==tags }
                .forEach { type->
                    childFragmentManager.findFragmentByTag(type.fragment_tag)?.let{
                        hide(it)
                    }
                }

        }


    }

    private fun createFragment(tags:FragmentTags):Fragment{
        return when(tags){
            FragmentTags.PAGE_SCHEDULE -> ScheduleFragment()
            FragmentTags.PAGE_SUBJECT_LIST->SubjectListFragment()
            FragmentTags.PAGE_RECORD->RecordFragment()


            else -> throw IllegalArgumentException("bottom navi view not found: fragment tag")
        }
    }

}