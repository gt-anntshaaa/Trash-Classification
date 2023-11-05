package com.example.trashclassification.presentation.view.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.trashclassification.R
import com.example.trashclassification.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // setup event listener to navigate Classification Fragment
        binding.onClickClassifyMenu.setOnClickListener { doClassifyMenu() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun doClassifyMenu() {
        findNavController().navigate(HomeFragmentDirections.actionHomeToClassification())
    }
}