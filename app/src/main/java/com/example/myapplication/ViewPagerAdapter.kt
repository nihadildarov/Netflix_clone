package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.GsItemPageBinding

class ViewPagerAdapter( private var title:List<String>, private var desc:List<String>,private var img:List<Int>):RecyclerView.Adapter <ViewPagerAdapter.PagerViewHolder> () {

    inner class PagerViewHolder(private val binding:GsItemPageBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(title:String ,desc:String,img:Int){
            with(binding){
                txtTitle.text=title
                txtDesc.text=desc
                imageView2.setImageResource(img)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = GsItemPageBinding.inflate(inflater,parent,false)
        return PagerViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return title.size
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.bind(title[position],desc[position],img[position])
    }
}