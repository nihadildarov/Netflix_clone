package com.example.myapplication.presentation.fragments.getstarted.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.GsItemPageBinding

class ViewPagerAdapterGetStartedBG(
    private val images: List<Int>,
    private val titles: List<String>,
    private val descs: List<String>
) : RecyclerView.Adapter<ViewPagerAdapterGetStartedBG.GetStartedViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GetStartedViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = GsItemPageBinding.inflate(inflater, parent, false)
        return GetStartedViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    override fun onBindViewHolder(holder: GetStartedViewHolder, position: Int) {
        val currentImg = images[position]
        val currentTitle = titles[position]
        val currentDesc = descs[position]
        holder.bind(currentImg,currentTitle,currentDesc)
    }

    inner class GetStartedViewHolder(private val binding: GsItemPageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(image: Int,title: String,desc: String) {
            binding.imageView2.setImageResource(image)
            binding.txtTitle.text = title
            binding.txtDesc.text = desc

        }
    }
}
