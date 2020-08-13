package com.dodolife.rapidnews.ui

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dodolife.rapidnews.BuildConfig
import com.dodolife.rapidnews.network.Article
import com.dodolife.rapidnews.network.NewsServices
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    private val services: NewsServices
): ViewModel() {

    val newsData = MutableLiveData<List<Article>>()

    val stateSet = MutableLiveData<Lifecycle.State>()

    fun refreshNews(date: String) {
        stateSet.postValue(Lifecycle.State.STARTED)
        viewModelScope.launch {
            try {
              val response = services.getNewsEverything(
                    "Health", date, "publishedAt", BuildConfig.API_KEY
                )
                newsData.value = response.articles
                stateSet.postValue(Lifecycle.State.CREATED)

            } catch (error: Exception) {
                Log.e("senoo", "error : ", error)
                stateSet.postValue(Lifecycle.State.DESTROYED)
            }
        }
    }
}