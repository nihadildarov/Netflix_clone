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
import com.example.myapplication.presentation.adapter_listener.MovieClickListener
import com.example.myapplication.presentation.fragments.home.adapters.MovieAdapterRecyclersMedium
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
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }





    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclers()
        setAdapters()
        btnBack()




    }












    private fun setRecyclers() {

        binding.rcyRecommendedGames.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rcyRecommendedMovies.layoutManager = LinearLayoutManager(requireContext())
        binding.rcySearchResultMovies.layoutManager = GridLayoutManager(requireContext(), 3)
    }






    private fun setAdapters() {

        val adapterMovie = AdapterSearchMovie(
            object : MovieClickListener {
                override fun movieClickListener(movieId: Long) {
                    findNavController().navigate(
                        SearchFragmentDirections.actionSearchToMovieDetails(
                            movieId
                        )
                    )
                }
            }
        )


        val adapterGames = AdapterSearchGames(
            object : MovieClickListener {
                override fun movieClickListener(movieId: Long) {
                    findNavController().navigate(
                        SearchFragmentDirections.actionSearchToMovieDetails(
                            movieId
                        )
                    )
                }
            }
        )


        val adapterMedium = MovieAdapterRecyclersMedium(
            object : MovieClickListener {
                override fun movieClickListener(movieId: Long) {
                    findNavController().navigate(
                        SearchFragmentDirections.actionSearchToMovieDetails(
                            movieId
                        )
                    )
                }
            }
        )

        binding.rcyRecommendedGames.adapter = adapterGames
        binding.rcyRecommendedMovies.adapter = adapterMovie
        binding.rcySearchResultMovies.adapter = adapterMedium

        setDataWithNoQuery(adapterMovie, adapterGames)
        setDataWithQuery(adapterMedium)
        searchMovie(adapterMedium)
    }






    private fun setDataWithNoQuery(
        adapterSearchMovie: AdapterSearchMovie,
        adapterGame: AdapterSearchGames
    ) {
        viewModel.getMovie()
        viewModel.movie.observe(viewLifecycleOwner) {
            adapterSearchMovie.submitList(it)
            adapterGame.submitList(it)

        }
    }





    private fun setDataWithQuery(adapterMovie: MovieAdapterRecyclersMedium) {
        viewModel.searchResultMovie.observe(viewLifecycleOwner) {
            Log.i("setDataWithQuery", "test")
            adapterMovie.updateMovieList(it)
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
            }
        )
    }






    private fun btnBack(){
        binding.imgBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }


    private fun setVisibilities(query:String){
        with(binding){
            if (query != "" || query.isNotBlank()){
                Log.i("setVisibilities",searchBox.query.isNullOrBlank().toString())
                rcySearchResultMovies.visibility = View.VISIBLE
                rcyRecommendedMovies.visibility = View.GONE
                rcyRecommendedGames.visibility = View.GONE
                txtRecommendedGames.visibility = View.GONE
                txtRecommendedMovies.visibility = View.GONE


            }
            else {
                Log.i("setVisibilities",searchBox.query.isNullOrBlank().toString())
                rcySearchResultMovies.visibility = View.GONE
                rcyRecommendedMovies.visibility = View.VISIBLE
                rcyRecommendedGames.visibility = View.VISIBLE
                txtRecommendedGames.visibility = View.VISIBLE
                txtRecommendedMovies.visibility = View.VISIBLE

            }
        }
    }


}
