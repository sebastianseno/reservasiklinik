package com.dodolife.rapidnews.modules

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class BaseFragment(@LayoutRes contentLayoutId: Int) : Fragment(contentLayoutId)