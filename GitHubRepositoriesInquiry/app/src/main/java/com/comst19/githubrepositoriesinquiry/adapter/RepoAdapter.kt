package com.comst19.githubrepositoriesinquiry.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.comst19.githubrepositoriesinquiry.databinding.ItemRepoBinding
import com.comst19.githubrepositoriesinquiry.model.Repo

class RepoAdapter(private val onClick : (Repo) -> Unit) : ListAdapter<Repo,RepoAdapter.ViewHolder>(diffUtil) {

    inner class ViewHolder(private val viewBinding : ItemRepoBinding) : RecyclerView.ViewHolder(viewBinding.root){
        fun bind(item: Repo) {
            viewBinding.repoNameTextView.text = item.name
            viewBinding.descriptionTextView.text = item.description
            viewBinding.forkCountTextView.text = item.forkCount.toString()
            viewBinding.starCountTextView.text = item.starCount.toString()

            viewBinding.root.setOnClickListener {
                onClick(item)
            }

        }

    }

    companion object{
        val diffUtil = object :  DiffUtil.ItemCallback<Repo>(){
            override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemRepoBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}