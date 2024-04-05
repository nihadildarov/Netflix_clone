package com.example.myapplication.presentation.fragments.accounts.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentAccountsBinding
import com.example.myapplication.data.remote.models.MembersProfiles
import com.example.myapplication.presentation.fragments.accounts.adapter.AccountsRcyAdapter
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AccountsFragment : Fragment() {
    private lateinit var binding: FragmentAccountsBinding
    private lateinit var fireStore: FirebaseFirestore
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccountsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fireStore = Firebase.firestore


        fireStore.collection("members").add(
            hashMapOf(
                "name" to "Murad",
                "password" to "mumumu123",
                "avatarPath" to "https://www.google.com/url?sa=i&url=https%3A%2F%2Fen.wikipedia.org%2Fwiki%2FCar&psig=AOvVaw2_eiaWnvfQaRndIgXRXjhA&ust=1710791946059000&source=images&cd=vfe&opi=89978449&ved=0CBMQjRxqFwoTCJjBleOK_IQDFQAAAAAdAAAAABAE"
            )
        ).addOnSuccessListener {
            Toast.makeText(context,"Success",Toast.LENGTH_LONG).show()
        }.addOnFailureListener{
            Toast.makeText(context,it.localizedMessage,Toast.LENGTH_LONG).show()
        }

        setRcy()
        setAdapter()
        goHome()
    }


    private fun setRcy() {
        binding.rcyAccounts.layoutManager = GridLayoutManager(context, 2)
    }


    private fun popUpDialog(){
    PinDialogFragment().show(requireActivity().supportFragmentManager,"PinDialogFragment")
    }

    private fun setAdapter() {
        val members = listOf(
            MembersProfiles(R.drawable.netflix_avatar1, "Martin"),
            MembersProfiles(R.drawable.netflix_avatar2, "John"),
            MembersProfiles(R.drawable.netflix_avatar3, "Bob"),
            MembersProfiles(R.drawable.netflix_avatar4, "Julia"),
            MembersProfiles(R.drawable.netflix_avatar5, "Elizabeth"),
        )
        val accountsAdapter = AccountsRcyAdapter {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            popUpDialog()
        }
        accountsAdapter.submitList(members)
        binding.rcyAccounts.adapter = accountsAdapter
    }

    private fun goHome() {
        binding.imgEdit.setOnClickListener {
            findNavController().navigate(R.id.action_accounts_to_home)
        }
    }
}