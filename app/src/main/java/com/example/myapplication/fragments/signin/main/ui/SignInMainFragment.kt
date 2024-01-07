package com.example.myapplication.fragments.signin.main.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentSignInMainBinding

class SignInMainFragment : Fragment() {
    private lateinit var binding: FragmentSignInMainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignInMainBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnXClick()
        btnGetStartedClick()
        //methods will be called here
    }


    private fun btnXClick(){
        binding.btnClose.setOnClickListener{
            findNavController().popBackStack()
        }
    }

    private fun btnGetStartedClick(){
        binding.btnGetStarted.setOnClickListener {
            findNavController().navigate(R.id.action_signInMainFragment_to_createAccountFragment)
        }
    }





}