package com.example.androidtasksilverkeytechnologies.home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.androidtasksilverkeytechnologies.databinding.FragmentHomeBinding
import com.example.androidtasksilverkeytechnologies.databinding.FragmentNewsDetailsBinding
import com.example.androidtasksilverkeytechnologies.databinding.NewsItemBinding

class NewDetailsFragment : Fragment() {
    lateinit var binding: FragmentNewsDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsDetailsBinding.inflate(inflater , container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}
