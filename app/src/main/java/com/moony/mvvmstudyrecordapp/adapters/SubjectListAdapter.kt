package com.moony.mvvmstudyrecordapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.moony.mvvmstudyrecordapp.R
import com.moony.mvvmstudyrecordapp.data.Subject
import com.moony.mvvmstudyrecordapp.databinding.SourceItemSubjectBinding

class SubjectListAdapter(val onItemClicked:(subject:Subject)->Unit):
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
                    onItemClicked(subject)

                }
            }

        }


    }



}
