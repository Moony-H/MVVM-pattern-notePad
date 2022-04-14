package com.moony.mvvmstudyrecordapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.moony.mvvmstudyrecordapp.R
import com.moony.mvvmstudyrecordapp.adapters.ColorsAdapter
import com.moony.mvvmstudyrecordapp.databinding.FragmentSubjectAddBinding
import com.moony.mvvmstudyrecordapp.viewModels.SubjectAddViewModel

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SubjectAddFragment:Fragment(),View.OnClickListener{
    //SubjectListView가 require parent Fragment이다.(같은 인스턴스를 공유한다.)
    private val subjectAddViewModel: SubjectAddViewModel by viewModels()
    private var _binding:FragmentSubjectAddBinding?=null
    private val binding:FragmentSubjectAddBinding
    get()=_binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        //data binding
        _binding=DataBindingUtil.inflate<FragmentSubjectAddBinding>(
            inflater,
            R.layout.fragment_subject_add,
            container,
            false
        ).apply {
            //데이터 바인딩에 viewModel 설정
            viewModel=subjectAddViewModel
        }

        //lifecycleOwner 등록(data binding 을 위해)
        binding.lifecycleOwner=this

        //RecyclerView 세팅
        binding.fragmentSubjectAddColorListView.layoutManager=GridLayoutManager(
            context,
            4,
            GridLayoutManager.HORIZONTAL,
            false
        )

        val adapter=ColorsAdapter(subjectAddViewModel)
        adapter.submitList(subjectAddViewModel.colorList)
        binding.fragmentSubjectAddColorListView.adapter=adapter

        //버튼 click callback 세팅
        binding.fragmentSubjectAddSaveButton.setOnClickListener(this)
        binding.fragmentSubjectAddCancelButton.setOnClickListener(this)

        return binding.root

    }
    override fun onDestroyView(){
        super.onDestroyView()
        _binding=null
    }

    override fun onClick(view: View?) {

        when(view){
            //저장 버튼이 눌릴 시
            binding.fragmentSubjectAddSaveButton->{
                subjectAddViewModel.insertSubject()


                parentFragmentManager.popBackStack()
            }

            //취소 버튼이 눌릴 시
            binding.fragmentSubjectAddCancelButton->{
                parentFragmentManager.popBackStack()
            }

        }

    }
}