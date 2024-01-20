package com.example.myapplication.fragments.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.RcyMovieItemGamesBinding

class AdapterRecyclersGames(
    private val imgList : List<Int>
):RecyclerView.Adapter<AdapterRecyclersGames.RcyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RcyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RcyMovieItemGamesBinding.inflate(inflater,parent,false)

        return RcyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return imgList.size
    }

    override fun onBindViewHolder(holder: RcyViewHolder, position: Int) {
        val currentText = imgList[position]
        holder.bind(currentText)
    }

    inner class RcyViewHolder(private val binding:RcyMovieItemGamesBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(img:Int){
            binding.imgSmallPoster.setImageResource(img)
        }
    }

}