package com.example.myapplication.presentation.fragments.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.remote.models.movie.Result
import com.example.myapplication.util.Constants.IMAGE_URL
import com.example.myapplication.databinding.RcyMovieItemGamesBinding
import com.example.myapplication.presentation.adapter_listener.MovieClickListener
import com.squareup.picasso.Picasso

class AdapterSearchGames(
    private val movieClickListener: MovieClickListener
):RecyclerView.Adapter<AdapterSearchGames.AdapterViewHolder>() {

    private val itemCallBack = object : DiffUtil.ItemCallback<Result>(){
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }

    }

    private val diffUtil = AsyncListDiffer(this,itemCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RcyMovieItemGamesBinding.inflate(inflater,parent,false)

        return AdapterViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return diffUtil.currentList.size
    }

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        val currentItem = diffUtil.currentList[position]
        holder.bind(currentItem)
    }



    inner class AdapterViewHolder ( private val binding: RcyMovieItemGamesBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(movie:Result){
            Picasso.get().load("$IMAGE_URL${movie.poster_path}").into(binding.imgSmallPoster)
            binding.txtName.text = movie.title
            binding.txtCategory.text = movie.vote_average.toString()

            binding.imgSmallPoster.setOnClickListener {
                movieClickListener.movieClickListener(movie.id.toLong())
            }
        }
    }
    fun submitList(movieList:List<Result>){
        diffUtil.submitList(movieList)
    }
}