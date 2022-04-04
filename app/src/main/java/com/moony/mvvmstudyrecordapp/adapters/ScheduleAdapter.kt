package com.moony.mvvmstudyrecordapp.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.moony.mvvmstudyrecordapp.R
import com.moony.mvvmstudyrecordapp.data.Record
import com.moony.mvvmstudyrecordapp.databinding.SourceItemScheduleBinding
import com.moony.mvvmstudyrecordapp.util.TimeConverter
import com.moony.mvvmstudyrecordapp.viewModels.ScheduleViewModel

class ScheduleAdapter(private val listViewModel: ScheduleViewModel) :
    ListAdapter<Record, ScheduleAdapter.ViewHolder>(RecordDiffCallback())
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

        @SuppressLint("SetTextI18n", "Range")
        fun bind(record: Record){
            val start=TimeConverter.convertOneTime(
                record.start_time_h,
                record.start_time_m,
            )
            val end=TimeConverter.convertOneTime(
            record.end_time_h,
            record.end_time_m,
            )

            binding.apply {
                sourceItemScheduleCardView.setCardBackgroundColor(Color.parseColor(record.subject_color))
                sourceItemScheduleTimeText.text=start+"\n"+end
                sourceItemScheduleSubjectText.text=record.subject_name
                sourceItemScheduleMemoText.text=record.memo
                setClickListener {

                }
            }

        }


    }




}
