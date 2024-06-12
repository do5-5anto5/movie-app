package com.do55anto5.movieapp.presenter.main.movie_details.comments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.do55anto5.movieapp.databinding.FragmentCommentsBinding
import com.do55anto5.movieapp.domain.model.AuthorDetails
import com.do55anto5.movieapp.domain.model.MovieReview
import com.do55anto5.movieapp.presenter.main.movie_details.adapter.CommentsAdapter


class CommentsFragment : Fragment() {

    private var _binding: FragmentCommentsBinding? = null
    private val binding get() = _binding!!

    private lateinit var commentsAdapter: CommentsAdapter

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

        commentsAdapter.submitList(fakeList())
    }

    private fun initRecyclerView() {
        commentsAdapter = CommentsAdapter()


        with(binding.recyclerComments) {
            adapter = commentsAdapter
        }

    }

    private fun fakeList(): List<MovieReview> {
        return listOf(
            MovieReview(
                author = "thealanfrench",
                authorDetails = AuthorDetails(
                    name = "",
                    username = "thealanfrench",
                    avatarPath = "https://image.tmdb.org/t/p/w500/1kks3YnVkpyQxzw36CObFPvhL5f.jpg",
                    rating = 5
                ),
                content = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
                createdAt = "2023-03-15T05:13:49.138Z",
                id = "6411540dfe6c1800bb659ebd",
                updatedAt = "2023-03-15T05:13:49.138Z",
                url = "https://www.themoviedb.org/review/6411540dfe6c1800bb659ebd"
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}