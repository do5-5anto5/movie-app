package com.do55anto5.movieapp.presenter.main.bottom_bar.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.do55anto5.movieapp.R
import com.do55anto5.movieapp.databinding.BottomSheetLogoutBinding
import com.do55anto5.movieapp.databinding.FragmentProfileBinding
import com.do55anto5.movieapp.domain.model.MenuProfile
import com.do55anto5.movieapp.domain.model.MenuProfileType
import com.do55anto5.movieapp.presenter.auth.activity.AuthActivity
import com.do55anto5.movieapp.presenter.auth.activity.AuthActivity.Companion.AUTHENTICATION_PARAMETER
import com.do55anto5.movieapp.presenter.auth.enums.AuthenticationDestinations
import com.do55anto5.movieapp.presenter.main.bottom_bar.profile.adapter.ProfileMenuAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.auth.FirebaseAuth

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
                        findNavController().navigate(R.id.action_menu_profile_to_editProfileFragment)
                    }
                    MenuProfileType.NOTIFICATION -> {

                    }
                    MenuProfileType.DOWNLOADS -> {
                        val bottomNavigation = activity?.findViewById<BottomNavigationView>(R.id.bottom_bar)
                        bottomNavigation?.selectedItemId = R.id.menu_download
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
                        showBottomSheetLogout()
                    }
                }
            }
        )

        with(binding.rvItems) {
            setHasFixedSize(true)
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

    private fun showBottomSheetLogout() {
        val bottomSheetDialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialog)
        val bottomSheetBinding = BottomSheetLogoutBinding.inflate(
            layoutInflater, null, false
        )

        bottomSheetBinding.btnCancel.setOnClickListener {
            bottomSheetDialog.dismiss()
        }

        bottomSheetBinding.btnConfirm.setOnClickListener {
            logout()
            bottomSheetDialog.dismiss()
        }

        bottomSheetDialog.setContentView(bottomSheetBinding.root)
        bottomSheetDialog.show()
    }

    private fun logout() {
        FirebaseAuth.getInstance().signOut()
        activity?.finish()
        val intent = Intent(requireContext(), AuthActivity::class.java)
        intent.putExtra(AUTHENTICATION_PARAMETER, AuthenticationDestinations.LOGIN_SCREEN)
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}