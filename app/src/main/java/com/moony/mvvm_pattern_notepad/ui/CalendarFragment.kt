package com.moony.mvvm_pattern_notepad.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.moony.mvvm_pattern_notepad.databinding.FragmentCalendarBinding

class CalendarFragment:Fragment() {
    private var _binding: FragmentCalendarBinding?=null
    private val binding get()=_binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCalendarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null;
    }
    override fun onDetach() {
        Log.d("fragment","onDetach")
        super.onDetach()
    }

}