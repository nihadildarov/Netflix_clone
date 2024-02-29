package com.example.myapplication

import android.graphics.Rect
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.databinding.FragmentYoutubePlayerBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class YoutubePlayerFragment : Fragment() {

    private lateinit var binding: FragmentYoutubePlayerBinding
    private lateinit var playerView: YouTubePlayerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentYoutubePlayerBinding.inflate(inflater,container,false)
        binding.youtubePlayerView.getGlobalVisibleRect(
            Rect()
        )

        return binding.root

    }



}