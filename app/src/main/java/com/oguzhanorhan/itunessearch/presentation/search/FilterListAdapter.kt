package com.oguzhanorhan.itunessearch.presentation.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.oguzhanorhan.itunessearch.databinding.ItemFilterBinding
import com.oguzhanorhan.itunessearch.domain.model.FilterItem

class FilterListAdapter(val onClickListener: OnClickListener) :
    ListAdapter<FilterItem, FilterListAdapter.FilterItemViewHolder>(DiffCallback) {

    class FilterItemViewHolder(private var binding: ItemFilterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: FilterItem) {
            binding.item = item
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<FilterItem>() {
        override fun areItemsTheSame(oldItem: FilterItem, newItem: FilterItem): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: FilterItem, newItem: FilterItem): Boolean {
            return oldItem.type == newItem.type
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FilterItemViewHolder {
        return FilterItemViewHolder(ItemFilterBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: FilterItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(item)
        }
        holder.bind(item)
    }

    class OnClickListener(val clickListener: (item: FilterItem) -> Unit) {
        fun onClick(item: FilterItem) = clickListener(item)
    }
}
