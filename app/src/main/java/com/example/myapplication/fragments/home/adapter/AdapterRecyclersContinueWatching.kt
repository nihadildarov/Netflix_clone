package com.example.myapplication.fragments.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.RcyItemContinueWatchingBinding

class AdapterRecyclersContinueWatching(
    private val imgList : List<Int>
):RecyclerView.Adapter<AdapterRecyclersContinueWatching.RcyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RcyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RcyItemContinueWatchingBinding.inflate(inflater,parent,false)

        return RcyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return imgList.size
    }

    override fun onBindViewHolder(holder: RcyViewHolder, position: Int) {
        val currentText = imgList[position]
        holder.bind(currentText)
    }

    inner class RcyViewHolder(private val binding:RcyItemContinueWatchingBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(img:Int){
            binding.imgWatched.setImageResource(img)
        }
    }

}