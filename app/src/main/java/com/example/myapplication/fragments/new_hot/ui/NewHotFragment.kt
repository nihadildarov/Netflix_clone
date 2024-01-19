package com.example.myapplication.fragments.new_hot.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentNewHotBinding

class NewHotFragment : Fragment() {
    private lateinit var binding:FragmentNewHotBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewHotBinding.inflate(inflater,container,false)
        return binding.root
    }

}