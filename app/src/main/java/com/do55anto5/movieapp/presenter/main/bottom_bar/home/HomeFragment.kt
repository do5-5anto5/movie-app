package com.do55anto5.movieapp.presenter.main.bottom_bar.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.do55anto5.movieapp.MainGraphDirections
import com.do55anto5.movieapp.databinding.FragmentHomeBinding
import com.do55anto5.movieapp.presenter.main.bottom_bar.home.adapter.MovieGenreAdapter
import com.do55anto5.movieapp.util.StateView
import com.do55anto5.movieapp.util.onNavigate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    private lateinit var genreMovieAdapter: MovieGenreAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()

        initObservers()

    }

    private fun initObservers() {
        viewModel.homeState.observe(viewLifecycleOwner) { stateView ->
            when(stateView) {
                is StateView.Loading -> {
                    binding.progressBar.isVisible = true
                    binding.recyclerGenres.isVisible = false
                }
                is StateView.Success -> {
                    binding.progressBar.isVisible = false
                    binding.recyclerGenres.isVisible = true
                }
                is StateView.Error -> {
                    binding.progressBar.isVisible = false
                    binding.recyclerGenres.isVisible = false
                }
            }
        }

        viewModel.moviesList.observe(viewLifecycleOwner) { moviesByGenre ->
            genreMovieAdapter.submitList(moviesByGenre)
        }
    }

    private fun initRecyclerView() {
        genreMovieAdapter = MovieGenreAdapter(
            showAllListener = { genreId, genreName ->
                val action =
                    HomeFragmentDirections.actionMenuHomeToMovieGenreFragment(genreId, genreName)
                findNavController().onNavigate(action)
            },
            movieClickListener = { movieId ->
                movieId?.let {
                    val action = MainGraphDirections.actionGlobalMovieDetailsFragment(movieId)
                    findNavController().onNavigate(action)
                }
            }
        )
        with(binding.recyclerGenres) {
            setHasFixedSize(true)
            adapter = genreMovieAdapter
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}