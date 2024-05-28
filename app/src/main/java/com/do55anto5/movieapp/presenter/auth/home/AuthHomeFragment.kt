package com.do55anto5.movieapp.presenter.auth.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.do55anto5.movieapp.R
import com.do55anto5.movieapp.databinding.FragmentAuthHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthHomeFragment : Fragment() {

    private var _binding: FragmentAuthHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAuthHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()
    }

    private fun initListeners() {
        binding.btnLoginWithPassword.setOnClickListener {
            findNavController().navigate(R.id.action_authHomeFragment_to_loginFragment)
        }
        binding.btnRegister.setOnClickListener {
            findNavController().navigate(R.id.action_authHomeFragment_to_registerFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}