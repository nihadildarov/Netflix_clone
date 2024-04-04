package com.example.myapplication.presentation.fragments.search.adapter

import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.remote.models.movie.Result
import com.example.myapplication.util.Constants.IMAGE_URL
import com.example.myapplication.databinding.RcyMovieItemGamesBinding
import com.example.myapplication.domain.remote.models.MovieResponse
import com.example.myapplication.domain.remote.models.MovieResult
import com.example.myapplication.presentation.adapter_listener.MovieClickListener
import com.squareup.picasso.Picasso

class AdapterSearchGames(
    private val movieClickListener: MovieClickListener
):RecyclerView.Adapter<AdapterSearchGames.AdapterViewHolder>() {

    private var isLoaded = false

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

        fun bind(movie:MovieResult){
            Picasso.get().load("$IMAGE_URL${movie.posterPath}").into(binding.imgSmallPoster)
            binding.txtName.text = movie.title
            binding.txtCategory.text = movie.vote.toString()
            binding.imgSmallPoster.setOnClickListener {
                movieClickListener.movieClickListener(movie.id.toLong())
            }

            if (isLoaded){
                binding.progressBar.visibility=GONE
                binding.txtName.visibility = VISIBLE
                binding.imgSmallPoster.visibility = VISIBLE
                binding.txtCategory.visibility = VISIBLE
                binding.cardvPoster.visibility = VISIBLE
            } else{
                binding.progressBar.visibility=VISIBLE
                binding.txtName.visibility = GONE
                binding.imgSmallPoster.visibility = GONE
                binding.txtCategory.visibility = GONE
                binding.cardvPoster.visibility = GONE
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
    fun submitList(movieList:List<MovieResult>){
        diffUtil.submitList(movieList)
    }
}