package com.example.myapplication.fragments.verify_email.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.databinding.FragmentVerifyEmailBinding

class VerifyEmailFragment : Fragment() {
    private lateinit var binding: FragmentVerifyEmailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVerifyEmailBinding.inflate(inflater,container,false)
        return binding.root
    }

}