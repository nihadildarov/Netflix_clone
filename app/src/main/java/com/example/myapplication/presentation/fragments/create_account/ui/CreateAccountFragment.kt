package com.example.myapplication.presentation.fragments.create_account.ui

import android.graphics.Typeface
import android.os.Bundle
import android.text.InputType
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myapplication.databinding.FragmentCreateAccountBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException


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
        val email = navArgs.email


        getEmailValue(email)
        setEdtFocus()
        requestFocus()
        spannableTextClick()
        hideMenuItems()
        checkEmptyFields()

    }


    private fun createAccount(
        email: String,
        password: String
    ) {

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Log.i("CreateAccount", "Success")
                    Toast.makeText(context, "Registered Successfully!", Toast.LENGTH_LONG).show()
                    goToVerifyAccount()
                } else if (it.exception is FirebaseAuthUserCollisionException) {
                    binding.txtAccountExists.visibility = View.VISIBLE
                } else {
                    Log.e("CreateAccount", it.exception.toString())
                    Toast.makeText(context, it.exception.toString(), Toast.LENGTH_LONG).show()
                }
            }
    }


    private fun requestFocus() {
        binding.emailTil.requestFocus()
    }



    private fun spannableTextClick() {
        val message = binding.txtAccountExists.text.toString()

        val spannable = SpannableString(message)

        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                findNavController().navigate(
                    CreateAccountFragmentDirections.actionCreateAccountToSignIn(
                        binding.edtEmail.text.toString()
                    )
                )
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }
        }
        val startIndex = message.indexOf("Sign into that account")
        val endIndex = startIndex + "Sign into that account".length

        val whiteColor = resources.getColor(android.R.color.white, null)


        spannable.setSpan(clickableSpan, startIndex, endIndex, 0)
        spannable.setSpan(ForegroundColorSpan(whiteColor), startIndex, endIndex, 0)
        spannable.setSpan(StyleSpan(Typeface.BOLD), startIndex, endIndex, 0)


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
                    edtEmail.inputType == InputType.TYPE_TEXT_VARIATION_WEB_EMAIL_ADDRESS -> {
                        emailTil.error = "Not a valid email address"
                        Log.i("checkEmptyFields", emailTil.error.toString())
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
                        createAccount(edtEmail.text.toString(), edtpassword.text.toString())
                    }

                }


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


    private fun getEmailValue(email: String) {
        binding.edtEmail.setText(email)
    }

    private fun goToVerifyAccount(){
        findNavController().navigate(CreateAccountFragmentDirections.actionCreateAccountToVerifyEmail())
    }

}