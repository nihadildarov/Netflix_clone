package com.example.myapplication.presentation.fragments.fullscreen

import android.content.pm.ActivityInfo
import android.gesture.Gesture
import android.opengl.Visibility
import android.os.Bundle
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentFullScreenBinding
import com.example.myapplication.util.Resource
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerCallback
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FullScreenFragment : Fragment() {

    private lateinit var binding: FragmentFullScreenBinding
    private val viewModel by viewModels<FullScreenViewModel>()
    private val args by navArgs<FullScreenFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFullScreenBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movieId = args.movieId
        observe(movieId)

    }

    private fun observe(movieId: Long) {
        viewModel.getMovieVideoById(movieId)


        viewModel.movie.observe(viewLifecycleOwner) { video ->

            when (video) {
                Resource.Loading -> {
                    binding.videoPlayer.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE
                }

                is Resource.Error -> {

                }

                is Resource.Success -> {

                    binding.videoPlayer.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE


                    with(binding.videoPlayer) {

                        initialize(object : AbstractYouTubePlayerListener() {
                            override fun onReady(youTubePlayer: YouTubePlayer) {
                                super.onReady(youTubePlayer)
                            }
                        })
                        addYouTubePlayerListener(object :
                            AbstractYouTubePlayerListener() {
                            override fun onReady(youTubePlayer: YouTubePlayer) {
                                super.onReady(youTubePlayer)
                                youTubePlayer.cueVideo(video.data[0].key, 0f)
                            }


                        })
                    }


                    binding.videoPlayer
                    binding.videoPlayer.enableAutomaticInitialization = true
                }
            }
        }

    }


}