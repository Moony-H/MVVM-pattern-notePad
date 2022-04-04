package com.moony.mvvmstudyrecordapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.moony.mvvmstudyrecordapp.data.Record
import com.moony.mvvmstudyrecordapp.databinding.SourceItemRecordBinding
import com.moony.mvvmstudyrecordapp.util.TimeConverter

class SubjectDetailRecordAdapter(val list:List<Record>):RecyclerView.Adapter<SubjectDetailRecordAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(SourceItemRecordBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
    inner class ViewHolder(val binding:SourceItemRecordBinding) :RecyclerView.ViewHolder(binding.root){
        fun bind(record:Record){
            val time=TimeConverter.convertFullTimeFormat(
                record.start_time_h,
                record.start_time_m,
                record.end_time_h,
                record.end_time_m
            )
            val recordDate=record.date+" "+time
            binding.sourceItemRecordDate.text= recordDate
            binding.sourceItemRecordNote.text=record.memo
        }

    }
}