package com.oguzhanorhan.itunessearch.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oguzhanorhan.itunessearch.data.model.ITunesApiStatus
import com.oguzhanorhan.itunessearch.datasource.model.mapToDomain
import com.oguzhanorhan.itunessearch.domain.model.FilterItem
import com.oguzhanorhan.itunessearch.domain.model.ITunesItem
import com.oguzhanorhan.itunessearch.domain.usecase.RetrieveFilterItemsUseCase
import com.oguzhanorhan.itunessearch.domain.usecase.SearchItemsUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SearchVM constructor(
    private val searchItemsUseCase: SearchItemsUseCase,
    private val retrieveFilterItemsUseCase: RetrieveFilterItemsUseCase): ViewModel() {

    private val _status = MutableLiveData<ITunesApiStatus>()

    val status: LiveData<ITunesApiStatus>
        get() = _status


    private val _items = MutableLiveData<List<ITunesItem>>()

    val items: LiveData<List<ITunesItem>>
        get() = _items

    private val _filterItems = MutableLiveData<List<FilterItem>>()

    val filterItems: LiveData<List<FilterItem>>
        get() = _filterItems

    private val _navigateToSelectedItem = MutableLiveData<ITunesItem>()

    val navigateToSelectedItem: LiveData<ITunesItem>
        get() = _navigateToSelectedItem

    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getCategories()
    }

    private fun getCategories() {
        coroutineScope.launch {
            val listResult = retrieveFilterItemsUseCase.get()
            _filterItems.value = listResult
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun searchItems(text: String, category: FilterItem) {
        coroutineScope.launch {
            _status.value = ITunesApiStatus.LOADING
            val listResult = searchItemsUseCase.get(text, category)
            _status.value = listResult.status

            when(_status.value) {
                ITunesApiStatus.DONE -> {
                    val domainItems = ArrayList<ITunesItem>()
                    listResult.data?.forEach{
                        domainItems.add(it.mapToDomain())
                    }
                    _items.value = domainItems
                }

                ITunesApiStatus.ERROR ->_items.value = ArrayList()
            }
        }
    }

    fun displayItemDetails(item: ITunesItem) {
        _navigateToSelectedItem.value = item
    }

    fun displayItemDetailsComplete() {
        _navigateToSelectedItem.value = null
    }
}