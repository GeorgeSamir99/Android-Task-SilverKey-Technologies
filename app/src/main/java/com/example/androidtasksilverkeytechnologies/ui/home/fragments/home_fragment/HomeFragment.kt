package com.example.androidtasksilverkeytechnologies.ui.home.fragments.home_fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidtasksilverkeytechnologies.databinding.FragmentHomeBinding
import com.example.androidtasksilverkeytechnologies.news_api.model.Article
import com.example.androidtasksilverkeytechnologies.ui.ArticleClickListener
import com.example.androidtasksilverkeytechnologies.ui.home.adapters.ArticlesAdapter

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    lateinit var  adapter: ArticlesAdapter
    private val viewModel = HomeViewModel()
    private var articleClickListener: ArticleClickListener? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ArticleClickListener){
            articleClickListener = context
        }
    }
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
        viewModel.getTopHeaLines()
        observeViewModel()
    }

    private fun observeViewModel() {
       viewModel.isLoadingLiveData.observe(viewLifecycleOwner){
            isloading->
           binding.circularProgress.isVisible = isloading
           binding.newsRecyclerView.isVisible = !isloading
        }
        viewModel.articlesList.observe(viewLifecycleOwner){
            articles->
            if (articles != null) {
                //adapter.updateArticles(articles)
                binding.newsRecyclerView.adapter = ArticlesAdapter(articles,articleClickListener ?: object : ArticleClickListener {
                    override fun onArticleClicked(article: Article) {
                        // Fallback implementation if articleClickListener is null
                        Log.e("HomeFragment", "Article clicked but no listener attached: ${article.getSafeTitle()}")
                    }
                })
            } else {
                Log.e("HomeFragment", "Articles list is null")
            }
        }
    }

    private fun initArticlesRecyclerView() {
        adapter = ArticlesAdapter(emptyList(), articleClickListener ?: object : ArticleClickListener {
            override fun onArticleClicked(article: Article) {
                // Fallback implementation if articleClickListener is null
                Log.e("HomeFragment", "Article clicked but no listener attached: ${article.getSafeTitle()}")
            }
        })
        binding.newsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            this.adapter = adapter
            isVisible = true
        }
    }

//    private fun navigateToNewsDetailsFragment() {
//        val bundle = Bundle().apply {
//            putParcelable("article", article)
//        }
//        findNavController().navigate(
//            R.id.action_homeFragment_to_newDetailsFragment,
//            bundle
//        )
//    }

    override fun onDetach() {
        super.onDetach()
        articleClickListener = null
    }


    /* private fun getTopHeaLines() {
         binding.circularProgress.isVisible = true
         ApiManger.getNewsServices().getTopHeaLines().enqueue(object : Callback<NewsResponse> {
             override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                 binding.circularProgress.isVisible = false
                 if (response.isSuccessful) {
                     val newsResponse = response.body()
                     if (newsResponse?.status == "ok" && newsResponse.articles != null) {
                         binding.newsRecyclerView.adapter = ArticlesAdapter(newsResponse.articles)
                         Log.e("onSuccess", "Fetched ${newsResponse.articles.size} articles")
                     } else {
                         Log.e("onSuccess", "No articles found")
                     }
                 } else {
                     Log.e("onSuccess", "Response failed: ${response.message()}")
                 }
             }

             override fun onFailure(call: Call<NewsResponse>, throwable: Throwable) {
                 binding.circularProgress.isVisible = false
                 Log.e("onFailure", "${throwable.message}")
                 adapter.updateArticles(listOf(
                     Article("Author", "Test Title 1", "Test Description 1", "url", "https://via.placeholder.com/150", "2023-10-01"),
                     Article("Author", "Test Title 2", "Test Description 2", "url", "https://via.placeholder.com/150", "2023-10-02")
                 ))
             }
         })
     }

     */




}
