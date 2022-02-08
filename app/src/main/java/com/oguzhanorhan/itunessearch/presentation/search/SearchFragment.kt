package com.oguzhanorhan.itunessearch.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.oguzhanorhan.itunessearch.R
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
            viewModel.resetSearchParameters()
            handleSearchBox(binding.searchBar.query.toString())
        })

        binding.itemList.adapter = ItemListAdapter(ItemListAdapter.OnClickListener {
            viewModel.displayItemDetails(it)
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

        binding.itemList.addOnScrollListener(object : RecyclerView.OnScrollListener(){

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                if (!recyclerView.canScrollVertically(1) && dy > 0)
                {
                    selectedCategory?.let { selectedCategory ->
                        viewModel.searchItems(
                            binding.searchBar.query.toString(),
                            selectedCategory
                        )
                    }
                }
            }
        })

        viewModel.navigateToSelectedItem.observe(this.viewLifecycleOwner, Observer {item ->
            item?.let {
                Navigation.findNavController(binding.root).navigate(SearchFragmentDirections.actionSearchFragmentToItemDetailsFragment(it))
                viewModel.displayItemDetailsComplete()
            }
        })

        return binding.root
    }

    private fun handleSearchBox(text: String) {
        selectedCategory?.let { category ->
            if (text.length <= 2) {
                Toast.makeText(activity,context?.getString(R.string.enter_more_than_2_char) ?: "", Toast.LENGTH_LONG).show()
            } else {
                viewModel.searchItems(text, category)
            }
        } ?: Toast.makeText(activity, context?.getString(R.string.select_category) ?: "", Toast.LENGTH_LONG).show()
    }
}