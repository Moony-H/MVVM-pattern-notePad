package com.moony.mvvm_pattern_notepad.adapters

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.moony.mvvm_pattern_notepad.data.Subject
import com.moony.mvvm_pattern_notepad.databinding.SourceItemColorBinding


class ColorsAdapter(
    private val colors:List<String>,
    private val toDo:(String)->Unit
    ):
    RecyclerView.Adapter<ColorsAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: SourceItemColorBinding) :RecyclerView.ViewHolder(binding.root){
        val imageView=binding.sourceItemColorCircle

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(SourceItemColorBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.imageView.setColorFilter(Color.parseColor(colors[position]))
        holder.binding.setClickListener {
            toDo(colors[position])

        }
    }

    override fun getItemCount(): Int {
        return colors.size
    }

}