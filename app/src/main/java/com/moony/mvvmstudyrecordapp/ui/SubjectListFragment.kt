package com.moony.mvvmstudyrecordapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.moony.mvvmstudyrecordapp.R
import com.moony.mvvmstudyrecordapp.Tag.FragmentTags
import com.moony.mvvmstudyrecordapp.adapters.SubjectListAdapter

import com.moony.mvvmstudyrecordapp.databinding.FragmentSubjectListBinding
import com.moony.mvvmstudyrecordapp.viewModels.SubjectListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SubjectListFragment: Fragment(),View.OnClickListener {

    private var _binding: FragmentSubjectListBinding?=null
    private val binding get()=_binding!!
    private lateinit var adapter:SubjectListAdapter
    private val subjectListViewModel: SubjectListViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSubjectListBinding.inflate(inflater, container, false)

        //RecyclerView 세팅
        binding.fragmentSubjectListListView.layoutManager=LinearLayoutManager(context)
        adapter= SubjectListAdapter{
            subjectListViewModel.setSelectedSubject(it)
            childFragmentManager.commit {
                add(
                    R.id.fragment_subject_list_container,
                    SubjectDetailFragment(),
                    FragmentTags.PAGE_SUBJECT_DETAIL.fragment_tag
                )
                addToBackStack(FragmentTags.PAGE_SUBJECT_DETAIL.fragment_tag)
            }

        }
        binding.fragmentSubjectListListView.adapter=adapter

        //Live Data 관찰.
        subjectListViewModel.allSubject.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }
        binding.fragmentSubjectListAddButton.setOnClickListener(this)
        return binding.root
    }




    override fun onDestroy() {
        super.onDestroy()
        _binding=null;
    }

    override fun onClick(view: View?) {
        when(view){

            binding.fragmentSubjectListAddButton->{

                val frag=parentFragment

                frag?.parentFragmentManager?.commit {
                    add(
                        R.id.activity_main_fragment_container,
                        SubjectAddFragment(),
                        FragmentTags.PAGE_SUBJECT_ADD.fragment_tag
                    )
                    addToBackStack(FragmentTags.PAGE_SUBJECT_ADD.fragment_tag)
                }
            }
        }
    }

}