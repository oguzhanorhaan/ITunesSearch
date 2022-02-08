package com.oguzhanorhan.itunessearch.presentation.itemdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.oguzhanorhan.itunessearch.databinding.FragmentItemDetailsBinding
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class ItemDetailsFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentItemDetailsBinding.inflate(inflater)
        binding.lifecycleOwner = this
        val item = ItemDetailsFragmentArgs.fromBundle(requireArguments()).selectedItem
        val vm: ItemDetailsVM by inject<ItemDetailsVM> { parametersOf(item) }
        binding.viewModel = vm
        return binding.root
    }
}