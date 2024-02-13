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
            accounts.setOnClickListener { findNavController().navigate(R.id.f_action_forNavigation_to_accounts) }
            createAccount.setOnClickListener { findNavController().navigate(R.id.f_action_forNavigation_to_createAccount) }
            downloads.setOnClickListener { findNavController().navigate(R.id.f_action_forNavigation_to_downloads)}
            finishSignUp.setOnClickListener { findNavController().navigate(R.id.f_action_forNavigation_to_finishSignUp) }
            getStarted.setOnClickListener { findNavController().navigate(R.id.f_action_forNavigation_to_getStarted) }
            home.setOnClickListener { findNavController().navigate(R.id.f_action_forNavigation_to_home) }
            newHot.setOnClickListener { findNavController().navigate(R.id.f_action_forNavigation_to_newHot) }
            search.setOnClickListener { findNavController().navigate(R.id.f_action_forNavigation_to_search) }
            signIn.setOnClickListener { findNavController().navigate(R.id.f_action_forNavigation_to_signIn) }
            signUp.setOnClickListener { findNavController().navigate(R.id.f_action_forNavigation_to_signUp) }
            verifyEmail.setOnClickListener { findNavController().navigate(R.id.f_action_forNavigation_to_verifyEmail) }
        }


    }


}