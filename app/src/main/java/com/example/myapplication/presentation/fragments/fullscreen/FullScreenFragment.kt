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
import com.example.myapplication.databinding.FragmentFullScreenBinding
import com.example.myapplication.util.Resource
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
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
        swipeDown()

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

                    binding.videoPlayer.addYouTubePlayerListener(object :
                        AbstractYouTubePlayerListener() {
                        override fun onReady(youTubePlayer: YouTubePlayer) {
                            super.onReady(youTubePlayer)
                            youTubePlayer.cueVideo(video.data[0].key, 0f)
                        }
                    })
                }
            }
        }

    }


    private fun swipeDown() {
        val swipeDownGestureDetector = object : GestureDetector.OnGestureListener {
            override fun onDown(e: MotionEvent): Boolean {
                Toast.makeText(context, "onDoubleTapEvent", Toast.LENGTH_LONG).show()
                findNavController().popBackStack()
                return true
            }

            override fun onShowPress(e: MotionEvent) {
                Toast.makeText(context, "onDoubleTapEvent", Toast.LENGTH_LONG).show()
            }

            override fun onSingleTapUp(e: MotionEvent): Boolean {
                Toast.makeText(context, "onDoubleTapEvent", Toast.LENGTH_LONG).show()
                return true
            }

            override fun onScroll(
                e1: MotionEvent?,
                e2: MotionEvent,
                distanceX: Float,
                distanceY: Float
            ): Boolean {
                Toast.makeText(context, "onDoubleTapEvent", Toast.LENGTH_LONG).show()
                return true
            }

            override fun onLongPress(e: MotionEvent) {
                Toast.makeText(context, "onDoubleTapEvent", Toast.LENGTH_LONG).show()
            }

            override fun onFling(
                e1: MotionEvent?,
                e2: MotionEvent,
                velocityX: Float,
                velocityY: Float
            ): Boolean {
                Toast.makeText(context, "onDoubleTapEvent", Toast.LENGTH_LONG).show()
                return true
            }


        }

        fun onSwipeDown() {

        }


    }


}