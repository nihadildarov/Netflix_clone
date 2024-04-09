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
import com.example.myapplication.databinding.RcyItemContinueWatchingBinding
import com.example.myapplication.domain.remote.models.MovieResponse
import com.example.myapplication.domain.remote.models.MovieResult
import com.example.myapplication.presentation.adapter_listener.MovieClickListener
import com.squareup.picasso.Picasso

class MovieAdapterRecyclersContinueWatching(
    private val movieClickListener: MovieClickListener
) : RecyclerView.Adapter<MovieAdapterRecyclersContinueWatching.RcyViewHolder>() {


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
        val binding = RcyItemContinueWatchingBinding.inflate(inflater, parent, false)

        return RcyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return diffUtil.currentList.size
    }

    override fun onBindViewHolder(holder: RcyViewHolder, position: Int) {
        val currentText = diffUtil.currentList[position]
        holder.bind(currentText)
    }

    fun submitList(movieList: List<MovieResult>) {
        diffUtil.submitList(movieList)
    }

    inner class RcyViewHolder(private val binding: RcyItemContinueWatchingBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: MovieResult) {

            with(binding) {

                if (isLoaded) {
                    imgWatched.visibility = VISIBLE
                    imgBtnDots.visibility = VISIBLE
                    imgBtnInfo.visibility = VISIBLE
                    progressBar.visibility = GONE
                }else{
                    imgWatched.visibility = GONE
                    imgBtnDots.visibility = GONE
                    imgBtnInfo.visibility = GONE
                    progressBar.visibility = VISIBLE
                }
            }

            Picasso.get().load(IMAGE_URL + movie.posterPath).into(binding.imgWatched)
//            binding..setOnClickListener {
//                movieClickListener.movieClickListener(movie.id.toLong())
//            }
        }
    }

    fun hideProgress() {
        isLoaded = true
        notifyDataSetChanged()
    }

    fun showProgress() {
        isLoaded = false
        notifyDataSetChanged()
    }

}