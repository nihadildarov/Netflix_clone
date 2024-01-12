package com.example.myapplication.fragments.create_account.ui

import android.os.Bundle
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentCreateAccountBinding


class CreateAccountFragment : Fragment() {
    private lateinit var binding: FragmentCreateAccountBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateAccountBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestFocus()
        spannableClickText()
        hideMenuItems()
        checkEmptyFields()
        goToAccounts()
    }


    private fun requestFocus(){
        binding.emailTil.requestFocus()
    }

    private fun spannableClickText(){
        val message = binding.txtAccountExists.text.toString()

        val spannable = SpannableString(message)

        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                findNavController().navigate(R.id.action_createAccount_to_signIn)
                Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
            }
        }


        val startIndex = message.indexOf("Sign into that account")
        val endIndex = startIndex + "Sign into that account".length

        spannable.setSpan(clickableSpan,startIndex,endIndex,0)


        binding.txtAccountExists.text = spannable
        binding.txtAccountExists.movementMethod = LinkMovementMethod.getInstance()
    }




    private fun hideMenuItems() {
        binding.toolbar.menu.getItem(0).setVisible(false)
        binding.toolbar.menu.getItem(2).setVisible(false)
        binding.toolbar.menu.getItem(1).setShowAsAction(1)
        binding.toolbar.menu.getItem(3).setShowAsAction(1)


    }


    private fun checkEmptyFields() {


        binding.btnCreateAccount.setOnClickListener {
            if (binding.edtEmail.text.isNullOrEmpty()) {
                binding.emailTil.error = "Email is required!"
            } else {
                // your code here if everything is okay
            }

            if (binding.edtpassword.text.isNullOrEmpty()) {
                binding.passwordTil.error = "Password is required!"
            } else if(binding.edtpassword.length() < 6 || binding.edtpassword.length() > 60){
                binding.passwordTil.error = "Password should be between 6 and 60 characters"
            } else {
                // your code here if everything is okay
            }

            showIfAccExist()
        }

        binding.edtEmail.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.emailTil.error = null
                binding.emailTil.isErrorEnabled = false
            }
        }

        binding.edtpassword.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.passwordTil.error = null
                binding.passwordTil.isErrorEnabled = false
            }
        }

    }


    private fun showIfAccExist(){
        if(binding.edtEmail.text.toString() == "nihad") {
            binding.txtAccountExists.visibility = View.VISIBLE
        } else {
            binding.txtAccountExists.visibility = View.GONE
        }
    }


    private fun goToAccounts(){
        binding.txtTitleCreateAccount.setOnClickListener {
            findNavController().navigate(R.id.action_createAccounts_to_accounts)
        }
    }

}