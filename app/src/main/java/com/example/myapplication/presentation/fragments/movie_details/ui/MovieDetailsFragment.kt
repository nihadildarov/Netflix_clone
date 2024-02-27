package com.example.myapplication.presentation.fragments.movie_details.ui

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myapplication.databinding.FragmentMovieDetailsBinding
import com.example.myapplication.presentation.fragments.movie_details.viewmodel.DetailsViewModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {
    private lateinit var binding: FragmentMovieDetailsBinding
    private val viewModel by viewModels<DetailsViewModel>()
    private val args by navArgs<MovieDetailsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movieId = args.movieId


        initViewModel(movieId)
        btnPlayClick(movieId)
        setPlayer()
        setDetails()
        searchBtnClick()
        btnBackClick()
    }


    private fun initViewModel(movieId:Long){



        viewModel.getMovieById(movieId)
        viewModel.getMovieVideoById(movieId)
        viewModel.isMovieExists(movieId)
    }
    private fun setPlayer() {

        viewModel.movieVideo.observe(viewLifecycleOwner) {

            Log.e("VideoKey", it[0].key)
            binding.player.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    super.onReady(youTubePlayer)
                    youTubePlayer.cueVideo(it[0].key, 0f)
                    }
                }
            )
        }
    }


    private fun setDetails(){
                viewModel.movieDetail.observe(viewLifecycleOwner){
                    binding.txtMovieName.text = it.title
                    binding.txtDescription.text = it.overview
                    binding.txtReleaseDate.text = it.release_date.substring(0,4)
                    binding.txtAgeRestriction.text = if(it.adult){ "18+ "}else{"18-"}
                    binding.txtSeasonInfo.text = it.original_language
                }
    }


    private fun searchBtnClick(){
        binding.btnSearch.setOnClickListener {
            findNavController().navigate(MovieDetailsFragmentDirections.actionMovieDetailsToSearch())
        }
    }



    private fun btnBackClick(){
        binding.imgBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun btnPlayClick(movieId: Long){
        binding.btnPlay.setOnClickListener {
            findNavController().navigate(MovieDetailsFragmentDirections.actionMovieDetailsFragmentToFullScreenFragment(movieId))
        }
    }


}

