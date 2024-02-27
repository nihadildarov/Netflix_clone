package com.example.myapplication.presentation.fragments.fullscreen

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
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
        binding = FragmentFullScreenBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movieId = args.movieId
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        observe(movieId)

    }

    private fun observe(movieId: Long){
        viewModel.getMovieVideoById(movieId)


        viewModel.movie.observe(viewLifecycleOwner){video->

            when (video){
                Resource.Loading ->{}
                is Resource.Error ->{}
                is Resource.Success ->{

            binding.videoPlayer.addYouTubePlayerListener(object : AbstractYouTubePlayerListener(){
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    super.onReady(youTubePlayer)
                    youTubePlayer.cueVideo(video.data[0].key,0f)
                }
            })
                }
            }
        }

    }


}