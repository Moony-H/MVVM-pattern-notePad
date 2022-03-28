package com.moony.mvvm_pattern_notepad.adapters

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.moony.mvvm_pattern_notepad.R
import com.moony.mvvm_pattern_notepad.data.Subject
import com.moony.mvvm_pattern_notepad.databinding.SourceItemColorBinding
import com.moony.mvvm_pattern_notepad.databinding.SourceItemSubjectBinding
import com.moony.mvvm_pattern_notepad.viewModels.SubjectViewModel

class SubjectListAdapter(val viewModel: SubjectViewModel):
    ListAdapter<Subject, SubjectListAdapter.ViewHolder>(SubjectDiffCallback())
{


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate<SourceItemSubjectBinding>(
                LayoutInflater.from(parent.context),
                R.layout.source_item_subject,
                parent,
                false
            ).apply {
                viewModel=viewModel
            }
        )


    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }



    inner class ViewHolder(val binding: SourceItemSubjectBinding) :RecyclerView.ViewHolder(binding.root){

        fun bind(subject: Subject){

            binding.apply {
                sourceItemSubjectName.text=subject.name
                sourceItemSubjectRating.rating=subject.importance
                sourceItemSubjectCardView.setCardBackgroundColor(subject.color_int)
                setClickListener {

                }
            }

        }


    }



}
private class SubjectDiffCallback : DiffUtil.ItemCallback<Subject>() {

    override fun areItemsTheSame(oldItem: Subject, newItem: Subject): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Subject, newItem: Subject): Boolean {
        return oldItem == newItem
    }
}