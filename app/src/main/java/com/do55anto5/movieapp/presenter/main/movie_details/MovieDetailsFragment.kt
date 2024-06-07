package com.do55anto5.movieapp.presenter.main.movie_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.do55anto5.movieapp.R
import com.do55anto5.movieapp.databinding.FragmentMovieDetailsBinding
import com.do55anto5.movieapp.domain.model.Movie
import com.do55anto5.movieapp.util.StateView
import com.do55anto5.movieapp.util.initToolbar
import dagger.hilt.android.AndroidEntryPoint
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Locale

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {

    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MovieDetailsViewModel by viewModels()

    private val args: MovieDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar(binding.toolbar, lightIcon = true)

        getMovieDetails()
    }

    private fun getMovieDetails() {
        viewModel.getMovieDetails(movieId = args.movieId).observe(viewLifecycleOwner) { stateView ->
            when (stateView) {
                is StateView.Loading -> {
                }

                is StateView.Success -> {
                    configData(stateView.data)
                }

                is StateView.Error -> {
                }
                else -> {

                }
            }
        }
    }

    private fun configData(movie: Movie?) {
        Glide.with(requireContext())
            .load("https://image.tmdb.org/t/p/w500${movie?.posterPath}")
            .error(R.drawable.bg_shadow)
            .into(binding.imageMovie)

            val originalFormat = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ROOT)
            val date = originalFormat.parse(movie?.releaseDate ?: "")
            val yearFormat = SimpleDateFormat("yyyy", Locale.ROOT)
            val year = date?.let { yearFormat.format(it) }

        with(binding) {
            textMovie.text = movie?.title
            textVoteAverage.text = String.format(Locale.ROOT,"%.1f", movie?.voteAverage)
            textReleaseDate.text = year
            textProductionCountry.text = movie?.productionCountries?.get(0)?.name ?: ""
            val genres = movie?.genres?.map { it.name }?.joinToString(", ")
            textGenres.text = getString(R.string.text_all_movie_genres, genres)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}