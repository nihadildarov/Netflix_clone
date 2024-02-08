package com.example.myapplication.presentation.fragments.new_hot.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.RcyNewItemComingSoonBinding
import com.example.myapplication.databinding.RcyNewItemEveryonesWatchingBinding
import com.example.myapplication.databinding.RcyNewItemGamesBinding

class AdapterRecycler:RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class ComingSoonViewHolder(private val binding:RcyNewItemComingSoonBinding):RecyclerView.ViewHolder(binding.root)
    inner class EveryOnesWatchingViewHolder(private val binding:RcyNewItemEveryonesWatchingBinding):RecyclerView.ViewHolder(binding.root)
    inner class GamesViewHolder(private val binding:RcyNewItemGamesBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }


}