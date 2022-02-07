package com.oguzhanorhan.itunessearch.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.oguzhanorhan.itunessearch.databinding.FragmentSearchBinding
import com.oguzhanorhan.itunessearch.domain.model.FilterItem
import com.oguzhanorhan.itunessearch.injectFeature
import com.thekhaeng.recyclerviewmargin.LayoutMarginDecoration
import org.koin.android.ext.android.inject

class SearchFragment : Fragment()  {

    private val viewModel by inject<SearchVM>()
    private lateinit var binding: FragmentSearchBinding
    private var selectedCategory: FilterItem? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?  {
        super.onCreate(savedInstanceState)

        binding = FragmentSearchBinding.inflate(inflater)

        injectFeature()

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        binding.filterList.adapter = FilterListAdapter(FilterListAdapter.OnClickListener {
            selectedCategory = it
            handleSearchBox(binding.searchBar.query.toString())
        })

        binding.itemList.adapter = ItemListAdapter(ItemListAdapter.OnClickListener {
            // viewModel.displayItemDetails(it)
        })

        binding.itemList.addItemDecoration(LayoutMarginDecoration( 1, 15 ))
        binding.filterList.addItemDecoration(LayoutMarginDecoration( 1, 15 ));

        binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                handleSearchBox(query)
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })

        return binding.root
    }

    private fun handleSearchBox(text: String) {
        selectedCategory?.let { category ->
            if (text.length <= 2) {
                Toast.makeText(activity, "Please enter more than 2 character", Toast.LENGTH_LONG).show()
            } else {
                viewModel.searchItems(text, category)
            }
        } ?: Toast.makeText(activity, "Please select a category", Toast.LENGTH_LONG).show()
    }
}