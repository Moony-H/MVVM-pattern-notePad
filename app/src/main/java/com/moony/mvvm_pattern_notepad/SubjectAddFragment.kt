package com.moony.mvvm_pattern_notepad

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.moony.mvvm_pattern_notepad.adapters.ColorsAdapter
import com.moony.mvvm_pattern_notepad.databinding.FragmentSubjectAddBinding

import com.moony.mvvm_pattern_notepad.viewModels.SubjectAddFragmentViewModel

class SubjectAddFragment:Fragment() {
    private val subjectAddViewModel:SubjectAddFragmentViewModel by viewModels()

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
            viewModel=subjectAddViewModel
        }
        binding.fragmentSubjectAddColorListView.layoutManager=GridLayoutManager(context,2,GridLayoutManager.HORIZONTAL,false)
        val colorListConvert= mutableListOf<String>()
        val colorList=ApplicationContext.getApplicationContext().resources.getStringArray(R.array.colors)
        for(i in colorList){
            colorListConvert.add(i)
        }
        binding.fragmentSubjectAddColorListView.adapter=ColorsAdapter(colorListConvert)
        subjectAddViewModel.currentValue.observe(viewLifecycleOwner){
            binding.fragmentSubjectAddSubLayout.setBackgroundColor(Color.parseColor(it.color))
            Log.d("color changed", it.color)

        }
        return binding.root

    }
}