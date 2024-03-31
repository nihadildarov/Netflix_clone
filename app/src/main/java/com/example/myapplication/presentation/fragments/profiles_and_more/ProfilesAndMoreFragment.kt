package com.example.myapplication.presentation.fragments.profiles_and_more

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentProfilesAndMoreBinding
import com.example.myapplication.presentation.fragments.accounts.MembersProfiles
import com.example.myapplication.presentation.fragments.accounts.adapter.AccountsRcyAdapter

class ProfilesAndMoreFragment : Fragment() {

    private lateinit var binding: FragmentProfilesAndMoreBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfilesAndMoreBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRcy()
        setBtnBack()
        btnManageProfile()
    }



    private fun setRcy(){

        val members = listOf(
            MembersProfiles(R.drawable.netflix_avatar1, "Martin"),
            MembersProfiles(R.drawable.netflix_avatar2, "John"),
            MembersProfiles(R.drawable.netflix_avatar3, "Bob"),
            MembersProfiles(R.drawable.netflix_avatar4, "Julia"),
            MembersProfiles(R.drawable.netflix_avatar5, "Elizabeth"),
        )

        binding.rcyMembers.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)

        val accountsAdapter = AccountsRcyAdapter {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
        accountsAdapter.setForSettingPage()
        accountsAdapter.submitList(members)

        binding.rcyMembers.adapter = accountsAdapter
    }


    private fun setBtnBack(){
        binding.imgBack.setOnClickListener{
            Toast.makeText(context,"back",Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
        }
    }


    private fun btnManageProfile(){
        binding.btnManageProfiles.setOnClickListener {
            findNavController().navigate(ProfilesAndMoreFragmentDirections.actionProfilesAndMoreFragmentToAccountSettingsFragment())
        }
    }
}