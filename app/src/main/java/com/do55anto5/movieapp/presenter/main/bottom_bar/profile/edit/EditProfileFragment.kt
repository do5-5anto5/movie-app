package com.do55anto5.movieapp.presenter.main.bottom_bar.profile.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.do55anto5.movieapp.R
import com.do55anto5.movieapp.databinding.FragmentEditProfileBinding
import com.do55anto5.movieapp.util.initToolbar
import com.do55anto5.movieapp.util.showSnackBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditProfileFragment : Fragment() {

    private var _binding: FragmentEditProfileBinding? = null
    private val binding get() = _binding!!

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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}