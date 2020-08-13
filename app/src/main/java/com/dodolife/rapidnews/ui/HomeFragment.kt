package com.dodolife.rapidnews.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.dodolife.rapidnews.R
import com.dodolife.rapidnews.modules.BaseFragment
import com.dodolife.rapidnews.ui.adapter.BannerAdapter
import com.dodolife.rapidnews.ui.adapter.NewsBannerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_home.*
import java.time.LocalDateTime
import kotlin.collections.ArrayList
import com.dodolife.rapidnews.utils.observe

class HomeFragment : BaseFragment(R.layout.fragment_home) {

    private val bannerAdapter by lazy (LazyThreadSafetyMode.NONE) {
        BannerAdapter()
    }
    private val newsBannerAdapter by lazy (LazyThreadSafetyMode.NONE) {
        NewsBannerAdapter()
    }

    private val viewModel by viewModels<MainViewModel>()

    private val arrayList = ArrayList<Int>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val current = LocalDateTime.now()

        swipeRefresh.setOnRefreshListener {
            viewModel.refreshNews(current.toString())

        }
        viewModel.refreshNews(current.toString())

        arrayList.add(R.drawable.banner_dummy)
        arrayList.add(R.drawable.banner_dummy)
        arrayList.add(R.drawable.banner_dummy)
        arrayList.add(R.drawable.banner_dummy)

        initPromoViewPager()

        initNewsViewPager()

        observe(viewModel.newsData) {
            newsBannerAdapter.items = it?: emptyList()
        }

        observe(viewModel.stateSet) {
            swipeRefresh.isRefreshing = it == Lifecycle.State.STARTED
            when (it) {
                Lifecycle.State.DESTROYED -> Toast.makeText(requireContext(), "Something Wrong", Toast.LENGTH_SHORT).show()
                Lifecycle.State.CREATED -> Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initNewsViewPager() {
        viewPagerNews.adapter = newsBannerAdapter
        viewPagerNews.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        TabLayoutMediator(indicatorNews, viewPagerNews) { tab: TabLayout.Tab, _ ->
            viewPagerNews.setCurrentItem(tab.position, true)
        }.attach()
        viewPagerNews.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToNewsListFragment())
        }
    }

    private fun initPromoViewPager() {
        bannerAdapter.items = arrayList
        viewPager.adapter = bannerAdapter
        viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        TabLayoutMediator(indicator, viewPager) { tab: TabLayout.Tab, _ ->
            viewPager.setCurrentItem(tab.position, true)
        }.attach()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        arrayList.clear()
    }
}