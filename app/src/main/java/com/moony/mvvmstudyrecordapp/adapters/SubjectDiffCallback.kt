package com.moony.mvvmstudyrecordapp.adapters

import androidx.recyclerview.widget.DiffUtil
import com.moony.mvvmstudyrecordapp.data.Subject

class SubjectDiffCallback : DiffUtil.ItemCallback<Subject>() {

    override fun areItemsTheSame(oldItem: Subject, newItem: Subject): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Subject, newItem: Subject): Boolean {
        return oldItem == newItem
    }
}