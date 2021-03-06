package com.oguzhanorhan.itunessearch.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.oguzhanorhan.itunessearch.common.BaseVM
import com.oguzhanorhan.itunessearch.data.model.ITunesApiStatus
import com.oguzhanorhan.itunessearch.datasource.model.mapToDomain
import com.oguzhanorhan.itunessearch.domain.model.FilterItem
import com.oguzhanorhan.itunessearch.domain.model.ITunesItem
import com.oguzhanorhan.itunessearch.domain.usecase.RetrieveFilterItemsUseCase
import com.oguzhanorhan.itunessearch.domain.usecase.SearchItemsUseCase
import kotlinx.coroutines.launch

class SearchVM constructor(
    private val searchItemsUseCase: SearchItemsUseCase,
    private val retrieveFilterItemsUseCase: RetrieveFilterItemsUseCase
) : BaseVM() {
    private var currentOffSet = 0
    private var paginationLimit = 20

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

    init {
        getCategories()
    }

    private fun getCategories() {
        launch {
            val listResult = retrieveFilterItemsUseCase.get()
            _filterItems.value = listResult
        }
    }

    var domainItems = ArrayList<ITunesItem>()

    fun searchItems(text: String, category: FilterItem) {

        if (currentOffSet < domainItems.size && currentOffSet > 0) {
            if (currentOffSet + paginationLimit < domainItems.size) {
                currentOffSet += paginationLimit
            } else {
                currentOffSet = domainItems.size
            }
            _items.value = ArrayList(domainItems.subList(0, currentOffSet))
        } else {
            launch {
                _status.value = ITunesApiStatus.LOADING
                val listResult = searchItemsUseCase.get(text, category)
                _status.value = listResult.status
                domainItems.clear()

                when (_status.value) {
                    ITunesApiStatus.DONE -> {

                        listResult.data?.forEach {
                            domainItems.add(it.mapToDomain())
                        }
                        if (paginationLimit < domainItems.size) {
                            _items.value = ArrayList(domainItems.subList(0, paginationLimit))
                            currentOffSet = paginationLimit
                        } else {
                            _items.value = ArrayList(domainItems.subList(0, domainItems.size))
                            currentOffSet = domainItems.size
                        }
                    }

                    ITunesApiStatus.ERROR -> _items.value = ArrayList()
                }
            }
        }
    }

    fun displayItemDetails(item: ITunesItem) {
        _navigateToSelectedItem.value = item
    }

    fun displayItemDetailsComplete() {
        _navigateToSelectedItem.value = null
    }

    fun resetSearchParameters() {
        currentOffSet = 0
        domainItems.clear()
    }
}
