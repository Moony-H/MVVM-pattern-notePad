package com.moony.mvvmstudyrecordapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.moony.mvvmstudyrecordapp.adapters.ScheduleAdapter


import com.moony.mvvmstudyrecordapp.databinding.FragmentScheduleBinding
import com.moony.mvvmstudyrecordapp.util.DateConverter
import com.moony.mvvmstudyrecordapp.viewModels.ScheduleViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScheduleFragment:Fragment() {
    private val scheduleViewModel: ScheduleViewModel by viewModels()
    private var _binding: FragmentScheduleBinding?=null
    private val binding get()=_binding!!

    private lateinit var adapter: ScheduleAdapter



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentScheduleBinding.inflate(inflater, container, false)

        //recyclerView 세팅
        binding.fragmentCalendarListView.layoutManager=LinearLayoutManager(context)
        adapter=ScheduleAdapter(scheduleViewModel)
        binding.fragmentCalendarListView.adapter=adapter


        //val dummy=Dummy_Records()
        //adapter.submitList(dummy.list)

        scheduleViewModel.currentRecord.observe(viewLifecycleOwner){ records->


            adapter.submitList(records)
            binding.fragmentCalendarNoListText.visibility=
                if(records.isEmpty())
                    View.VISIBLE
                else
                    View.GONE
        }

        binding.fragmentCalendarCalendar.setOnDateChangeListener{ _, year, month, day->
            val date=DateConverter.convertDateToString(year,month,day)
            scheduleViewModel.setCurrentDate(date)
        }






        return binding.root

    }



    private fun getDateToString(year: Int, month: Int, day: Int): String {
        var m=month.toString()
        if (m.length<2){
            m= "0$m"
        }

        return "$year-${month + 1}-$day"
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }



}