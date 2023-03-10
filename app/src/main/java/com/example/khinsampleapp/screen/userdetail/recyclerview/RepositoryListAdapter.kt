package com.example.khinsampleapp.screen.userdetail.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.khinsampleapp.databinding.ViewUserDetailRepositoryEntryBinding
import com.example.khinsampleapp.repository.network.model.RepositoryInfo
import com.example.khinsampleapp.common.textOrGone

class RepositoryListAdapter :
    RecyclerView.Adapter<RepositoryListAdapter.RepositoryListViewHolder>() {

    private val repositoryList: MutableList<RepositoryInfo> = mutableListOf()

    var onRepositoryClickListener: ((RepositoryInfo) -> Unit)? = null

    fun insertToList(repositories: List<RepositoryInfo>) {
        val currentUserCount = repositoryList.size
        repositoryList.addAll(repositories)
        notifyItemRangeInserted(currentUserCount, repositories.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        RepositoryListViewHolder(
            ViewUserDetailRepositoryEntryBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ),
                parent,
                false
            ),
            onRepositoryClickListener
        )

    override fun onBindViewHolder(holder: RepositoryListViewHolder, position: Int) {
        holder.update(repositoryList[position])
    }

    override fun getItemCount() = repositoryList.size

    class RepositoryListViewHolder(
        private val binding: ViewUserDetailRepositoryEntryBinding,
        private val onRepositoryClickListener: ((RepositoryInfo) -> Unit)?
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun update(repository: RepositoryInfo) {
            with(binding) {
                userDetailRepositoryEntryName.textOrGone(repository.name)
                userDetailRepositoryEntryDescription.textOrGone(repository.description)
                userDetailRepositoryEntryUrl.textOrGone(repository.url)
                userDetailRepositoryEntryLanguage.textOrGone(repository.language)

                root.setOnClickListener {
                    onRepositoryClickListener?.invoke(repository)
                }
            }
        }
    }
}