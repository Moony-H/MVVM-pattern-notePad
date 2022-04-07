package com.moony.mvvmstudyrecordapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.*
import com.moony.mvvmstudyrecordapp.R
import com.moony.mvvmstudyrecordapp.Tag.FragmentTags
import com.moony.mvvmstudyrecordapp.databinding.FragmentNavigationBinding
import com.moony.mvvmstudyrecordapp.viewModels.NavigationViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.lang.IllegalArgumentException

@AndroidEntryPoint
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

        //네비게이션이 클릭되면 클릭된 버튼의 Id를 저장해 놓는다.
        binding.fragmentNavigationNavi.setOnItemSelectedListener {
            navViewModel.setCurrentPage(it.itemId)

        }

        //저장된 id를 관찰하여, id가 바뀌면(사용자가 클릭하면) Fragment를 전환한다.
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

            //사용자가 원하는 Fragment를 tag로 받고, 그것이 존재하는지 찾는다.
            var targetFragment=childFragmentManager.findFragmentByTag(tags.fragment_tag)

            targetFragment?:run{
                //프래그먼트가 단 한 개도 생성되지 않았을 경우, ScheduleFragment를 기본으로 생성한다.
                targetFragment=ScheduleFragment()
                //Fragment add
                add(R.id.fragment_navigation_container,createFragment(tags),tags.fragment_tag)

            }

            //단순히 show, hide로 사용자가 원하는 Fragment만 보이게 만든다.
            show(targetFragment!!)

            //filterNot으로 show 한 Fragment(사용자가 요청한 Fragment)만 건너 뛰고, 나머지 Fragment들은 hide 한다.
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
            FragmentTags.PAGE_RECORD_LIST->RecordListFragment()


            else -> throw IllegalArgumentException("bottom navi view not found: fragment tag")
        }
    }

}