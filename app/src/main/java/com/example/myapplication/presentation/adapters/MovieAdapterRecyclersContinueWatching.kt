package com.example.myapplication.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.remote.models.movie.Result
import com.example.myapplication.data.util.Constants.IMAGE_URL
import com.example.myapplication.databinding.RcyItemContinueWatchingBinding
import com.squareup.picasso.Picasso

class MovieAdapterRecyclersContinueWatching(

):RecyclerView.Adapter<MovieAdapterRecyclersContinueWatching.RcyViewHolder>() {


    val onClick: (Result)-> Unit={}

    private val itemCallBack = object : DiffUtil.ItemCallback<Result>(){
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }
    }

    private val diffUtil = AsyncListDiffer(this,itemCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RcyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RcyItemContinueWatchingBinding.inflate(inflater,parent,false)

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

    inner class RcyViewHolder(private val binding:RcyItemContinueWatchingBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(movie:Result){
            Picasso.get().load("$IMAGE_URL${movie.poster_path}").into(binding.imgWatched)
        }
    }

}