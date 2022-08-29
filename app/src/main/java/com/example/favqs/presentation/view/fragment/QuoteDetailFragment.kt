package com.example.favqs.presentation.view.fragment

import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.favqs.data.local.Images
import com.example.favqs.databinding.FragmentQuoteDetailBinding
import com.example.favqs.presentation.view.adapter.TagAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class QuoteDetailFragment : Fragment() {

    private var _binding: FragmentQuoteDetailBinding? = null
    private val binding get() = _binding!!

    val args: QuoteDetailFragmentArgs by navArgs()
    private val tagAdapter: TagAdapter by lazy {
        TagAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setUpTransition()
        _binding = FragmentQuoteDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
    }

    private fun setUpView() {
        with(binding) {
            imageView.setImageResource(Images.ImageList[args.image % 10])
            quoteDetails.text = args.quote.body
            authorName.text = args.quote.author
            downVoteCount.text = args.quote.downVotesCount.toString()
            upVoteCount.text = args.quote.upVotesCount.toString()
            favCount.text = args.quote.favoritesCount.toString()
            tagRecyclerView.adapter = tagAdapter
            tagRecyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
            tagAdapter.setData(args.quote.tags)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpTransition() {
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)

    }

}