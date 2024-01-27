package com.example.myapplication.fragments.new_hot.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.RcyNewItemEveryonesWatchingBinding

class AdapterRcyNewHotEveryonesWatching (private val images:List<Int>):
    RecyclerView.Adapter<AdapterRcyNewHotEveryonesWatching.ViewHolderRcyNewHotEveryonesWatching>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolderRcyNewHotEveryonesWatching {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RcyNewItemEveryonesWatchingBinding.inflate(inflater,parent,false)

        return ViewHolderRcyNewHotEveryonesWatching(binding)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    override fun onBindViewHolder(holder: ViewHolderRcyNewHotEveryonesWatching, position: Int) {
        val currenItem = images[position]
        holder.bind(currenItem)
    }

    inner class ViewHolderRcyNewHotEveryonesWatching(private val binding: RcyNewItemEveryonesWatchingBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind(image:Int){
                binding.videoPlayer.setImageResource(image)
            }

    }

}