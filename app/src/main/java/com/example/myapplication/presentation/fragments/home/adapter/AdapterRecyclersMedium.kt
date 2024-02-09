package com.example.myapplication.presentation.fragments.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.remote.models.movie.Result
import com.example.myapplication.data.util.Constants.IMAGE_URL
import com.example.myapplication.databinding.RcyMovieItemMediumPosterBinding
import com.squareup.picasso.Picasso

class AdapterRecyclersMedium(
):RecyclerView.Adapter<AdapterRecyclersMedium.RcyViewHolder>() {

    private val itemCallback = object : DiffUtil.ItemCallback<Result>(){
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem==newItem
        }
    }

    private val diffUtil = AsyncListDiffer(this,itemCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RcyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RcyMovieItemMediumPosterBinding.inflate(inflater,parent,false)

        return RcyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return diffUtil.currentList.size
    }

    override fun onBindViewHolder(holder: RcyViewHolder, position: Int) {
        val currentText = diffUtil.currentList[position]
        holder.bind(currentText)
    }

    fun submitList(movieList:List<Result>){
        diffUtil.submitList(movieList)
    }

    inner class RcyViewHolder(private val binding:RcyMovieItemMediumPosterBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(movieList: Result){
            Picasso.get().load("$IMAGE_URL${movieList.poster_path}").into(binding.imgMediumPoster)
        }
    }

}