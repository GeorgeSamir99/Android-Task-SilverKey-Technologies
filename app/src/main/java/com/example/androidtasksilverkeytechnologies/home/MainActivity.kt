package com.example.androidtasksilverkeytechnologies.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtasksilverkeytechnologies.R
import com.example.androidtasksilverkeytechnologies.databinding.ActivityMainBinding
import com.example.androidtasksilverkeytechnologies.home.fragments.FavListFragment
import com.example.androidtasksilverkeytechnologies.home.fragments.HomeFragment
import com.example.androidtasksilverkeytechnologies.home.fragments.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
     lateinit var binding : ActivityMainBinding
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showFragment(HomeFragment())
        binding.bottomNav.setOnNavigationItemSelectedListener { menuItem ->
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
            return@setOnNavigationItemSelectedListener true
        }
    }
    private fun showFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.News_fragment_container,fragment)
            .commit()
    }


}
