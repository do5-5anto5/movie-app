package com.do55anto5.movieapp.presenter.main.bottom_bar.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.do55anto5.movieapp.R
import com.do55anto5.movieapp.databinding.FragmentMovieDetailsBinding
import com.do55anto5.movieapp.databinding.FragmentProfileBinding
import com.do55anto5.movieapp.domain.model.MenuProfile
import com.do55anto5.movieapp.domain.model.MenuProfileType
import com.do55anto5.movieapp.presenter.main.bottom_bar.profile.adapter.ProfileMenuAdapter
import com.do55anto5.movieapp.presenter.main.movie_details.adapter.CommentsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var mAdapter: ProfileMenuAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configData()

        initRecyclerView()
    }

    private fun initRecyclerView() {

        mAdapter = ProfileMenuAdapter(
            items = MenuProfile.items,
            context = requireContext(),
            onClick = { type ->
                when (type) {
                    MenuProfileType.PROFILE -> {

                    }
                    MenuProfileType.NOTIFICATION -> {

                    }
                    MenuProfileType.DOWNLOADS -> {

                    }
                    MenuProfileType.SECURITY -> {

                    }
                    MenuProfileType.LANGUAGE -> {

                    }
                    MenuProfileType.DARK_MODE -> {

                    }
                    MenuProfileType.HELPER -> {

                    }
                    MenuProfileType.PRIVACY_POLICY -> {

                    }
                    MenuProfileType.LOGOUT -> {

                    }
                }
            }
        )

        with(binding.rvItems) {
            adapter = mAdapter
        }
    }

    private fun configData() {
        with(binding) {
            imgProfile.setImageDrawable(
                ContextCompat.getDrawable(requireContext(), R.drawable.person_placeholder)
            )

            txtProfileName.text = "Mock Name"
            txtProfileEmail.text = "mock@email.com"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}