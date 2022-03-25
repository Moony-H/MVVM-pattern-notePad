package com.moony.mvvm_pattern_notepad.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.moony.mvvm_pattern_notepad.R
import com.moony.mvvm_pattern_notepad.databinding.FragmentNavigationBinding

class NavigationFragment:Fragment() {
    private var _binding:FragmentNavigationBinding?=null
    private val binding get()=_binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        parentFragmentManager.commit {
            replace(R.id.fragment_navigation_container,SubjectAddFragment())
            addToBackStack("SubjectAddFragment")
        }
        _binding= FragmentNavigationBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onDestroy() {

        super.onDestroy()
        _binding=null
    }

}