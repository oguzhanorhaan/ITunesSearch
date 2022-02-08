package com.oguzhanorhan.itunessearch.presentation.search
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.oguzhanorhan.itunessearch.databinding.ListViewItemBinding
import com.oguzhanorhan.itunessearch.domain.model.ITunesItem

class ItemListAdapter(val onClickListener: OnClickListener) :
    ListAdapter<ITunesItem, ItemListAdapter.ListItemViewHolder>(DiffCallback) {

    class ListItemViewHolder(private var binding: ListViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ITunesItem) {
            binding.item = item
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<ITunesItem>() {
        override fun areItemsTheSame(oldItem: ITunesItem, newItem: ITunesItem): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: ITunesItem, newItem: ITunesItem): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListItemViewHolder {
        return ListItemViewHolder(ListViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(item)
        }
        holder.bind(item)
    }

    class OnClickListener(val clickListener: (item: ITunesItem) -> Unit) {
        fun onClick(item: ITunesItem) = clickListener(item)
    }
}
