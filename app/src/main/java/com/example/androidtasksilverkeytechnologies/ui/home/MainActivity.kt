package com.example.androidtasksilverkeytechnologies.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.androidtasksilverkeytechnologies.R
import com.example.androidtasksilverkeytechnologies.databinding.ActivityMainBinding
import com.example.androidtasksilverkeytechnologies.news_api.model.Article
import com.example.androidtasksilverkeytechnologies.ui.ArticleClickListener
import com.example.androidtasksilverkeytechnologies.ui.home.fragments.fav_list_fragment.FavListFragment
import com.example.androidtasksilverkeytechnologies.ui.home.fragments.home_fragment.HomeFragment
import com.example.androidtasksilverkeytechnologies.ui.home.fragments.news_details_fragment.NewDetailsFragment
import com.example.androidtasksilverkeytechnologies.ui.home.fragments.profile_fregment.ProfileFragment

class MainActivity : AppCompatActivity(), ArticleClickListener {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(0, systemBars.top, 0, 0) // Adjust padding if needed
            insets
        }
        showFragment(HomeFragment())
        binding.bottomNav.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    showFragment(HomeFragment())
                }
                R.id.nav_saved -> {
                    showFragment(FavListFragment())
                }
                R.id.nav_profile -> {
                    showFragment(ProfileFragment())
                }
                else -> showFragment(HomeFragment())

            }
             true
        }

    }
    private fun showFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.News_fragment_container,fragment)
            .addToBackStack(null)
            .commit()
    }
    override fun onArticleClicked(article: Article) {
        val fragment = NewDetailsFragment()
        val bundle = Bundle().apply {
            putParcelable("article", article)
        }
        fragment.arguments = bundle
        showFragment(fragment)
    }


}
