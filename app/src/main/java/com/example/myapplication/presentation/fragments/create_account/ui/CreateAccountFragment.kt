package com.example.myapplication.presentation.fragments.create_account.ui

import android.os.Bundle
import android.text.InputType
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentCreateAccountBinding
import com.example.myapplication.presentation.fragments.signin.ui.SignInFragmentArgs
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.lang.Exception


class CreateAccountFragment : Fragment() {
    private lateinit var binding: FragmentCreateAccountBinding
    private lateinit var auth: FirebaseAuth
    private val navArgs by navArgs<CreateAccountFragmentArgs>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateAccountBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        auth.signOut()
        val email =  navArgs.email


        getEmailValue(email)
        setEdtFocus()
        requestFocus()
        spannableClickText()
        hideMenuItems()
        checkEmptyFields()
        goToAccounts()

    }


    private fun createAccount(
        email:String,
        password:String
    ) {

        auth.createUserWithEmailAndPassword(email.toString(), password.toString())
            .addOnSuccessListener {
                Log.i("CreateAccount", "Success")
                Toast.makeText(context, "Registered Successfully!", Toast.LENGTH_LONG).show()

            }
            .addOnFailureListener {
                Log.e("CreateAccount", it.localizedMessage!!)
                Toast.makeText(context, it.localizedMessage, Toast.LENGTH_LONG).show()
            }


    }


    private fun requestFocus() {
        binding.emailTil.requestFocus()
    }


    private fun spannableClickText() {
        val message = binding.txtAccountExists.text.toString()

        val spannable = SpannableString(message)

        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                findNavController().navigate(R.id.action_createAccount_to_signIn)
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }
        }
        val startIndex = message.indexOf("Sign into that account")
        val endIndex = startIndex + "Sign into that account".length

        spannable.setSpan(clickableSpan, startIndex, endIndex, 0)


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




        with(binding) {
            binding.btnCreateAccount.setOnClickListener {



                Log.i("CreateAccount", "clicked")


                when {

                    //1
                    edtEmail.text.isNullOrEmpty() -> {
                        emailTil.error = "Email is required!"
                        Log.i("checkEmptyFields", emailTil.error.toString())
                    }

                    //2
                    edtEmail.inputType == InputType.TYPE_TEXT_VARIATION_WEB_EMAIL_ADDRESS ->{
                        emailTil.error = "Not a valid email address"
                        Log.i("checkEmptyFields",emailTil.error.toString())
                    }

                    //3
                    edtpassword.text.isNullOrEmpty() -> {
                        emailTil.isErrorEnabled = false
                        passwordTil.error = "Password is required!"
                        Log.i("checkEmptyFields", passwordTil.error.toString())
                    }

                    //4
                    edtpassword.length() < 6 || edtpassword.length() > 60 -> {
                        passwordTil.error = "Password should be between 6 and 60 characters"
                        Log.i("checkEmptyFields", passwordTil.error.toString())
                    }


                    else -> {
                        Log.i("checkEmptyFields", "CASE: createAccount")
                        createAccount(edtEmail.text.toString(),edtpassword.text.toString())
                    }

                }

                showWarningIfAccExist()

            }
        }
    }


    private fun setEdtFocus() {
        with(binding) {
            edtEmail.setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    emailTil.error = null
                    emailTil.isErrorEnabled = false
                }
            }


            edtpassword.setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    passwordTil.error = null
                    passwordTil.isErrorEnabled = false
                }
            }
        }
    }


    private fun showWarningIfAccExist() {

        //not settled yet
        auth.pendingAuthResult?.result
        if (binding.edtEmail.text.toString() == "nihad") {
            binding.txtAccountExists.visibility = View.VISIBLE
        } else {
            binding.txtAccountExists.visibility = View.GONE
        }
    }


    private fun goToAccounts() {
        binding.txtTitleCreateAccount.setOnClickListener {
            findNavController().navigate(R.id.action_createAccounts_to_accounts)
        }
    }

    private fun getEmailValue(email: String){
        binding.edtEmail.setText(email)
    }
}