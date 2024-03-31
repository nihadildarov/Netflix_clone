package com.example.myapplication.presentation.fragments.new_hot.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.remote.models.movie.Genre
import com.example.myapplication.data.remote.models.movie.Result
import com.example.myapplication.util.Constants.IMAGE_URL
import com.example.myapplication.databinding.RcyNewItemEveryonesWatchingBinding
import com.example.myapplication.domain.remote.models.MovieResult
import com.example.myapplication.presentation.adapter_listener.MovieClickListener
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso

class AdapterRcyNewHotEveryoneWatching (
    private val movieClickListener: MovieClickListener
):
    RecyclerView.Adapter<AdapterRcyNewHotEveryoneWatching.ViewHolderRcyNewHotEveryoneWatching>() {

        private val itemCallback = object : DiffUtil.ItemCallback<MovieResult>(){
            override fun areItemsTheSame(oldItem: MovieResult, newItem: MovieResult): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieResult, newItem: MovieResult): Boolean {
                return oldItem == newItem
            }

        }

    private val diffUtil = AsyncListDiffer(this,itemCallback)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolderRcyNewHotEveryoneWatching {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RcyNewItemEveryonesWatchingBinding.inflate(inflater,parent,false)

        return ViewHolderRcyNewHotEveryoneWatching(binding)
    }

    override fun getItemCount(): Int {
        return diffUtil.currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolderRcyNewHotEveryoneWatching, position: Int) {
        val currentItem = diffUtil.currentList[position]
        holder.bind(currentItem)
    }

    inner class ViewHolderRcyNewHotEveryoneWatching(private val binding: RcyNewItemEveryonesWatchingBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind(movie:MovieResult) {

//
//                val genreList = movie.genreId.mapNotNull {genre->
//                    genres.find { it.id == genre }?.name
//                }
//                binding.txtCategories.text = genreList.joinToString { ", " }

                //binding.txtCategories.text = genres.substring(1,genres.length-1)
                Picasso.get().load("$IMAGE_URL${movie.backDropPath}").into(binding.videoPlayer)
                Picasso.get().load("$IMAGE_URL${movie.posterPath}").into(binding.imgGameName)
                binding.txtDescription.text = movie.overView
                binding.videoPlayer.setOnClickListener {
                    movieClickListener.movieClickListener(movie.id.toLong())
                }
            }

    }

    fun submitList(movieList:List<MovieResult>){
        diffUtil.submitList(movieList)
    }

}