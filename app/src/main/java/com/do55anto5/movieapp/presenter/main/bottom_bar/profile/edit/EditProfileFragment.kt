package com.do55anto5.movieapp.presenter.main.bottom_bar.profile.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.do55anto5.movieapp.R
import com.do55anto5.movieapp.databinding.FragmentEditProfileBinding
import com.do55anto5.movieapp.domain.model.user.User
import com.do55anto5.movieapp.util.FirebaseHelper
import com.do55anto5.movieapp.util.StateView
import com.do55anto5.movieapp.util.hideKeyboard
import com.do55anto5.movieapp.util.initToolbar
import com.do55anto5.movieapp.util.showSnackBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditProfileFragment : Fragment() {

    private var _binding: FragmentEditProfileBinding? = null
    private val binding get() = _binding!!

    private val viewModel: EditProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditProfileBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar(binding.toolbar)

        initListeners()
    }

    private fun initListeners() {
        binding.btnUpdate.setOnClickListener {
            validateData()
        }
    }

    private fun validateData() {

        val name = binding.editNameFirstName.text.toString()
        val surname = binding.editSurname.text.toString()
        val phoneNumber = binding.editPhoneNumber.text.toString()
        val gender = binding.editGender.text.toString()
        val country = binding.editCountry.text.toString()

        if (name.isEmpty()) {
            showSnackBar(R.string.text_name_empty)
            return
        }

        if (surname.isEmpty()) {
            showSnackBar(R.string.text_name_empty)
            return
        }

        if (phoneNumber.isEmpty()) {
            showSnackBar(R.string.text_phone_invalid)
            return
        }

        if (gender.isEmpty()) {
            showSnackBar(R.string.text_gender_empty)
            return
        }

        if (country.isEmpty()) {
            showSnackBar(R.string.text_country_empty)
            return
        }

        hideKeyboard()

        val user = User(
            firstName = name,
            surname = surname,
            phoneNumber = phoneNumber,
            email = FirebaseHelper.getAuth().currentUser?.email,
            gender = gender,
            country = country
        )

        update(user)


    }

    private fun update(user: User) {
        viewModel.update(user).observe(viewLifecycleOwner) { stateView ->
            when (stateView) {
                is StateView.Loading -> {
                    showLoading(true)
                }
                is StateView.Success -> {
                    showLoading(false)
                    showSnackBar(R.string.snackbar_text_update_edit_profile_fragment)
                }
                is StateView.Error -> {
                    showLoading(false)
                    showSnackBar(
                        FirebaseHelper.validError(stateView.message ?: ""))
                }
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            Glide
                .with(requireContext())
                .load(R.drawable.loading)
                .into(binding.progressLoading)

            binding.progressLoading.isVisible = true
        } else {
            binding.progressLoading.isVisible = false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}