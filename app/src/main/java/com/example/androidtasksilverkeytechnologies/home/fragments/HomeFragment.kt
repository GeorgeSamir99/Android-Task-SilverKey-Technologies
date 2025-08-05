package com.example.androidtasksilverkeytechnologies.home.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidtasksilverkeytechnologies.databinding.FragmentHomeBinding
import com.example.androidtasksilverkeytechnologies.home.ArticlesAdapter
import com.example.androidtasksilverkeytechnologies.news_api.ApiManger
import com.example.androidtasksilverkeytechnologies.news_api.model.Article
import com.example.androidtasksilverkeytechnologies.news_api.model.NewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    lateinit var  adapter: ArticlesAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater , container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initArticlesRecyclerView()
        // Display mock articles for testing
        val mockArticles = listOf(
            Article("Author", "Test Title 1", "Test Description 1", "url", "https://via.placeholder.com/150", "2023-10-01"),
            Article("Author", "Test Title 2", "Test Description 2", "url", "https://via.placeholder.com/150", "2023-10-02")
        )
        adapter.updateArticles(mockArticles)
        Log.e("HomeFragment", "Showing mock ${mockArticles.size} articles")
        // Fetch real articles
        getTopHeaLines()

    }

    private fun initArticlesRecyclerView() {
        adapter = ArticlesAdapter(emptyList())
        binding.newsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            this.adapter = adapter
            isVisible = true // Ensure RecyclerView is visible
        }
    }

    private fun getTopHeaLines() {
        showLoading()
        ApiManger.getNewsServices().getTopHeaLines().enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                hideLoading()
                if (response.isSuccessful) {
                    val newsResponse = response.body()
                    Log.e("HomeFragment", "Response: status=${newsResponse?.status}, articles=${newsResponse?.articles?.size ?: 0}")
                    if (newsResponse?.status == "ok" && !newsResponse.articles.isNullOrEmpty()) {
                        adapter.updateArticles(newsResponse.articles)
                        Log.e("HomeFragment", "Showing ${newsResponse.articles.size} API articles")
                    } else {
                        Log.e("HomeFragment", "No articles found: ${newsResponse?.status}")
                    }
                } else {
                    Log.e("HomeFragment", "Response failed: code=${response.code()}, message=${response.message()}")
                }
            }

            override fun onFailure(call: Call<NewsResponse>, throwable: Throwable) {
                hideLoading()
                Log.e("onFailure", "${throwable.message}")
            }
        })
    }
//    fun showArticles(articlesList : List<Article>){
//        adapter.submitList(articlesList)
//    }
    private fun showLoading(){
        binding.circularProgress.isVisible = true
        binding.newsRecyclerView.isVisible = false
    }
    fun hideLoading(){
        binding.circularProgress.isVisible = false
        binding.newsRecyclerView.isVisible = true
    }



}
