package com.oguzhanorhan.itunessearch

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.oguzhanorhan.itunessearch.data.model.ITunesApiStatus
import com.oguzhanorhan.itunessearch.domain.model.FilterItem
import com.oguzhanorhan.itunessearch.domain.model.ITunesItem
import com.oguzhanorhan.itunessearch.presentation.search.FilterListAdapter
import com.oguzhanorhan.itunessearch.presentation.search.ItemListAdapter

@BindingAdapter("itunesListData")
fun bindItunesItemsRecyclerView(recyclerView: RecyclerView, data: List<ITunesItem>?) {
    val adapter = recyclerView.adapter as ItemListAdapter
    adapter.submitList(data)
}

@BindingAdapter("filterListData")
fun bindFilterRecyclerView(recyclerView: RecyclerView, data: List<FilterItem>?) {
    val adapter = recyclerView.adapter as FilterListAdapter
    adapter.submitList(data)
}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            )
            .into(imgView)
    }
}

@BindingAdapter("apiStatus")
fun bindStatus(statusImageView: ImageView, status: ITunesApiStatus?) {
    when (status) {
        ITunesApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        ITunesApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        ITunesApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}
