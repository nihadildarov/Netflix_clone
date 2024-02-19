package com.example.myapplication.presentation.fragments.home.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.remote.models.movie.Result
import com.example.myapplication.util.Constants.IMAGE_URL
import com.example.myapplication.databinding.RcyMovieItemMediumPosterBinding
import com.example.myapplication.presentation.adapter_listener.MovieClickListener
import com.squareup.picasso.Picasso

class MovieAdapterRecyclersMedium(
    private val movieClickListener: MovieClickListener
):RecyclerView.Adapter<MovieAdapterRecyclersMedium.RcyViewHolder>() {

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





    inner class RcyViewHolder(private val binding:RcyMovieItemMediumPosterBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(movie: Result){

            val img = "$IMAGE_URL${movie.poster_path}"
            Picasso.get().load("$IMAGE_URL${movie.poster_path}").into(binding.imgMediumPoster)

            binding.imgMediumPoster.setOnClickListener {
                movieClickListener.movieClickListener(movie.id.toLong())
            }
            Log.i("movie111",img)
        }
    }



    fun submitList(movieList:List<Result>){
        diffUtil.submitList(movieList)
    }

    fun updateMovieList(newList:List<Result>) {
        diffUtil.submitList(newList)
        diffUtil.currentList
    }

}
