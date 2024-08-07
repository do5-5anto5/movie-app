package com.do55anto5.movieapp.presenter.main.bottom_bar.download

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.do55anto5.movieapp.MainGraphDirections
import com.do55anto5.movieapp.R
import com.do55anto5.movieapp.databinding.BottomSheetDeleteMovieBinding
import com.do55anto5.movieapp.databinding.FragmentDownloadBinding
import com.do55anto5.movieapp.domain.model.movie.Movie
import com.do55anto5.movieapp.presenter.main.bottom_bar.download.adapter.DownloadMovieAdapter
import com.do55anto5.movieapp.util.calculateFileSize
import com.do55anto5.movieapp.util.calculateMovieTime
import com.do55anto5.movieapp.util.initToolbar
import com.do55anto5.movieapp.util.onNavigate
import com.ferfalk.simplesearchview.SimpleSearchView
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DownloadFragment : Fragment() {

    private var _binding: FragmentDownloadBinding? = null
    private val binding get() = _binding!!

    private lateinit var mAdapter: DownloadMovieAdapter

    private val viewModel: DownloadViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDownloadBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar(toolbar = binding.toolbar, showBackIcon = false)

        initRecyclerView()

        initObservers()

        getData()

        initListeners()
    }

    private fun initSearchView() {
        binding.simpleSearchView.setOnQueryTextListener(object :
            SimpleSearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.isNotEmpty() || newText.isBlank()) {
                    viewModel.searchMovie(newText)
                }
                return true
            }

            override fun onQueryTextCleared(): Boolean {
                return false
            }
        })

        binding.simpleSearchView.setOnSearchViewListener(object :
            SimpleSearchView.SearchViewListener {
            override fun onSearchViewShown() {
            }

            override fun onSearchViewClosed() {
                viewModel.getMovies()
            }

            override fun onSearchViewShownAnimation() {
            }

            override fun onSearchViewClosedAnimation() {
            }
        })
    }

    private fun initRecyclerView() {
        mAdapter = DownloadMovieAdapter(
            context = requireContext(),
            detailsClickListener = { movieId ->
                movieId?.let {
                    val action = MainGraphDirections.actionGlobalMovieDetailsFragment(movieId)
                    findNavController().onNavigate(action)
                }
            },
            deleteClickListener = { movie ->
                showBottomSheetDeleteMovie(movie)
            }
        )
        with(binding.rvMovies) {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = mAdapter
        }
    }

    private fun showBottomSheetDeleteMovie(movie: Movie?) {
        val bottomSheetDialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialog)
        val bottomSheetBinding = BottomSheetDeleteMovieBinding.inflate(
            layoutInflater, null, false
        )

        Glide.with(requireContext())
            .load("https://image.tmdb.org/t/p/w200${movie?.posterPath}")
            .error(R.drawable.bg_shadow)
            .into(bottomSheetBinding.ivMovie)

        with(bottomSheetBinding) {
            textMovie.text = movie?.title
            textDuration.text = movie?.runtime?.calculateMovieTime()
            textSize.text = movie?.runtime?.toDouble()?.calculateFileSize()
        }

        bottomSheetBinding.btnCancel.setOnClickListener {
            bottomSheetDialog.dismiss()
        }

        bottomSheetBinding.btnConfirm.setOnClickListener {
            movie?.let {
                viewModel.deleteMovie(it.id)
                bottomSheetDialog.dismiss()
            }
        }

        bottomSheetDialog.setContentView(bottomSheetBinding.root)
        bottomSheetDialog.show()
    }

    private fun getData() {
        viewModel.getMovies()
    }

    private fun initObservers() {
        viewModel.moviesList.observe(viewLifecycleOwner) { movies ->
            mAdapter.submitList(movies)
            emptyState(movies.isEmpty())
        }

        viewModel.moviesSearchList.observe(viewLifecycleOwner) { movies ->
            mAdapter.submitList(movies)
            emptyState(movies.isEmpty())
        }
    }

    private fun initListeners() {
        initSearchView()
        onBackPressed()
    }

    private fun emptyState(empty: Boolean) {
        binding.rvMovies.isVisible = !empty
        binding.layoutEmpty.isVisible = empty
    }

    private fun onBackPressed() {
        requireActivity().onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object: OnBackPressedCallback(true){
                override fun handleOnBackPressed() {
                    if (binding.simpleSearchView.isVisible) {
                        binding.simpleSearchView.closeSearch()
                    } else {
                        findNavController().popBackStack()
                    }
                }
            })
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