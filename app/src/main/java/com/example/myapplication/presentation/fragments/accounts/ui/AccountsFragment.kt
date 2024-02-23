package com.example.myapplication.presentation.fragments.accounts.ui

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentAccountsBinding
import com.example.myapplication.presentation.fragments.accounts.MembersProfiles
import com.example.myapplication.presentation.fragments.accounts.adapter.AccountsRcyAdapter

class AccountsFragment : Fragment() {
    private lateinit var binding: FragmentAccountsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccountsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRcy()
        setAdapter()
        goHome()
    }


    private fun setRcy() {
        binding.rcyAccounts.layoutManager = GridLayoutManager(context, 2)
    }



    private fun popUpDialog():Dialog{
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Enter Pin").setPositiveButton("OK"){
            dialog,_->
            Toast.makeText(context,"Okay clicked",Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }.setNegativeButton("Cancel"){
            dialog,_->
            Toast.makeText(context,"Cancel clicked",Toast.LENGTH_SHORT).show()
            dialog.cancel()
        }.setView(R.layout.fragment_pin_dialog)

        return builder.create()
    }

    private fun setAdapter() {
        val members = listOf<MembersProfiles>(
            MembersProfiles(R.drawable.netflix_avatar1,"Martin"),
            MembersProfiles(R.drawable.netflix_avatar2,"John"),
            MembersProfiles(R.drawable.netflix_avatar3,"Bob"),
            MembersProfiles(R.drawable.netflix_avatar4,"Julia"),
            MembersProfiles(R.drawable.netflix_avatar5,"Elizabeth"),
        )




        val accountsAdapter = AccountsRcyAdapter(members) {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            popUpDialog().show()
        }
        binding.rcyAccounts.adapter = accountsAdapter

    }



    private fun goHome() {
        binding.imgEdit.setOnClickListener {
            findNavController().navigate(R.id.action_accounts_to_home)
        }
    }



}