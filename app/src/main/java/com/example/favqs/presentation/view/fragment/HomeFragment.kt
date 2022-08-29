package com.example.favqs.presentation.view.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.favqs.databinding.FragmentHomeBinding
import com.example.favqs.domain.model.QuoteData
import com.example.favqs.domain.model.Resource
import com.example.favqs.presentation.view.adapter.QuoteRecyclerview
import com.example.favqs.presentation.view.adapter.OnQuoteClickInterface
import com.example.favqs.presentation.view.adapter.OnTagClickListener
import com.example.favqs.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), OnQuoteClickInterface, OnTagClickListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val mainViewModel: MainViewModel by viewModels()
    private val quoteAdapter: QuoteRecyclerview by lazy {
        QuoteRecyclerview(this, this)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel.getQuoteList()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setView()
        onSearchRequest()
        activatePullToRefresh()
        closeAppOnBackPress()
    }

    private fun setView() {
        binding.quoteListView.layoutManager = LinearLayoutManager(requireContext())
        binding.quoteListView.adapter = quoteAdapter
        mainViewModel.quoteListData.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    binding.quoteProgressBar.visibility = View.VISIBLE
                }
                is Resource.Error -> {
                    binding.quoteProgressBar.visibility = View.INVISIBLE
                    Toast.makeText(requireContext(), "${resource.messages}", Toast.LENGTH_LONG)
                        .show()
                }
                is Resource.Success -> {
                    binding.quoteProgressBar.visibility = View.INVISIBLE
                    val list = resource.data?.quotes?.filter {
                        it.id != 0
                    }
                    if (list.isNullOrEmpty()) {
                        binding.noQuoteAvailable.visibility = View.VISIBLE
                        binding.quoteListView.visibility = View.INVISIBLE

                    } else {
                        binding.quoteListView.visibility = View.VISIBLE
                        binding.noQuoteAvailable.visibility = View.INVISIBLE
                        quoteAdapter.setData(list)
                    }

                }

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onSearchRequest() {
        with(binding) {
            searchQuote.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(p0: String?): Boolean {
                    p0?.let {
                        if (p0.isNotBlank()) {
                            mainViewModel.getQuoteListBySearch(p0)
                        }
                    }
                    return true
                }
            })
        }
    }


    override fun onQuoteClick(quote: QuoteData, imagePosition: Int) {
        val action =
            HomeFragmentDirections.actionHomeFragmentToQuoteDetailFragment(imagePosition, quote)
        findNavController().navigate(action)
    }

    override fun onTagClick(tag: String) {
        mainViewModel.getQuoteListByTag(tag)
    }

    private fun activatePullToRefresh() {
        binding.pullToRefresh.setOnRefreshListener {
            mainViewModel.getQuoteList()
            binding.pullToRefresh.isRefreshing = false
        }
    }

    fun closeAppOnBackPress(){
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            requireActivity().finish()
        }
    }
}