package com.example.androidtasksilverkeytechnologies.ui.home.fragments.news_details_fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.androidtasksilverkeytechnologies.R
import com.example.androidtasksilverkeytechnologies.databinding.FragmentNewsDetailsBinding
import com.example.androidtasksilverkeytechnologies.news_api.model.Article

class NewDetailsFragment : Fragment() {
    private lateinit var binding: FragmentNewsDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsDetailsBinding.inflate(inflater , container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val article = arguments?.getParcelable<Article>("article")
        article?.let {

            binding.newsTitle.text = it.getSafeTitle()
            binding.newsTimestamp.text = it.getSafePublishedAt()
            binding.newsDescription.text = it.getSafeDescription()

            binding.openArticleButton.setOnClickListener {
                val url = article.url ?: ""
                if (url.isNotEmpty()) {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    startActivity(intent)
                }
            }
            Glide.with(binding.root)
                .load(it.urlToImage)
                .placeholder(R.drawable.test_image)
                .error(R.drawable.test_image)
                .into(binding.newsImage)

        }
    }
}
