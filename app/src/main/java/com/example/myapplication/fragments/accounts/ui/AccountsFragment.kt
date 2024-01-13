package com.example.myapplication.fragments.accounts.ui

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
import com.example.myapplication.fragments.accounts.adapter.AccountsRcyAdapter

class AccountsFragment : Fragment() {
    private lateinit var binding : FragmentAccountsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccountsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRcy ()
        setAdapter ()
        goHome()
    }


    private fun setRcy (){
        binding.rcyAccounts.layoutManager = GridLayoutManager(context,2)
    }

    private fun setAdapter (){
        val img = listOf<Int>(
            R.drawable.img,
            R.drawable.img,
            R.drawable.img,
            R.drawable.img,
            R.drawable.img,
            )

        val name = listOf<String>(
            "Martin",
            "John",
            "Bob",
            "Julia",
            "Elizabeth"
        )


        val accountsAdapter = AccountsRcyAdapter(img,name){
            Toast.makeText(context,it,Toast.LENGTH_SHORT).show()
        }
        binding.rcyAccounts.adapter = accountsAdapter

    }

    private fun goHome(){
        binding.imgEdit.setOnClickListener {
            findNavController().navigate(R.id.action_accounts_to_home)
        }
    }




}