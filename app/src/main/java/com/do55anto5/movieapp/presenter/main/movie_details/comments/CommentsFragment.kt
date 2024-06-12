package com.do55anto5.movieapp.presenter.main.movie_details.comments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.do55anto5.movieapp.databinding.FragmentCommentsBinding
import com.do55anto5.movieapp.presenter.main.movie_details.adapter.CommentsAdapter
import com.do55anto5.movieapp.presenter.main.movie_details.details.MovieDetailsViewModel
import com.do55anto5.movieapp.util.StateView
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CommentsFragment : Fragment() {

    private var _binding: FragmentCommentsBinding? = null
    private val binding get() = _binding!!

    private lateinit var commentsAdapter: CommentsAdapter

    private val movieDetailsViewModel: MovieDetailsViewModel by activityViewModels()

    private val commentsViewModel: CommentsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCommentsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()

        initObservers()

    }

    private fun initRecyclerView() {

        commentsAdapter = CommentsAdapter()

        with(binding.recyclerComments) {
            adapter = commentsAdapter
        }
    }

    private fun initObservers() {
        movieDetailsViewModel.movieId.observe(viewLifecycleOwner) { movieId ->
            if(movieId > 0) {
                getMovieReviews(movieId)
            }
        }
    }

   private fun getMovieReviews(movieId: Int) {

        commentsViewModel.getMovieReviews(movieId).observe(viewLifecycleOwner) { stateView ->
            when (stateView) {
                is StateView.Loading -> {

                }
                is StateView.Success -> {
                    commentsAdapter.submitList(stateView.data)
                }
                is StateView.Error -> {

                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}