package com.example.myapplication.presentation.fragments.movie_details.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.myapplication.databinding.FragmentMovieDetailsBinding
import com.example.myapplication.presentation.fragments.movie_details.viewmodel.DetailsViewModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {
    private lateinit var binding : FragmentMovieDetailsBinding
    private val viewModel by viewModels<DetailsViewModel>()
    private val args by navArgs<MovieDetailsFragmentArgs>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieDetailsBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movieId = args.movieId

        viewModel.getMovieById(movieId)
        viewModel.getMovieVideoById(movieId)
        viewModel.isMovieExists(movieId)
        testPlayer ()

    }

    private fun testPlayer () {

        viewModel.movieVideo.observe(viewLifecycleOwner){

            binding.player.addYouTubePlayerListener(object : AbstractYouTubePlayerListener(){
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    super.onReady(youTubePlayer)
                    youTubePlayer.cueVideo(it[0].key,0f)
                }
            })


        }

    }

}

