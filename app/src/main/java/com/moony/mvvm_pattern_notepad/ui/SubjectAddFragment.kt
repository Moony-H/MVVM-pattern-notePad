package com.moony.mvvm_pattern_notepad.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.moony.mvvm_pattern_notepad.R
import com.moony.mvvm_pattern_notepad.adapters.ColorsAdapter
import com.moony.mvvm_pattern_notepad.databinding.FragmentSubjectAddBinding
import com.moony.mvvm_pattern_notepad.viewModels.SubjectAddViewModel

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
            2,
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


    override fun onClick(view: View?) {

        when(view){
            //저장 버튼이 눌릴 시
            binding.fragmentSubjectAddSaveButton->{
                subjectAddViewModel.insertSubject()

                Log.d("testing","${parentFragmentManager.backStackEntryCount}")
                parentFragmentManager.popBackStack()
            }

            //취소 버튼이 눌릴 시
            binding.fragmentSubjectAddCancelButton->{
                parentFragmentManager.popBackStack()
            }

        }

    }
}