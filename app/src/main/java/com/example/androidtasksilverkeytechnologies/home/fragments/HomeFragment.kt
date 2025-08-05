package com.example.androidtasksilverkeytechnologies.home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtasksilverkeytechnologies.R
import com.example.androidtasksilverkeytechnologies.databinding.FragmentHomeBinding
import com.example.androidtasksilverkeytechnologies.home.NewsAdapter

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater , container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = binding.newsRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = NewsAdapter(getSampleNews())

    }
    fun getSampleNews(): List<NewsItem> {
        return listOf(
            NewsItem("The rise of global tech", "3 days ago", "From AI breakthroughs to sustainable innovation...", R.drawable.test_image),
            NewsItem("The rise of global tech", "3 days ago", "From AI breakthroughs to sustainable innovation...", R.drawable.test_image),
            NewsItem("The rise of global tech", "3 days ago", "From AI breakthroughs to sustainable innovation...", R.drawable.test_image),
            NewsItem("The rise of global tech", "3 days ago", "From AI breakthroughs to sustainable innovation...", R.drawable.test_image),NewsItem("The rise of global tech", "3 days ago", "From AI breakthroughs to sustainable innovation...", R.drawable.test_image),
            NewsItem("The rise of global tech", "3 days ago", "From AI breakthroughs to sustainable innovation...", R.drawable.test_image),
        )
    }

}
data class NewsItem(val title: String, val timestamp: String, val description: String, val imageResId: Int)