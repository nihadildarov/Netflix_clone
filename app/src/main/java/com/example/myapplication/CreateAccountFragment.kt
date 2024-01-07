package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.databinding.FragmentCreateAccountBinding


class CreateAccountFragment : Fragment() {
    private lateinit var binding: FragmentCreateAccountBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateAccountBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideMenuItems()
    }

    private fun hideMenuItems(){
        binding.toolbar.menu.getItem(0).setVisible(false)
        binding.toolbar.menu.getItem(2).setVisible(false)
        binding.toolbar.menu.getItem(1).setShowAsAction(1)
        binding.toolbar.menu.getItem(3).setShowAsAction(1)


    }


}