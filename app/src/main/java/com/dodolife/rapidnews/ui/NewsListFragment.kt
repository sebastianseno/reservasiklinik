package com.dodolife.rapidnews.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.dodolife.rapidnews.R
import com.dodolife.rapidnews.modules.BaseFragment
import com.dodolife.rapidnews.ui.adapter.NewsRecyclerViewAdapter
import com.dodolife.rapidnews.utils.observe
import kotlinx.android.synthetic.main.fragment_news_list.*
import java.time.LocalDateTime

class NewsListFragment : BaseFragment(R.layout.fragment_news_list) {

    private val viewModel by viewModels<MainViewModel>()

    private val adapter by lazy (LazyThreadSafetyMode.NONE) {
        NewsRecyclerViewAdapter(::onClick)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val current = LocalDateTime.now()

        swipeRefresh.setOnRefreshListener {
            viewModel.refreshNews(current.toString())

        }

        viewModel.refreshNews(current.toString())

        recycler.adapter = adapter

        observe(viewModel.newsData) {
            adapter.items = it?: emptyList()
        }

        observe(viewModel.stateSet) {
            swipeRefresh.isRefreshing = it == Lifecycle.State.STARTED
            when (it) {
                Lifecycle.State.DESTROYED -> Toast.makeText(requireContext(), "Something Wrong", Toast.LENGTH_SHORT).show()
                Lifecycle.State.CREATED -> Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
            }
        }

    }


    private fun onClick(s: String) {
        findNavController().navigate(
            NewsListFragmentDirections.actionHomeFragmentToWebView(s)
        )
    }
}

