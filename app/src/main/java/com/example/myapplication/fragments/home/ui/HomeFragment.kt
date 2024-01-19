package com.example.myapplication.fragments.home.ui

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.fragments.home.adapter.AdapterRecyclersBig
import com.example.myapplication.fragments.home.adapter.AdapterRecyclersContinueWatching
import com.example.myapplication.fragments.home.adapter.AdapterRecyclersMedium
import com.example.myapplication.fragments.home.adapter.AdapterRecyclersSmall

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setGamesAdapter ()
        createRecyclers()
    }


    private fun setGamesAdapter (){
        binding.rcyMobileGames.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        binding.rcyMobileGames.adapter = AdapterRecyclersSmall(
            listOf(
                R.drawable.ic_netflix_short_logo,
                R.drawable.img,
                R.drawable.logo_netflix)
        )
    }


    private fun createRecyclers(){
        val itemList = listOf(
            R.drawable.img,
            R.drawable.img,
            R.drawable.img,
            R.drawable.img,
            R.drawable.img,
            R.drawable.img,
            R.drawable.img,
            R.drawable.img,
            R.drawable.img

            )

        val textList = listOf(
            "Birinci",
            "Ikinci",
            "Ucuncu",
            "Dorduncu",
            "Besinci",
            "Altinci",
            "Yeddinci",
            "Continue watching",
            "Sekkizinci",
            "Doqquzuncu",
            "Onuncu"
        )

        val adapterMedium = AdapterRecyclersMedium(itemList)
        val adapterBig = AdapterRecyclersBig(itemList)
        val adapterSmall = AdapterRecyclersSmall(itemList)
        val adapterContinueWatching = AdapterRecyclersContinueWatching(itemList)

        var prevId = 1

        for (i in 1..textList.size){
            val constraintLayout = binding.constraintHome
            val constraintSet = ConstraintSet()


            val textHeader = TextView(requireContext())
            textHeader.text = textList[i-1]
            textHeader.id = View.generateViewId()
            textHeader.setTextColor(resources.getColor(R.color.white))
            val paramsTxt = ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_CONSTRAINT,ConstraintLayout.LayoutParams.WRAP_CONTENT)
            textHeader.layoutParams = paramsTxt


            val recycler = RecyclerView(requireContext())
            recycler.id = View.generateViewId()
            recycler.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            val paramsRcy = ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT,ConstraintLayout.LayoutParams.WRAP_CONTENT)
            recycler.layoutParams = paramsRcy

            constraintLayout.addView(textHeader)
            constraintLayout.addView(recycler)

            constraintSet.clone(constraintLayout)

            //TextHeaders constraints
            //Top
            if(i==1){
                constraintSet.connect(textHeader.id,ConstraintSet.TOP,binding.rcyMobileGames.id,ConstraintSet.BOTTOM,50)
            } else {
                constraintSet.connect(textHeader.id,ConstraintSet.TOP,prevId,ConstraintSet.BOTTOM,50)
            }

            //Start
            constraintSet.connect(textHeader.id,ConstraintSet.START,ConstraintSet.PARENT_ID,ConstraintSet.START)
            //End
            constraintSet.connect(textHeader.id,ConstraintSet.END,ConstraintSet.PARENT_ID,ConstraintSet.END)



            //Recyclers constraints
            //Top
            constraintSet.connect(recycler.id,ConstraintSet.TOP,textHeader.id,ConstraintSet.BOTTOM,)
            //Start
            constraintSet.connect(recycler.id,ConstraintSet.START,ConstraintSet.PARENT_ID,ConstraintSet.START)
            //End
            constraintSet.connect(recycler.id,ConstraintSet.END,ConstraintSet.PARENT_ID,ConstraintSet.END)

            prevId = recycler.id
            when  {
                i==4 -> recycler.adapter = adapterBig
                i==1 -> recycler.adapter = adapterSmall
                i==textList.size -> recycler.adapter = adapterBig
                textList[i]== "Continue watching" -> recycler.adapter = adapterContinueWatching
                else -> recycler.adapter = adapterMedium
            }

            constraintSet.applyTo(constraintLayout)
        }



    }


}