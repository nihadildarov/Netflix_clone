package com.example.myapplication.presentation.fragments.accounts.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.RcyItemAccountsBinding
import com.example.myapplication.data.remote.models.MembersProfiles

class AccountsRcyAdapter(

    private val onClick: (String) -> Unit
) : RecyclerView.Adapter<AccountsRcyAdapter.AccountsRcyViewHolder>() {


    var isForSettingPage = false
    private val itemCallBack = object : DiffUtil.ItemCallback<MembersProfiles>(){
        override fun areItemsTheSame(oldItem: MembersProfiles, newItem: MembersProfiles): Boolean {
            return oldItem.name==newItem.name
        }

        override fun areContentsTheSame(
            oldItem: MembersProfiles,
            newItem: MembersProfiles
        ): Boolean {
            return oldItem==newItem
        }

    }

    private val diffUtil = AsyncListDiffer(this,itemCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountsRcyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RcyItemAccountsBinding.inflate(inflater, parent, false)

        return AccountsRcyViewHolder(binding){onClick(diffUtil.currentList[it].name)}
    }

    override fun getItemCount(): Int {
        return diffUtil.currentList.size
    }

    override fun onBindViewHolder(holder: AccountsRcyViewHolder, position: Int) {
        val currentItem = diffUtil.currentList[position]
        holder.bind(isForSettingPage,currentItem)


    }

    inner class AccountsRcyViewHolder(
        private val binding: RcyItemAccountsBinding,
        clickAtPosition: (Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(isForSettingPage:Boolean,members: MembersProfiles) {
            binding.imgAccounts.setImageResource(members.profilePoster)
            binding.txtAccounts.text = members.name

            if (isForSettingPage){
                binding.cardViewPoster.strokeWidth = 1
            } else {
                binding.cardViewPoster.strokeWidth = 0
            }
        }

        init {
            itemView.setOnClickListener {
                clickAtPosition(adapterPosition)
            }
        }





    }
    fun submitList(members: List<MembersProfiles>){
        diffUtil.submitList(members)
    }



    fun setForSettingPage(){
        isForSettingPage = true
    }


}