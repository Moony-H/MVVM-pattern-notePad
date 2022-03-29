package com.moony.mvvm_pattern_notepad.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.moony.mvvm_pattern_notepad.data.Subject
import com.moony.mvvm_pattern_notepad.databinding.FragmentSubjectDetailBinding
import com.moony.mvvm_pattern_notepad.viewModels.SubjectListViewModel

class SubjectDetailFragment: Fragment(),View.OnClickListener {

    private val viewModel: SubjectListViewModel by viewModels( ownerProducer = {requireParentFragment()} )

    private var _binding:FragmentSubjectDetailBinding?=null
    private val binding:FragmentSubjectDetailBinding
    get()=_binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding= FragmentSubjectDetailBinding.inflate(inflater,container,false)

        viewModel.selectedSubject.value?.apply {
            binding.fragmentSubjectDetailMemoText.text= memo
            binding.fragmentSubjectDetailName.text=name
            binding.fragmentSubjectDetailRating.rating=importance
            binding.fragmentSubjectDetailCardView.setCardBackgroundColor(color_int)

        }
        binding.fragmentSubjectDetailCloseButton.setOnClickListener(this)
        binding.fragmentSubjectDetailDeleteButton.setOnClickListener(this)

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

    override fun onClick(view: View?) {
        when(view){
            binding.fragmentSubjectDetailCloseButton->{
                parentFragmentManager.popBackStack()
            }
            binding.fragmentSubjectDetailDeleteButton->{
                viewModel.selectedSubject.value?.let { viewModel.deleteSubject(it) }
                parentFragmentManager.popBackStack()
            }
        }
    }


}