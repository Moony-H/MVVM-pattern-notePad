package com.moony.mvvm_pattern_notepad.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.moony.mvvm_pattern_notepad.R
import com.moony.mvvm_pattern_notepad.data.Record

import com.moony.mvvm_pattern_notepad.data.Subject
import com.moony.mvvm_pattern_notepad.databinding.SourceItemRecordBinding
import com.moony.mvvm_pattern_notepad.databinding.SourceItemScheduleBinding
import com.moony.mvvm_pattern_notepad.databinding.SourceItemSubjectMiniBinding


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

