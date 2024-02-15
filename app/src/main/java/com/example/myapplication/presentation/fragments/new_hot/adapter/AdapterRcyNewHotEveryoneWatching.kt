package com.example.myapplication.presentation.fragments.new_hot.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.remote.models.movie.Result
import com.example.myapplication.data.util.Constants.IMAGE_URL
import com.example.myapplication.databinding.RcyNewItemEveryonesWatchingBinding
import com.example.myapplication.presentation.adapter_listener.MovieClickListener
import com.squareup.picasso.Picasso

class AdapterRcyNewHotEveryoneWatching (
    private val movieClickListener: MovieClickListener
):
    RecyclerView.Adapter<AdapterRcyNewHotEveryoneWatching.ViewHolderRcyNewHotEveryoneWatching>() {

        private val itemCallback = object : DiffUtil.ItemCallback<Result>(){
            override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
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

            fun bind(movie:Result) {
                Picasso.get().load("$IMAGE_URL${movie.backdrop_path}").into(binding.videoPlayer)
                binding.videoPlayer.setOnClickListener {
                    movieClickListener.movieClickListener(movie.id.toLong())
                }
            }

    }

    fun submitList(movieList:List<Result>){
        diffUtil.submitList(movieList)
    }

}