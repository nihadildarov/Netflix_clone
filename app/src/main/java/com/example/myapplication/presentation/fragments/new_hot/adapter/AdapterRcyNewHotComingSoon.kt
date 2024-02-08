package com.example.myapplication.presentation.fragments.new_hot.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.RcyNewItemComingSoonBinding

class AdapterRcyNewHotComingSoon(private val imageList : List<Int>):RecyclerView.Adapter<AdapterRcyNewHotComingSoon.ViewHolderRcyNewHotComingSoon>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolderRcyNewHotComingSoon {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RcyNewItemComingSoonBinding.inflate(inflater,parent,false)

        return ViewHolderRcyNewHotComingSoon(binding)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun onBindViewHolder(holder: ViewHolderRcyNewHotComingSoon, position: Int) {
        val currentItem = imageList[position]
        holder.bind(currentItem)
    }

    inner class ViewHolderRcyNewHotComingSoon (private val binding: RcyNewItemComingSoonBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(image:Int){
            binding.imgVideo.setImageResource(image)

        }
    }
}