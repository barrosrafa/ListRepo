package com.barros.listrepo.ui.home.adapter

import androidx.recyclerview.widget.DiffUtil
import com.barros.listrepo.model.Repository

internal object RepositoryDiff : DiffUtil.ItemCallback<Repository>() {
    override fun areItemsTheSame(
        oldItem: Repository,
        newItem: Repository
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: Repository,
        newItem: Repository
    ): Boolean {
        return oldItem == newItem
    }
}