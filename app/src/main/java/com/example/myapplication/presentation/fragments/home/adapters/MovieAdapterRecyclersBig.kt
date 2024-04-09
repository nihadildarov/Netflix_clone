package com.example.myapplication.presentation.fragments.home.adapters

import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.remote.models.movie.Result
import com.example.myapplication.util.Constants.IMAGE_URL
import com.example.myapplication.databinding.RcyMovieItemBigPosterBinding
import com.example.myapplication.domain.remote.models.MovieResult
import com.example.myapplication.presentation.adapter_listener.MovieClickListener
import com.squareup.picasso.Picasso

class   MovieAdapterRecyclersBig(
    private val movieClickListener: MovieClickListener
) : RecyclerView.Adapter<MovieAdapterRecyclersBig.RcyViewHolder>() {


    private var isLoaded = false
    private val itemCallBack = object : DiffUtil.ItemCallback<MovieResult>() {
        override fun areItemsTheSame(oldItem: MovieResult, newItem: MovieResult): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: MovieResult, newItem: MovieResult): Boolean {
            return oldItem == newItem
        }
    }

    private val diffUtil = AsyncListDiffer(this, itemCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RcyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RcyMovieItemBigPosterBinding.inflate(inflater, parent, false)

        return RcyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return diffUtil.currentList.size
    }

    override fun onBindViewHolder(holder: RcyViewHolder, position: Int) {
        val currentItem = diffUtil.currentList[position]
        holder.bind(currentItem)
    }

    fun submitList(movieList: List<MovieResult>) {
        diffUtil.submitList(movieList)
    }

    inner class RcyViewHolder(private val binding: RcyMovieItemBigPosterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: MovieResult) {

            if (isLoaded){
                binding.imgBigPoster.visibility = VISIBLE
                binding.progressBar.visibility = GONE
            }else{
                binding.imgBigPoster.visibility = GONE
                binding.progressBar.visibility = VISIBLE
            }

            Picasso.get().load("$IMAGE_URL${movie.posterPath}").into(binding.imgBigPoster)
            binding.imgBigPoster.setOnClickListener {
                movieClickListener.movieClickListener(movie.id.toLong())
            }
        }
    }


    fun hideProgress(){
        isLoaded = true
        notifyDataSetChanged()
    }

    fun showProgress (){
        isLoaded = false
        notifyDataSetChanged()
    }

}

