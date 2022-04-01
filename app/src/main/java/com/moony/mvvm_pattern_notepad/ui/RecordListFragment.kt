package com.moony.mvvm_pattern_notepad.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.moony.mvvm_pattern_notepad.R
import com.moony.mvvm_pattern_notepad.Tag.FragmentTags
import com.moony.mvvm_pattern_notepad.adapters.RecordSubjectAdapter
import com.moony.mvvm_pattern_notepad.databinding.FragmentRecordSubjectListBinding
import com.moony.mvvm_pattern_notepad.viewModels.RecordViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecordListFragment:Fragment() {
    private val viewModel:RecordViewModel by viewModels()
    private var _binding: FragmentRecordSubjectListBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter:RecordSubjectAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentRecordSubjectListBinding.inflate(inflater, container, false)
        adapter= RecordSubjectAdapter{

            parentFragmentManager.commit {
                add(
                    R.id.fragment_record_subject_list_container,
                    RecordAddFragment(),
                    FragmentTags.PAGE_RECORD_ADD.fragment_tag
                )
                addToBackStack(FragmentTags.PAGE_RECORD_ADD.fragment_tag)
            }
        }
        binding.fragmentRecordSubjectListList.layoutManager=GridLayoutManager(
            context,
            5,GridLayoutManager.HORIZONTAL,
            false
        )
        binding.fragmentRecordSubjectListList.adapter=adapter

        viewModel.allSubject.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null;
    }

}