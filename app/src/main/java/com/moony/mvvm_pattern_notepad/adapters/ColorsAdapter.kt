package com.moony.mvvm_pattern_notepad.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.moony.mvvm_pattern_notepad.R
import com.moony.mvvm_pattern_notepad.databinding.SourceItemColorBinding
import com.moony.mvvm_pattern_notepad.viewModels.SubjectViewModel


class ColorsAdapter(val viewModel:SubjectViewModel):
    ListAdapter<String,ColorsAdapter.ViewHolder>(ColorDiffCallback())
{


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate<SourceItemColorBinding>(
                LayoutInflater.from(parent.context),
                R.layout.source_item_color,
                parent,
                false
            ).apply {
                subjectViewModel=viewModel
            }
        )


    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }



    inner class ViewHolder(val binding: SourceItemColorBinding) :RecyclerView.ViewHolder(binding.root){

        fun bind(string: String){

            binding.apply {
                sourceItemColorCircle.setColorFilter(Color.parseColor(string))
                setClickListener {
                    binding.subjectViewModel?.setColor(string)
                }
            }

        }


    }



}
private class ColorDiffCallback : DiffUtil.ItemCallback<String>() {

    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }
}
