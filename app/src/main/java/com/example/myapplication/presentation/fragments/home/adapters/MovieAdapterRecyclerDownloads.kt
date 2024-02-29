package com.example.myapplication.presentation.fragments.home.adapters

import android.graphics.Color
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.RcyFirstItemDownloadsBinding
import com.example.myapplication.databinding.RcyMovieItemMediumPosterBinding
import com.example.myapplication.util.Resource
import java.lang.IllegalArgumentException

class MovieAdapterRecyclerDownloads(
    private val dataList: List<Int>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    var isLoaded = false

    private val VIEW_TYPE_FIRST = 1
    private val VIEW_TYPE_NORMAL = 2

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) VIEW_TYPE_FIRST else VIEW_TYPE_NORMAL
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_FIRST -> {
                val binding = RcyFirstItemDownloadsBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                FirstViewHolder(binding)
            }

            VIEW_TYPE_NORMAL -> {
                val binding = RcyMovieItemMediumPosterBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                NormalViewHolder(binding)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            VIEW_TYPE_FIRST -> {

                val firstViewHolder = holder as FirstViewHolder
                firstViewHolder.bind(dataList[0])

            }

            VIEW_TYPE_NORMAL -> {
                val normalViewHolder = holder as NormalViewHolder
                normalViewHolder.bind(dataList[position])
            }
        }

    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    // ViewHolder classes
    inner class FirstViewHolder(private val binding: RcyFirstItemDownloadsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(image: Int) {
            binding.imgBackGround.setImageResource(image)
            val message = binding.txtDesc.text.toString()

            val spannableString = SpannableString(message)
            val startIndex = message.indexOf("Downloads for You")
            val endIndex = startIndex + "Downloads for You".length
            val spanColor = ForegroundColorSpan(Color.WHITE)

            spannableString.setSpan(
                spanColor,
                startIndex,
                endIndex,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            binding.txtDesc.text = spannableString
        }
    }

    inner class NormalViewHolder(private val binding: RcyMovieItemMediumPosterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(image: Int) {

            if (isLoaded){
                binding.imgMediumPoster.visibility = VISIBLE
                binding.progressBar.visibility = GONE
            }else{
                binding.imgMediumPoster.visibility = GONE
                binding.progressBar.visibility = VISIBLE
            }
            binding.imgMediumPoster.setImageResource(image)

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



