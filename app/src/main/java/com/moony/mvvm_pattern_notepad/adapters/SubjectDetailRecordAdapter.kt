package com.moony.mvvm_pattern_notepad.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.moony.mvvm_pattern_notepad.data.Record
import com.moony.mvvm_pattern_notepad.databinding.SourceItemRecordBinding

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
            val recordDate=record.date+" "+record.start_time+" ~ "+record.end_time
            binding.sourceItemRecordDate.text= recordDate
            binding.sourceItemRecordNote.text=record.memo
        }

    }
}