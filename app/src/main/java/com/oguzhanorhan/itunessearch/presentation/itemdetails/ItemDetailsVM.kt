package com.oguzhanorhan.itunessearch.presentation.itemdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.oguzhanorhan.itunessearch.common.BaseVM
import com.oguzhanorhan.itunessearch.domain.model.ITunesItem

class ItemDetailsVM(
    private var item: ITunesItem
) : BaseVM() {
    private val _selectedItem = MutableLiveData<ITunesItem>()

    val selectedItem: LiveData<ITunesItem>
        get() = _selectedItem

    init {
        _selectedItem.value = item
    }
}
