package com.example.androidtasksilverkeytechnologies.ui.home.fragments.home_fragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidtasksilverkeytechnologies.news_api.ApiManger
import com.example.androidtasksilverkeytechnologies.news_api.model.Article

import com.example.androidtasksilverkeytechnologies.news_api.model.NewsResponse
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {
    var isLoadingLiveData = MutableLiveData(false)
    var articlesList = MutableLiveData<List<Article?>>()

     fun getTopHeaLines() {
        isLoadingLiveData.value = true
         viewModelScope.launch{
             try {
                 isLoadingLiveData.value = false
                 val newsResponse = ApiManger.getNewsServices().getTopHeaLines()
                 articlesList.value = newsResponse.articles!!
             }
             catch (ex : Throwable){
                 isLoadingLiveData.value = false
                 Log.e("onSuccess", "Response failed: ${ex.message}")
             }
        }

         /*
         val newsResponse = ApiManger.getNewsServices().getTopHeaLines()
         .enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                isLoadingLiveData.value = false
                if (response.isSuccessful) {
                    val newsResponse = response.body()
                    if (newsResponse?.status == "ok" && newsResponse.articles != null) {
                        articlesList.value = newsResponse.articles
                        Log.e("onSuccess", "Fetched ${newsResponse.articles.size} articles")
                    } else {
                        Log.e("onSuccess", "No articles found")
                    }
                } else {
                    Log.e("onSuccess", "Response failed: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<NewsResponse>, throwable: Throwable) {
               isLoadingLiveData.value = false
              //  binding.circularProgress.isVisible = false
                Log.e("onFailure", "${throwable.message}")

            }
        })
        */
    }

}