package com.example.myapplication.presentation.fragments.signup.ui

import android.os.Bundle
import android.util.Log
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
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.auth
import com.google.firebase.auth.oAuthCredential

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
        auth = Firebase.auth

        btnXClick()
        btnGetStartedClick()


    }



    private fun isEmailRegistered(email:String,callBack: (Boolean)-> Unit){

        auth.createUserWithEmailAndPassword(email,"dummyPassword")
            .addOnCompleteListener{
                if (it.isSuccessful){
                    //Email is not registered
                    callBack(false)
                } else
                    if (it.exception is FirebaseAuthUserCollisionException){
                        //Email is already registered
                        callBack(true)
                    } else {
                        // Error occurred
                        callBack(false)
                        Log.e("isEmailRegistered",it.exception.toString())
                    }
            }
    }




    private fun checkEmailIfExist(email:String){
        isEmailRegistered(binding.edtEmail.text.toString()){
            if (it){

                findNavController().navigate(SignUpFragmentDirections.actionSignUpToSignIn(email))
            }else{
                auth.currentUser?.delete()
                findNavController().navigate(SignUpFragmentDirections.actionSignInMainToCreateAccount(email))
            }
        }

    }




    private fun btnXClick(){
        binding.btnClose.setOnClickListener{
            findNavController().popBackStack()

        }
    }

    private fun btnGetStartedClick(){
        binding.btnGetStarted.setOnClickListener {
            if (!binding.edtEmail.text.isNullOrEmpty()) {
                checkEmailIfExist(binding.edtEmail.text.toString())

            } else{
                binding.emailTil.error = "Email is required!"
            }
        }
    }

//
//    override fun onDestroy() {
//        super.onDestroy()
//        auth.currentUser?.delete()
//    }
//





}