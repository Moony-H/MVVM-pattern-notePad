package com.moony.mvvm_pattern_notepad.adapters

import androidx.recyclerview.widget.DiffUtil
import com.moony.mvvm_pattern_notepad.data.Subject

class SubjectDiffCallback : DiffUtil.ItemCallback<Subject>() {

    override fun areItemsTheSame(oldItem: Subject, newItem: Subject): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Subject, newItem: Subject): Boolean {
        return oldItem == newItem
    }
}