package com.example.myapplication.fragments.signin.email.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.myapplication.databinding.FragmentSignInBinding

class SignInFragment : Fragment() {
    private lateinit var binding: FragmentSignInBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnNewUserClick()
        btnBack()
    }


    private fun btnNewUserClick(){
        binding.txtSignUpNow.setOnClickListener {
            findNavController().popBackStack()
        }
    }


    private fun btnBack(){
        binding.btnBack.setOnClickListener{
            findNavController().popBackStack()
        }
    }
}
