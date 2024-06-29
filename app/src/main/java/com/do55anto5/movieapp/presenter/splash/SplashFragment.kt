package com.do55anto5.movieapp.presenter.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.do55anto5.movieapp.R
import com.do55anto5.movieapp.databinding.FragmentSplashBinding
import com.do55anto5.movieapp.util.FirebaseHelper
import com.do55anto5.movieapp.util.onNavigate

class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initSplashScreen()
    }

    private fun initSplashScreen() {
        if(!FirebaseHelper.isAuthenticated()) {
            Handler(Looper.getMainLooper()).postDelayed({
                run {
                    findNavController().onNavigate(R.id.action_splashFragment_to_onboardingFragment)
                }
            }, 3000)
        } else {
            binding.animationView.isVisible = false
            Handler(Looper.getMainLooper()).postDelayed({
                run {
                    findNavController().onNavigate(R.id.action_splashFragment2_to_menu_home)
                }
            }, 1200)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}