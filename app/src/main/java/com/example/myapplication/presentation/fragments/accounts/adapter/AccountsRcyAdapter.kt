package com.example.myapplication.presentation.fragments.accounts.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.RcyItemAccountsBinding
import com.example.myapplication.presentation.fragments.accounts.MembersProfiles

class AccountsRcyAdapter(
    private val members:List<MembersProfiles>,
    private val onClick: (String) -> Unit
) : RecyclerView.Adapter<AccountsRcyAdapter.AccountsRcyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountsRcyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RcyItemAccountsBinding.inflate(inflater, parent, false)

        return AccountsRcyViewHolder(binding){onClick(members[it].name)}
    }

    override fun getItemCount(): Int {
        return members.size
    }

    override fun onBindViewHolder(holder: AccountsRcyViewHolder, position: Int) {
        val currentItem = members[position]
        holder.bind(currentItem)

    }

    inner class AccountsRcyViewHolder(
        private val binding: RcyItemAccountsBinding,
        clickAtPosition: (Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(members:MembersProfiles) {
            binding.imgAccounts.setImageResource(members.profilePoster)
            binding.txtAccounts.text = members.name
        }

        init {
            itemView.setOnClickListener {
                clickAtPosition(adapterPosition)
            }
        }
    }


}