package com.moony.mvvm_pattern_notepad.ui

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
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

        //data binding
        val binding=DataBindingUtil.inflate<FragmentSubjectAddBinding>(
            inflater,
            R.layout.fragment_subject_add,
            container,
            false
        ).apply {
            viewModel=subjectViewModel
        }

        //lifecycleOwner 등록
        binding.lifecycleOwner=this

        //RecyclerView 세팅
        binding.fragmentSubjectAddColorListView.layoutManager=GridLayoutManager(
            context,
            2,
            GridLayoutManager.HORIZONTAL,
            false
        )

        //RecyclerView의 item이 클릭시 할 행동 세팅
        val adapter=ColorsAdapter(subjectViewModel)
        adapter.submitList(subjectViewModel.colorList)
        binding.fragmentSubjectAddColorListView.adapter=adapter

        binding.fragmentSubjectAddSaveButton.setOnClickListener {
            subjectViewModel.insertSubject()
        }

        binding.testButton.setOnClickListener {
            subjectViewModel.getAll()
            parentFragmentManager.commit {
                replace(R.id.activity_main_fragment_container,CalendarFragment())
                addToBackStack("test")

            }
        }

        return binding.root

    }

    override fun onDetach() {
        Log.d("fragment","onDetach")
        super.onDetach()
    }
}