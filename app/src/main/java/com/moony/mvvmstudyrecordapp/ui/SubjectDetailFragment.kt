package com.moony.mvvmstudyrecordapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.moony.mvvmstudyrecordapp.R
import com.moony.mvvmstudyrecordapp.databinding.FragmentSubjectDetailBinding




import com.moony.mvvmstudyrecordapp.adapters.SubjectDetailRecordAdapter
import com.moony.mvvmstudyrecordapp.data.dummy.Dummy_Records

import com.moony.mvvmstudyrecordapp.viewModels.SubjectListViewModel

class SubjectDetailFragment: Fragment(),View.OnClickListener {

    private val viewModel: SubjectListViewModel by viewModels( ownerProducer = {requireParentFragment()} )

    private var _binding: FragmentSubjectDetailBinding?=null
    private val binding:FragmentSubjectDetailBinding
        get()=_binding!!
    private lateinit var adapter:SubjectDetailRecordAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding= DataBindingUtil.inflate<FragmentSubjectDetailBinding>(
            inflater,
            R.layout.fragment_subject_detail,
            container,
            false
        ).apply {
            vm=viewModel
        }


        binding.fragmentSubjectDetailCloseButton.setOnClickListener(this)
        binding.fragmentSubjectDetailDeleteButton.setOnClickListener(this)
        adapter=SubjectDetailRecordAdapter()

        binding.fragmentSubjectDetailRecordsRecyclerView.layoutManager=LinearLayoutManager(context)
        binding.fragmentSubjectDetailRecordsRecyclerView.adapter=adapter

        viewModel.selectedSubjectRecord.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }

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
                viewModel.deleteSubject()

                parentFragmentManager.popBackStack()
            }
        }
    }


}