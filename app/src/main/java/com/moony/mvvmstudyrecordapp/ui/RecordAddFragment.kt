package com.moony.mvvmstudyrecordapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.moony.mvvmstudyrecordapp.R
import com.moony.mvvmstudyrecordapp.databinding.FragmentRecordAddBinding
import com.moony.mvvmstudyrecordapp.viewModels.RecordViewModel


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
        
        binding.lifecycleOwner=this

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
                parentFragmentManager.popBackStack()
            }
        }
    }

}