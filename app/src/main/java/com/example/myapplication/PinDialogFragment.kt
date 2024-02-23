package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.databinding.FragmentPinDialogBinding


class PinDialogFragment : Fragment() {
    private lateinit var binding:FragmentPinDialogBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPinDialogBinding.inflate(inflater,container,false)
        return binding.root
    }

    private fun setEditBox():Boolean{
        if (binding.edtPin.text.length == 4){
            return true
        } else{
            return false
        }
    }

}