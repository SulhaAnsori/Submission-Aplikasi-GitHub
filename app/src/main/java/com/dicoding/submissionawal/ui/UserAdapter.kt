package com.dicoding.submissionawal.ui

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.submissionawal.data.response.ItemsItem
import com.dicoding.submissionawal.databinding.ItemUserBinding
import com.dicoding.submissionawal.ui.detail.DetailUserActivity

class UserAdapter : ListAdapter<ItemsItem, UserAdapter.MyViewHolder>(DIFF_CALLBACK) {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.d("UserAdapter", "Binding position: $position")
        val login = getItem(position)
        holder.bind(login)


        holder.itemView.setOnClickListener {
            val intentDetail = Intent(holder.itemView.context, DetailUserActivity::class.java)
            intentDetail.putExtra(DetailUserActivity.USERNAME, login.login)
            holder.itemView.context.startActivity(intentDetail)
        }
    }

   inner class MyViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ItemsItem) {
            binding.tvUsername.text = "${item.login}"
            Glide.with(itemView.context)
                .load(item.avatarUrl)
                .centerCrop()
                .optionalCircleCrop()
                .into(binding.ivUser)
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ItemsItem>() {
            override fun areItemsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
                return oldItem == newItem
            }
        }
    }

    interface OnItemClickCallback{
        fun onItemClicked(data: ItemsItem)
    }
}

