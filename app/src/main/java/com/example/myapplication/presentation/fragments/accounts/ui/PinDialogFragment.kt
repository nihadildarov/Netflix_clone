package com.example.myapplication.presentation.fragments.accounts.ui

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
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


    override fun onCreate(savedInstanceState: Bundle?) {
        setStyle(DialogFragment.STYLE_NORMAL,R.style.TransparentDialogTheme)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        return super.onCreateDialog(savedInstanceState)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnOkClicked()
        btnCancelClicked()
        checkPinLength()

    }

    private fun startVibration(){
        val shakeAnim = AnimationUtils.loadAnimation(requireContext(),R.anim.bouncing_anim)
        view?.startAnimation(shakeAnim)

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
                startVibration()
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