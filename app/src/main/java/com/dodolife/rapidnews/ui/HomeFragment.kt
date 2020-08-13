package com.dodolife.rapidnews.ui

import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.dodolife.rapidnews.R
import com.dodolife.rapidnews.modules.BaseFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment(R.layout.fragment_home) {

    private val bannerAdapter by lazy (LazyThreadSafetyMode.NONE) {
        BannerAdapter()
    }

    private val arrayList = ArrayList<Int>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arrayList.add(R.drawable.banner_dummy)
        arrayList.add(R.drawable.banner_dummy)
        arrayList.add(R.drawable.banner_dummy)
        arrayList.add(R.drawable.banner_dummy)

        initViewPager()

    }

    private fun initViewPager() {
        bannerAdapter.items = arrayList
        viewPager.adapter = bannerAdapter
        viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        TabLayoutMediator(indicator, viewPager) { tab: TabLayout.Tab, _ ->
            viewPager.setCurrentItem(tab.position, true)
        }.attach()
    }
}