package com.example.myapplication.presentation.fragments.new_hot.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.remote.models.movie.Result
import com.example.myapplication.util.Constants.IMAGE_URL
import com.example.myapplication.databinding.RcyNewItemComingSoonBinding
import com.example.myapplication.presentation.adapter_listener.MovieClickListener
import com.squareup.picasso.Picasso

class AdapterRcyNewHotComingSoon(
    private val movieClickListener: MovieClickListener
):RecyclerView.Adapter<AdapterRcyNewHotComingSoon.ViewHolderRcyNewHotComingSoon>() {


    private val itemCallback = object : DiffUtil.ItemCallback<Result>(){
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem==newItem
        }
    }

    private val diffUtil = AsyncListDiffer(this,itemCallback)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolderRcyNewHotComingSoon {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RcyNewItemComingSoonBinding.inflate(inflater,parent,false)

        return ViewHolderRcyNewHotComingSoon(binding)
    }

    override fun getItemCount(): Int {
        Log.i("LIST_SIZE",diffUtil.currentList.size.toString())
        return diffUtil.currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolderRcyNewHotComingSoon, position: Int) {
        val currentItem = diffUtil.currentList[position]
        holder.bind(currentItem)
    }

    inner class ViewHolderRcyNewHotComingSoon (private val binding: RcyNewItemComingSoonBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(movie:Result){
            Picasso.get().load("$IMAGE_URL${movie.backdrop_path}").into(binding.imgVideo)
            Picasso.get().load("$IMAGE_URL${movie.poster_path}").into(binding.imgPosterFilmName)
            binding.txtComingDate.text = "Coming in ${movie.release_date}"
            binding.txtDesc.text = movie.overview
            binding.txtDay.text = movie.release_date.substring(8,10)
            binding.txtMonth.text =
                when (movie.release_date.substring(5,7).toInt()){
                    1 -> {"JAN"}
                    2 -> {"FEB"}
                    3 -> {"MAR"}
                    4 -> {"APR"}
                    5 -> {"MAY"}
                    6 -> {"JUN"}
                    7 -> {"JUL"}
                    8 -> {"AUG"}
                    9 -> {"SEP"}
                    10 -> {"OCT"}
                    11 -> {"NOV"}
                    else -> {"DEC"}
                }


            binding.txtGenres.text = movie.genre_ids.toString()
            binding.imgVideo.setOnClickListener {
                movieClickListener.movieClickListener(movie.id.toLong())
            }
        }




    }




    fun submitList(movieList:List<Result>){
        diffUtil.submitList(movieList)
    }
}