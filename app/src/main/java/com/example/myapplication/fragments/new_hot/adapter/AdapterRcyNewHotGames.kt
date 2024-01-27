package com.example.myapplication.fragments.new_hot.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.RcyNewItemGamesBinding

class AdapterRcyNewHotGames(private val images : List<Int>):RecyclerView.Adapter<AdapterRcyNewHotGames.ViewHolderRcyNewHotGames>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderRcyNewHotGames {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RcyNewItemGamesBinding.inflate(inflater,parent,false)

        return ViewHolderRcyNewHotGames(binding)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    override fun onBindViewHolder(holder: ViewHolderRcyNewHotGames, position: Int) {
        val currentItem = images[position]
        holder.bind(currentItem)
    }
    inner class ViewHolderRcyNewHotGames(private val binding:RcyNewItemGamesBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(image:Int){
            binding.videoPlayer.setImageResource(image)
        }
    }
}
