package com.example.androidtasksilverkeytechnologies.home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.androidtasksilverkeytechnologies.databinding.FragmentFavListBinding
import com.example.androidtasksilverkeytechnologies.databinding.FragmentHomeBinding

class FavListFragment : Fragment() {
    lateinit var binding: FragmentFavListBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavListBinding.inflate(inflater , container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}