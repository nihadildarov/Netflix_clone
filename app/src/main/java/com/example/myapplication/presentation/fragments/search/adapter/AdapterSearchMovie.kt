package com.example.myapplication.presentation.fragments.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.remote.models.movie.Result
import com.example.myapplication.util.Constants.IMAGE_URL
import com.example.myapplication.databinding.RcyRecommendedMoviesBinding
import com.example.myapplication.domain.remote.models.MovieResult
import com.example.myapplication.presentation.adapter_listener.MovieClickListener
import com.squareup.picasso.Picasso

class AdapterSearchMovie(
    private val movieClickListener: MovieClickListener
) : RecyclerView.Adapter<AdapterSearchMovie.AdapterViewHolder>(){

    private val itemCallBack = object : DiffUtil.ItemCallback<MovieResult>(){
        override fun areItemsTheSame(oldItem: MovieResult, newItem: MovieResult): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieResult, newItem: MovieResult): Boolean {
            return oldItem == newItem
        }

    }

    private val diffUtil = AsyncListDiffer(this,itemCallBack)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RcyRecommendedMoviesBinding.inflate(inflater,parent,false)

        return AdapterViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return diffUtil.currentList.size
    }

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        val currentItem = diffUtil.currentList[position]
        holder.bind(currentItem)
    }


    inner class AdapterViewHolder(private val binding : RcyRecommendedMoviesBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(movie:MovieResult){
            binding.txtMovieName.text = movie.title
            Picasso.get().load("$IMAGE_URL${movie.backDropPath}").into(binding.imgPoster)
            binding.cardViewPoster.setOnClickListener {
                movieClickListener.movieClickListener(movie.id.toLong())
            }

        }
    }


    fun submitList(movieList:List<MovieResult>){
        diffUtil.submitList(movieList)
    }


}