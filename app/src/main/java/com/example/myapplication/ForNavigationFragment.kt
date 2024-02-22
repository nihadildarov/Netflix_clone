package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.myapplication.databinding.FragmentForNavigationBinding

class ForNavigationFragment : Fragment() {
    private lateinit var binding: FragmentForNavigationBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentForNavigationBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        with(binding){
            accounts.setOnClickListener { findNavController().navigate(ForNavigationFragmentDirections.fActionForNavigationToAccounts()) }
            createAccount.setOnClickListener { findNavController().navigate(ForNavigationFragmentDirections.fActionForNavigationToCreateAccount("")) }
            downloads.setOnClickListener { findNavController().navigate(ForNavigationFragmentDirections.fActionForNavigationToDownloads())}
            finishSignUp.setOnClickListener { findNavController().navigate(ForNavigationFragmentDirections.fActionForNavigationToFinishSignUp()) }
            getStarted.setOnClickListener { findNavController().navigate(ForNavigationFragmentDirections.fActionForNavigationToGetStarted()) }
            home.setOnClickListener { findNavController().navigate(ForNavigationFragmentDirections.fActionForNavigationToHome()) }
            newHot.setOnClickListener { findNavController().navigate(ForNavigationFragmentDirections.fActionForNavigationToNewHot()) }
            search.setOnClickListener { findNavController().navigate(ForNavigationFragmentDirections.fActionForNavigationToSearch()) }
            signIn.setOnClickListener { findNavController().navigate(ForNavigationFragmentDirections.fActionForNavigationToSignIn("")) }
            signUp.setOnClickListener { findNavController().navigate(ForNavigationFragmentDirections.fActionForNavigationToSignUp()) }
            verifyEmail.setOnClickListener { findNavController().navigate(ForNavigationFragmentDirections.fActionForNavigationToVerifyEmail()) }
        }
    }


}