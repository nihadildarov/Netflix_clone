package com.example.myapplication.presentation.fragments.signup.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignUpBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(inflater,container,false)
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
            findNavController().navigate(R.id.action_signInMain_to_createAccount)
        }
    }





}