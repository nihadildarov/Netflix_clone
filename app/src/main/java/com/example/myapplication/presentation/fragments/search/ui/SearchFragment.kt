package com.example.myapplication.presentation.fragments.search.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.FragmentSearchBinding
import com.example.myapplication.domain.remote.Mapper.toMovieResult
import com.example.myapplication.presentation.adapter_listener.MovieClickListener
import com.example.myapplication.presentation.fragments.home.adapters.MovieAdapterRecyclersMedium
import com.example.myapplication.presentation.fragments.search.adapter.AdapterSearchGames
import com.example.myapplication.presentation.fragments.search.adapter.AdapterSearchMovie
import com.example.myapplication.presentation.fragments.search.viewmodel.SearchViewModel
import com.example.myapplication.util.Resource
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private val viewModel by viewModels<SearchViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclers()
        setAdapters()
        btnProfileClick()
        btnBack()
    }


    private fun setRecyclers() {

        binding.rcyRecommendedGames.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rcyRecommendedMovies.layoutManager = LinearLayoutManager(requireContext())
        binding.rcySearchResultMovies.layoutManager = GridLayoutManager(requireContext(), 3)
    }


    private fun setAdapters() {

        val adapterMovie = AdapterSearchMovie(object : MovieClickListener {
            override fun movieClickListener(movieId: Long) {
                findNavController().navigate(
                    SearchFragmentDirections.actionSearchToMovieDetails(
                        movieId
                    )
                )
            }
        })


        val adapterGames = AdapterSearchGames(object : MovieClickListener {
            override fun movieClickListener(movieId: Long) {
                findNavController().navigate(
                    SearchFragmentDirections.actionSearchToMovieDetails(
                        movieId
                    )
                )
            }
        })


        val adapterMedium = MovieAdapterRecyclersMedium{
            findNavController().navigate(SearchFragmentDirections.actionSearchToMovieDetails(it))
        }





        binding.rcyRecommendedGames.adapter = adapterGames
        binding.rcyRecommendedMovies.adapter = adapterMovie
        binding.rcySearchResultMovies.adapter = adapterMedium

        setDataWithNoQuery(adapterMovie, adapterGames)
        setDataWithQuery(adapterMedium)
        searchMovie(adapterMedium)
    }


    private fun setDataWithNoQuery(
        adapterSearchMovie: AdapterSearchMovie, adapterGame: AdapterSearchGames
    ) {
        viewModel.getMovie()
        viewModel.movie.observe(viewLifecycleOwner) {
            when (it) {
                Resource.Loading -> {
                    adapterGame.showProgress()
                    adapterSearchMovie.showProgress()
                }

                is Resource.Success -> {
                    adapterGame.hideProgress()
                    adapterSearchMovie.hideProgress()
                    adapterSearchMovie.submitList(it.data.toMovieResult())
                    adapterGame.submitList(it.data.toMovieResult())
                }

                is Resource.Error -> {

                }
            }

        }
    }


    private fun setDataWithQuery(adapterMovie: MovieAdapterRecyclersMedium) {
        viewModel.searchResultMovie.observe(viewLifecycleOwner) {
            Log.i("setDataWithQuery", "test")
            when (it) {
                Resource.Loading -> {
                    adapterMovie.showProgress()
                }

                is Resource.Success -> {
                    adapterMovie.hideProgress()
                    adapterMovie.updateMovieList(it.data.toMovieResult())
                }

                is Resource.Error -> {

                }
            }
        }
    }


    private fun searchMovie(
        adapterMovie: MovieAdapterRecyclersMedium
    ) {


        binding.searchBox.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.trim() != "") {
                    Log.i("onQueryTextChange", newText)
                    viewModel.searchMovieByName(newText)
                    setDataWithQuery(adapterMovie)
                    setVisibilities(newText)
                }
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                if (query.trim() != "") {
                    Log.i("onQueryTextSubmit", query)
                    viewModel.searchMovieByName(query)
                    setDataWithQuery(adapterMovie)
                    binding.searchBox.clearFocus()
                    setVisibilities(query)
                }
                return true
            }
        })
    }


    private fun btnBack() {
        binding.imgBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }


    private fun setVisibilities(query: String) {


        with(binding) {


            if (query == "" || query.isBlank()) {
                Log.i(
                    "setVisibilities",
                    "IsEmpty true? : " + searchBox.query.isNullOrBlank().toString()
                )
                rcySearchResultMovies.visibility = View.GONE
                rcyRecommendedMovies.visibility = View.VISIBLE
                rcyRecommendedGames.visibility = View.VISIBLE
                txtRecommendedGames.visibility = View.VISIBLE
                txtRecommendedMovies.visibility = View.VISIBLE
            } else {
                Log.i(
                    "setVisibilities",
                    "IsEmpty false?: " + searchBox.query.isNullOrBlank().toString()
                )
                rcySearchResultMovies.visibility = View.VISIBLE
                rcyRecommendedMovies.visibility = View.GONE
                rcyRecommendedGames.visibility = View.GONE
                txtRecommendedGames.visibility = View.GONE
                txtRecommendedMovies.visibility = View.GONE
            }


        }


    }


    private fun btnProfileClick() {
        binding.imgProfile.setOnClickListener {
            findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToProfilesAndMoreFragment())
        }
    }

}
