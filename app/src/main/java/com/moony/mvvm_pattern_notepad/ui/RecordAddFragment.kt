package com.moony.mvvm_pattern_notepad.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.moony.mvvm_pattern_notepad.R
import com.moony.mvvm_pattern_notepad.databinding.FragmentRecordAddBinding
import com.moony.mvvm_pattern_notepad.databinding.FragmentRecordSubjectListBinding
import com.moony.mvvm_pattern_notepad.databinding.FragmentSubjectAddBinding
import com.moony.mvvm_pattern_notepad.viewModels.RecordViewModel
import dagger.hilt.android.AndroidEntryPoint


class RecordAddFragment: Fragment(),View.OnClickListener {
    private var _binding: FragmentRecordAddBinding?=null
    private val binding:FragmentRecordAddBinding
        get()=_binding!!

    private val viewModel:RecordViewModel by viewModels(ownerProducer = {requireParentFragment()})


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding=DataBindingUtil.inflate<FragmentRecordAddBinding>(
            inflater,
            R.layout.fragment_record_add,
            container,
            false
        ).apply {
            //데이터 바인딩에 viewModel 설정
            vm=viewModel
        }

        binding.fragmentRecordAddSaveButton.setOnClickListener(this)
        binding.fragmentRecordAddCancelButton.setOnClickListener(this)
        return binding.root
    }

    override fun onClick(view: View?) {
        when (view){
            binding.fragmentRecordAddSaveButton->{
                viewModel.insertRecord()
                viewModel.updateSubject()

                parentFragmentManager.popBackStack()
            }
            binding.fragmentRecordAddCancelButton->{
                viewModel.getAllRecord()
                parentFragmentManager.popBackStack()
            }
        }
    }

}