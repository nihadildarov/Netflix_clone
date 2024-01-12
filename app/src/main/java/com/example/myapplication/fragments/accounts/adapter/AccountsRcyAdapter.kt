package com.example.myapplication.fragments.accounts.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.RcyItemAccountsBinding

class AccountsRcyAdapter(
    private val profileImg:List<Int>,
    private val profileName:List<String>
):RecyclerView.Adapter<AccountsRcyAdapter.AccountsRcyViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountsRcyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RcyItemAccountsBinding.inflate(inflater,parent,false)
        return AccountsRcyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return profileImg.size
    }

    override fun onBindViewHolder(holder: AccountsRcyViewHolder, position: Int) {
        val curProfileImg = profileImg[position]
        val curProfileName = profileName[position]
        holder.bind(curProfileImg,curProfileName)
    }

    inner class AccountsRcyViewHolder (private val binding : RcyItemAccountsBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(img:Int,name:String){
            binding.imgAccounts.setImageResource(img)
            binding.txtAccounts.text = name
        }
    }

}