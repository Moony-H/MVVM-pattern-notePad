package com.moony.mvvm_pattern_notepad.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.moony.mvvm_pattern_notepad.R
import com.moony.mvvm_pattern_notepad.data.Record
import com.moony.mvvm_pattern_notepad.databinding.SourceItemScheduleBinding
import com.moony.mvvm_pattern_notepad.viewModels.ScheduleViewModel
import com.moony.mvvm_pattern_notepad.viewModels.SubjectListViewModel

class ScheduleAdapter(private val listViewModel: ScheduleViewModel) :
    ListAdapter<Record, ScheduleAdapter.ViewHolder>(ScheduleDiffCallback())
{


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate<SourceItemScheduleBinding>(
                LayoutInflater.from(parent.context),
                R.layout.source_item_schedule,
                parent,
                false
            ).apply {
                vm=listViewModel
            }
        )


    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }



    inner class ViewHolder(val binding: SourceItemScheduleBinding) : RecyclerView.ViewHolder(binding.root){

        @SuppressLint("SetTextI18n")
        fun bind(record: Record){

            binding.apply {
                sourceItemScheduleCardView.setCardBackgroundColor(Color.parseColor(record.subject_color))
                sourceItemScheduleTimeText.text=record.start_time+"\n"+record.end_time
                sourceItemScheduleSubjectText.text=record.subject_name
                sourceItemScheduleMemoText.text=record.memo
                setClickListener {

                }
            }

        }


    }



}
private class ScheduleDiffCallback : DiffUtil.ItemCallback<Record>() {
    override fun areItemsTheSame(oldItem: Record, newItem: Record): Boolean {
        return oldItem.id==newItem.id
    }

    override fun areContentsTheSame(oldItem: Record, newItem: Record): Boolean {
        return false
    }


}