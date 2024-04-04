package com.example.myapplication.presentation.fragments.accounts.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentPinDialogBinding


class PinDialogFragment : DialogFragment() {
    private lateinit var binding: FragmentPinDialogBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPinDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnOkClicked()
        btnCancelClicked()
        checkPinLength()
    }

    private fun checkPinLength(){
        binding.edtPin.addTextChangedListener {
            if (it?.length == 4){
                dismiss()
                findNavController().navigate(AccountsFragmentDirections.actionAccountsToHome())
            }
        }
    }

    private fun btnOkClicked() {
        binding.txtOk.setOnClickListener {
            if (binding.edtPin.text.length == 4) {
                dismiss()
                findNavController().navigate(AccountsFragmentDirections.actionAccountsToHome())
            } else {
                Toast.makeText(context, "Incompletec pin. Pin must be 4 digit", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    private fun btnCancelClicked() {
        binding.txtCancel.setOnClickListener {
            dismiss()
        }
    }
}