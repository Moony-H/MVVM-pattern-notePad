package com.moony.mvvm_pattern_notepad.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.moony.mvvm_pattern_notepad.adapters.ScheduleAdapter
import com.moony.mvvm_pattern_notepad.data.dummy.Dummy_Records
import com.moony.mvvm_pattern_notepad.databinding.FragmentCalendarBinding
import com.moony.mvvm_pattern_notepad.viewModels.SubjectViewModel

class ScheduleFragment:Fragment() {
    private val subjectViewModel: SubjectViewModel by activityViewModels()
    private var _binding: FragmentCalendarBinding?=null
    private val binding get()=_binding!!
    private lateinit var adapter: ScheduleAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCalendarBinding.inflate(inflater, container, false)

        //recyclerView 세팅
        binding.fragmentCalendarListView.layoutManager=LinearLayoutManager(context)
        adapter=ScheduleAdapter(subjectViewModel)
        binding.fragmentCalendarListView.adapter=adapter
        val dummy=Dummy_Records()
        adapter.submitList(dummy.list)
        return binding.root

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null;
    }

}