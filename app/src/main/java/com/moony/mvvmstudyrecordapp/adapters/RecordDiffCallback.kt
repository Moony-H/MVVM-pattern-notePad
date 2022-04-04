package com.moony.mvvmstudyrecordapp.adapters

import androidx.recyclerview.widget.DiffUtil
import com.moony.mvvmstudyrecordapp.data.Record

class RecordDiffCallback : DiffUtil.ItemCallback<Record>() {
    override fun areItemsTheSame(oldItem: Record, newItem: Record): Boolean {
        return oldItem.id==newItem.id
    }

    override fun areContentsTheSame(oldItem: Record, newItem: Record): Boolean {
        return false
    }


}