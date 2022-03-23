package com.moony.mvvm_pattern_notepad.ui

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.moony.mvvm_pattern_notepad.RecordApplication
import com.moony.mvvm_pattern_notepad.R
import com.moony.mvvm_pattern_notepad.adapters.ColorsAdapter
import com.moony.mvvm_pattern_notepad.data.Subject
import com.moony.mvvm_pattern_notepad.databinding.FragmentSubjectAddBinding

import com.moony.mvvm_pattern_notepad.viewModels.SubjectViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SubjectAddFragment:Fragment() {
    private val subjectViewModel:SubjectViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding=DataBindingUtil.inflate<FragmentSubjectAddBinding>(
            inflater,
            R.layout.fragment_subject_add,
            container,
            false
        ).apply {
            viewModel=subjectViewModel
        }
        binding.lifecycleOwner=this
        binding.fragmentSubjectAddColorListView.layoutManager=GridLayoutManager(context,2,GridLayoutManager.HORIZONTAL,false)
        val colorListConvert= mutableListOf<String>()
        val colorList=
            RecordApplication.getApplicationContext().resources.getStringArray(R.array.colors)
        for(i in colorList){
            colorListConvert.add(i)
        }
        binding.fragmentSubjectAddColorListView.adapter=ColorsAdapter(colorListConvert){
            subjectViewModel.currentValue.value?.apply {
                val temp= Subject(name,importance,memo,it,Color.parseColor(it))
                subjectViewModel.currentValue.value=temp
            }


        }

        binding.fragmentSubjectAddSaveButton.setOnClickListener {
            subjectViewModel.insertSubject()
        }

        binding.testButton.setOnClickListener {
            subjectViewModel.getAll()
        }

        return binding.root

    }
}