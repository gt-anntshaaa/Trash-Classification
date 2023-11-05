package com.example.trashclassification.presentation.view.splashscreen

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.trashclassification.R
import com.example.trashclassification.databinding.FragmentSplashBinding
import com.example.trashclassification.presentation.common.util.setupFullScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : Fragment() {
    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configure the behavior of the hidden system bars
        //requireActivity().setupFullScreen()

        handleSplashScreen()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun handleSplashScreen() {
        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            // start your main home
            findNavController().navigate(SplashFragmentDirections.actionSplashToHome())
        }, 2000)
    }
}