package com.example.myapplication.presentation.fragments.new_hot.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentNewHotBinding
import com.example.myapplication.presentation.adapter_listener.MovieClickListener
import com.example.myapplication.presentation.fragments.new_hot.adapter.AdapterRcyNewHotComingSoon
import com.example.myapplication.presentation.fragments.new_hot.adapter.AdapterRcyNewHotEveryoneWatching
import com.example.myapplication.presentation.fragments.new_hot.adapter.AdapterRcyNewHotGames
import com.example.myapplication.presentation.fragments.new_hot.view_model.NewHotViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewHotFragment : Fragment() {
    private lateinit var binding:FragmentNewHotBinding
    private val viewModel by viewModels<NewHotViewModel> ()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewHotBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclers()
        chipsClick()
        viewModel.getUpComingMovies()
        viewModel.getEveryOneWatchingMovies()


    }


    private fun setRecyclers(){
        with(binding){
            rcyComingSoon.layoutManager = LinearLayoutManager(requireContext())
            rcyEveryonesWatching.layoutManager = LinearLayoutManager(requireContext())
            rcyGames.layoutManager = LinearLayoutManager(requireContext())
            setAdapters()
        }
    }


    private fun setAdapters(){


        val comingSoonAdapter = AdapterRcyNewHotComingSoon(
            object :MovieClickListener{
                override fun movieClickListener(movieId: Long) {
                    //findNavController().navigate(NewHotFragmentDirections.actionNewHotFragmentToMovieDetailsFragment(movieId))
                }
            }
        )


        viewModel.upComing.observe(viewLifecycleOwner){
            comingSoonAdapter.submitList(it.sortedBy { it.release_date })
        }


        val everyOnesWatchingAdapter = AdapterRcyNewHotEveryoneWatching(
            object : MovieClickListener{
                override fun movieClickListener(movieId: Long) {
                    //findNavController().navigate(NewHotFragmentDirections.actionNewHotFragmentToMovieDetailsFragment(movieId))
                }
            }
        )

        viewModel.everyOneWatching.observe(viewLifecycleOwner){
            everyOnesWatchingAdapter.submitList(it.shuffled())
        }

        val gamesAdapter = AdapterRcyNewHotGames(listOf(R.drawable.img,R.drawable.img,R.drawable.img,))

        with(binding){
            rcyComingSoon.adapter = comingSoonAdapter
            rcyEveryonesWatching.adapter = everyOnesWatchingAdapter
            rcyGames.adapter = gamesAdapter
        }
    }


    private fun chipsClick(){
        with(binding) {

            chipComingSoon.setOnClickListener {
                Toast.makeText(context,"chip1",Toast.LENGTH_SHORT).show()
                scrollViewRcy.smoothScrollTo(0,rcyComingSoon.top)
            }

            chipEveryoneWatch.setOnClickListener {
                Toast.makeText(context,"chip2",Toast.LENGTH_SHORT).show()
                scrollViewRcy.smoothScrollTo(0,rcyEveryonesWatching.top)
            }

            chipGames.setOnClickListener {
                Toast.makeText(context,"chip3",Toast.LENGTH_SHORT).show()
                scrollViewRcy.smoothScrollTo(0,rcyGames.top)
            }

        }
    }

}