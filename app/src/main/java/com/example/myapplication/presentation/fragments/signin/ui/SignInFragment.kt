package com.example.myapplication.presentation.fragments.signin.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myapplication.databinding.FragmentSignInBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class SignInFragment : Fragment() {
    private lateinit var binding: FragmentSignInBinding
    private lateinit var auth: FirebaseAuth
    private val navArgs by navArgs<SignInFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignInBinding.inflate(inflater, container, false)
        auth = Firebase.auth
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val email = navArgs.email
        setEmailFromRegisterPage(email)
        btnNewUserClick()
        btnBack()
        btnSignInClick()
    }




    private fun btnNewUserClick() {
        binding.txtSignUpNow.setOnClickListener {
            findNavController().popBackStack()
        }
    }


    private fun btnBack() {
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }


    private fun signIn(email: String, password: String){

            auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    Log.i("signIn", "signed in successfully")
                    findNavController().navigate(SignInFragmentDirections.actionSignInToAccounts())

                    Toast.makeText(context,"Signed In successfully",Toast.LENGTH_SHORT).show()
                }.addOnCanceledListener {
                    Log.i("signIn", "sign in cancelled successfully")
                    Toast.makeText(context,"Sign in canceled",Toast.LENGTH_SHORT).show()
                }.addOnFailureListener {
                    Log.i("signIn", it.message.toString())
                    Toast.makeText(context,it.localizedMessage,Toast.LENGTH_SHORT).show()
                }.addOnCompleteListener {
                    Log.i("signIn", it.exception.toString())
                    Toast.makeText(context,it.exception.toString(),Toast.LENGTH_SHORT).show()
                }
    }





    private fun setEmailFromRegisterPage(email:String){
        binding.edtEmail.setText(email)
    }


    private fun btnSignInClick(){
        binding.btnSignIn.setOnClickListener {
            checkFields()
        }
    }



    private fun checkFields(){

        with(binding){
            when{
                edtEmail.text.isNullOrEmpty() -> {}
                edtPassword.text.isNullOrEmpty() -> {}
                else ->{
                    signIn(edtEmail.text.toString(),edtPassword.text.toString())
                }
            }
        }
    }




}

