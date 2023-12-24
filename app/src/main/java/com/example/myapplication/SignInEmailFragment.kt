package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.databinding.FragmentSignInEmailBinding

class SignInEmailFragment : Fragment() {
    private lateinit var binding: FragmentSignInEmailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignInEmailBinding.inflate(inflater, container, false)
        return binding.root
    }

}