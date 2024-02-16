package com.example.myapplication.presentation.fragments.search.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentSearchBinding
import com.example.myapplication.presentation.adapter_listener.MovieClickListener
import com.example.myapplication.presentation.fragments.search.adapter.AdapterSearchGames
import com.example.myapplication.presentation.fragments.search.adapter.AdapterSearchMovie
import com.example.myapplication.presentation.fragments.search.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private val viewModel by viewModels<SearchViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclers()
    }


    private fun setRecyclers(){

        binding.rcyRecommendedGames.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        binding.rcyRecommendedMovies.layoutManager = LinearLayoutManager(requireContext())

        val adapterMovie = AdapterSearchMovie(
            object : MovieClickListener{
                override fun movieClickListener(movieId: Long) {
                    findNavController().navigate(SearchFragmentDirections.actionSearchToMovieDetails(movieId))
                }
            }
        )

        val adapterGames = AdapterSearchGames(
            object : MovieClickListener{
                override fun movieClickListener(movieId: Long) {
                    findNavController().navigate(SearchFragmentDirections.actionSearchToMovieDetails(movieId))
                }
            }
        )
        setData(adapterMovie,adapterGames)

        binding.rcyRecommendedGames.adapter = adapterGames
        binding.rcyRecommendedMovies.adapter = adapterMovie
    }



    private fun setData (adapterMovie: AdapterSearchMovie,adapterGame: AdapterSearchGames){
        viewModel.getMovie()
        viewModel.movie.observe(viewLifecycleOwner){
            adapterMovie.submitList(it)
            adapterGame.submitList(it)
        }
    }




}
