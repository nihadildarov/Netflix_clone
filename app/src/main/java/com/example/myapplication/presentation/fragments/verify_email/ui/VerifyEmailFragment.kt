package com.example.myapplication.presentation.fragments.verify_email.ui

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.CLIPBOARD_SERVICE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myapplication.databinding.FragmentVerifyEmailBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth


class VerifyEmailFragment : Fragment() {
    private lateinit var binding: FragmentVerifyEmailBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVerifyEmailBinding.inflate(inflater, container, false)
        auth = Firebase.auth

        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnSignOutClick()
        btnCopyLinkClick()
        cardViewClickToCopy()
    }


    private fun btnSignOutClick() {
        binding.txtSignOut.setOnClickListener {
            auth.signOut()
            Toast.makeText(context,"Signed Out",Toast.LENGTH_SHORT).show()
            findNavController().navigate(VerifyEmailFragmentDirections.actionVerifyEmailFragmentToSignInFragment(""))
        }

    }


    private fun btnCopyLinkClick(){
        binding.btnCopy.setOnClickListener{
            copyText(binding.txtLink.text.toString())
        }
    }

    private fun cardViewClickToCopy(){
        binding.cardViewCopyLink.setOnClickListener {
            copyText(binding.txtLink.text.toString())
        }
    }



    private fun copyText(text:String){
        val clipboard = requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("Copied Text",text)

        clipboard.setPrimaryClip(clip)
        Toast.makeText(context,"Link copied",Toast.LENGTH_SHORT).show()
    }
}