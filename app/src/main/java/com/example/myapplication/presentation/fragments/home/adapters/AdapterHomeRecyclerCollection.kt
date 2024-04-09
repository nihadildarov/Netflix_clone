package com.example.myapplication.presentation.fragments.home.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.findFragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.RcyHomeItemsChildRcysBinding
import com.example.myapplication.presentation.fragments.home.model.ChildRcyModel
import com.example.myapplication.presentation.fragments.home.ui.HomeFragment
import com.example.myapplication.presentation.fragments.home.ui.HomeFragmentDirections
import kotlin.random.Random

class AdapterHomeRecyclerCollection(
    private val movieAdapterBig: MovieAdapterRecyclersBig,
    private var movieAdapterRecyclersMedium: MovieAdapterRecyclersMedium,
    private val movieAdapterRecyclersContinueWatching: MovieAdapterRecyclersContinueWatching
) : RecyclerView.Adapter<AdapterHomeRecyclerCollection.ViewHolderHomeRecyclerCollection>() {

    private var progressVisibility = false
    private val itemCallBack = object : DiffUtil.ItemCallback<ChildRcyModel>() {
        override fun areItemsTheSame(oldItem: ChildRcyModel, newItem: ChildRcyModel): Boolean {
            return oldItem.id == newItem.id && oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: ChildRcyModel, newItem: ChildRcyModel): Boolean {
            return oldItem == newItem
        }

    }

    private val diffUtil = AsyncListDiffer(this, itemCallBack)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolderHomeRecyclerCollection {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RcyHomeItemsChildRcysBinding.inflate(inflater, parent, false)
        return ViewHolderHomeRecyclerCollection(binding)
    }

    override fun getItemCount(): Int {
        Log.e("ItemCount",diffUtil.currentList.size.toString())
        Log.e("ItemCount",diffUtil.currentList.toString())
        return diffUtil.currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolderHomeRecyclerCollection, position: Int) {
        val current = diffUtil.currentList[position]

        holder.bind(current)
    }


    inner class ViewHolderHomeRecyclerCollection(
        private val binding: RcyHomeItemsChildRcysBinding,
    ) : RecyclerView.ViewHolder(binding.root) {


        fun bind(childItem: ChildRcyModel) {
            binding.txtRcyHeaders.text = childItem.header
            binding.rcyHomeRecyclersItem.layoutManager =
                LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)



            val shuffledList = childItem.recycler.shuffled()
            Log.i("childItemHeaderName", childItem.header)
            when (childItem.header) {

                "Only on Netflix" -> {
                    binding.rcyHomeRecyclersItem.adapter = movieAdapterBig
                    movieAdapterBig.submitList(childItem.recycler.shuffled())
                }

                "Continue watching" -> {
                    binding.rcyHomeRecyclersItem.adapter =
                        movieAdapterRecyclersContinueWatching
                    movieAdapterRecyclersContinueWatching.submitList(childItem.recycler.shuffled())
                }


                else -> {

                    movieAdapterRecyclersMedium = MovieAdapterRecyclersMedium {
                        val action = itemView.findFragment<HomeFragment>()
                        action.findNavController().navigate(HomeFragmentDirections.actionHomeToMovieDetails(it))
                    }
                    Log.e("MediumAdapter",shuffledList.toString())
                    binding.rcyHomeRecyclersItem.adapter = movieAdapterRecyclersMedium
                    movieAdapterRecyclersMedium.submitList(shuffledList)

                }
            }



            if (progressVisibility){
                movieAdapterRecyclersContinueWatching.showProgress()
                movieAdapterBig.showProgress()
                movieAdapterRecyclersMedium.showProgress()
            } else {
                movieAdapterBig.hideProgress()
                movieAdapterRecyclersMedium.hideProgress()
                movieAdapterRecyclersContinueWatching.hideProgress()
            }
        }
    }

    fun submitList(list: List<ChildRcyModel>) {
        diffUtil.submitList(list)
        Log.e("diffutilSubmitted", list.size.toString())
    }


    fun showProgress(){
        progressVisibility = true

    }

    fun hideProgress(){
        progressVisibility = false

    }
}

