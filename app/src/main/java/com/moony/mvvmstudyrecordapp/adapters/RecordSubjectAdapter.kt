package com.moony.mvvmstudyrecordapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.moony.mvvmstudyrecordapp.R

import com.moony.mvvmstudyrecordapp.data.Subject
import com.moony.mvvmstudyrecordapp.databinding.SourceItemSubjectMiniBinding


class RecordSubjectAdapter(val onClick:(Subject)->Unit):ListAdapter<Subject,RecordSubjectAdapter.ViewHolder>(SubjectDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.source_item_subject_mini,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    inner class ViewHolder(val binding:SourceItemSubjectMiniBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(subject:Subject){
            binding.sourceItemSubjectMiniCard.setCardBackgroundColor(subject.color_int)
            binding.sourceItemSubjectMiniText.text=subject.name
            binding.setClickListener {
                onClick(subject)
            }
        }
    }
}

