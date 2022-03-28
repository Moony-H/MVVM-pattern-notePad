package com.moony.mvvm_pattern_notepad.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import com.moony.mvvm_pattern_notepad.R
import com.moony.mvvm_pattern_notepad.Tag.FragmentTags
import com.moony.mvvm_pattern_notepad.adapters.SubjectListAdapter
import com.moony.mvvm_pattern_notepad.databinding.FragmentCalendarBinding

import com.moony.mvvm_pattern_notepad.databinding.FragmentSubjectListBinding
import com.moony.mvvm_pattern_notepad.viewModels.SubjectViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SubjectListFragment: Fragment(),View.OnClickListener {

    private var _binding: FragmentSubjectListBinding?=null
    private val binding get()=_binding!!
    private lateinit var adapter:SubjectListAdapter
    private val subjectViewModel: SubjectViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSubjectListBinding.inflate(inflater, container, false)

        //RecyclerView 세팅
        binding.fragmentSubjectListListView.layoutManager=LinearLayoutManager(context)
        adapter= SubjectListAdapter(subjectViewModel)
        binding.fragmentSubjectListListView.adapter=adapter

        //Live Data 관찰.
        subjectViewModel.allSubject.observe(viewLifecycleOwner){
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
                childFragmentManager.commit {
                    add(
                        R.id.fragment_subject_list_container,
                        SubjectAddFragment(),
                        FragmentTags.PAGE_SUBJECT_ADD.fragment_tag
                    )
                    addToBackStack(FragmentTags.PAGE_SUBJECT_ADD.fragment_tag)
                }
            }
        }
    }

}