package com.dodolife.rapidnews.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dodolife.rapidnews.BuildConfig
import com.dodolife.rapidnews.network.NewsResponse
import com.dodolife.rapidnews.network.NewsServices
import kotlinx.coroutines.launch
import java.util.*

class MainViewModel @ViewModelInject constructor(
    private val services: NewsServices
): ViewModel() {

    val newsData = MutableLiveData<NewsResponse>()

    fun refreshNews(date: String) {
        viewModelScope.launch {
            try {
              val response = services.getNewsEverything(
                    "Health", date, "publishedAt", BuildConfig.API_KEY
                )
                newsData.value = response
            } catch (error: Exception) {

            }
        }
    }
}