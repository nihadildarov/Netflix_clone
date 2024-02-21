package com.example.myapplication.presentation.fragments.signup.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationSet
import androidx.navigation.fragment.findNavController
import androidx.transition.Fade
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentSignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignUpBinding
    private lateinit var auth: FirebaseAuth
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

    }


    private fun btnXClick(){
        binding.btnClose.setOnClickListener{
            findNavController().popBackStack()

        }
    }

    private fun btnGetStartedClick(){
        binding.btnGetStarted.setOnClickListener {
            findNavController().navigate(SignUpFragmentDirections.actionSignInMainFragmentToSignInEmailFragment(binding.edtEmail.text.toString()))
        }
    }





}