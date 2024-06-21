package com.do55anto5.movieapp.presenter.main.movie_genre

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.do55anto5.movieapp.MainGraphDirections
import com.do55anto5.movieapp.R
import com.do55anto5.movieapp.databinding.FragmentMovieGenreBinding
import com.do55anto5.movieapp.presenter.main.movie_genre.adapter.MoviePagingAdapter
import com.do55anto5.movieapp.util.StateView
import com.do55anto5.movieapp.util.hideKeyboard
import com.do55anto5.movieapp.util.initToolbar
import com.ferfalk.simplesearchview.SimpleSearchView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MovieGenreFragment : Fragment() {

    private var _binding: FragmentMovieGenreBinding? = null
    private val binding get() = _binding!!

    private val args: MovieGenreFragmentArgs by navArgs()

    private lateinit var moviePagingAdapter: MoviePagingAdapter

    private val viewModel: MovieGenreViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieGenreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar(binding.toolbar)

        binding.toolbar.title = args.name

        initRecyclerView()

        initSearchView()

        getMoviesByGenre()
    }

    private fun initRecyclerView() {
        moviePagingAdapter = MoviePagingAdapter(
            context = requireContext(),
            movieClickListener = { movieId ->
                movieId?.let {
                    val action = MainGraphDirections.actionGlobalMovieDetailsFragment(movieId)
                    findNavController().navigate(action)
                }
            }
        )
        with(binding.recyclerMovies) {
            layoutManager = GridLayoutManager(requireContext(), 2)
            setHasFixedSize(true)
            adapter = moviePagingAdapter
        }
    }

    private fun initSearchView() {
        binding.simpleSearchView.setOnQueryTextListener(object : SimpleSearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {

                hideKeyboard()
                if (query.isNotEmpty()) searchMovies(query)

                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                Log.d("SimpleSearchView", "Text changed:$newText")
                return false
            }

            override fun onQueryTextCleared(): Boolean {
                Log.d("SimpleSearchView", "Text cleared")
                return false
            }
        })

        binding.simpleSearchView.setOnSearchViewListener(object : SimpleSearchView.SearchViewListener {
            override fun onSearchViewShown() {
                Log.d("SimpleSearchView", "onSearchViewShown")
            }

            override fun onSearchViewClosed() {
                getMoviesByGenre()
            }

            override fun onSearchViewShownAnimation() {
                Log.d("SimpleSearchView", "onSearchViewShownAnimation")
            }

            override fun onSearchViewClosedAnimation() {
                Log.d("SimpleSearchView", "onSearchViewClosedAnimation")
            }
        })
    }

    private fun getMoviesByGenre(forceRequest: Boolean = false) {
        lifecycleScope.launch{
            viewModel.getMoviesByGenre(genreId = args.genreId, forceRequest = forceRequest)
            viewModel.moviesList.collectLatest { pagingData ->
                moviePagingAdapter.submitData(viewLifecycleOwner.lifecycle ,pagingData)
            }
        }
    }

    private fun searchMovies(query: String?) {
        viewModel.searchMovies(query).observe(viewLifecycleOwner) { stateView ->
            when (stateView) {
                is StateView.Loading -> {
                    binding.recyclerMovies.isVisible = false
                    binding.progressBar.isVisible = true
                }

                is StateView.Success -> {
                    binding.progressBar.isVisible = false
                    getMoviesByGenre(forceRequest = true)
                    binding.recyclerMovies.isVisible = true
                }

                is StateView.Error -> {
                    binding.progressBar.isVisible = false
                    binding.recyclerMovies.isVisible = true
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search_view, menu)
        val item = menu.findItem(R.id.action_search)
        binding.simpleSearchView.setMenuItem(item)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}