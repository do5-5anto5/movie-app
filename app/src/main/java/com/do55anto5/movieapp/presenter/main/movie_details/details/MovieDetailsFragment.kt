package com.do55anto5.movieapp.presenter.main.movie_details.details

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.do55anto5.movieapp.R
import com.do55anto5.movieapp.databinding.DialogDownloadingBinding
import com.do55anto5.movieapp.databinding.FragmentMovieDetailsBinding
import com.do55anto5.movieapp.domain.model.Movie
import com.do55anto5.movieapp.presenter.main.movie_details.similar.SimilarFragment
import com.do55anto5.movieapp.presenter.main.movie_details.trailers.TrailersFragment
import com.do55anto5.movieapp.presenter.main.movie_details.adapter.CastAdapter
import com.do55anto5.movieapp.presenter.main.movie_details.adapter.ViewPagerAdapter
import com.do55anto5.movieapp.presenter.main.movie_details.comments.CommentsFragment
import com.do55anto5.movieapp.util.StateView
import com.do55anto5.movieapp.util.ViewPager2ViewHeightAnimator
import com.do55anto5.movieapp.util.calculateFileSize
import com.do55anto5.movieapp.util.initToolbar
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Locale

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {

    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MovieDetailsViewModel by activityViewModels()

    private val args: MovieDetailsFragmentArgs by navArgs()

    private lateinit var castAdapter: CastAdapter

    private lateinit var dialogDownloading: AlertDialog

    private lateinit var movie: Movie

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

        initRecyclerViewCredits()

        configTabLayout()

        initListeners()
    }

    private fun initListeners() {
        binding.btnDownload.setOnClickListener {
            showDownloadingDialog()
            insertMovie()
        }
    }

    private fun configTabLayout() {

        viewModel.setMovieId(movieId = args.movieId)

        val adapter = ViewPagerAdapter(requireActivity())
        val mViewPager = ViewPager2ViewHeightAnimator()

        mViewPager.viewPager2 = binding.viewPager
        mViewPager.viewPager2?.adapter = adapter

        binding.viewPager.adapter = adapter

        adapter.addFragment(
            fragment = TrailersFragment(),
            title = R.string.title_trailers_tab_layout,
        )
        adapter.addFragment(
            fragment = SimilarFragment(),
            title = R.string.title_similar_tab_layout,
        )
        adapter.addFragment(
            fragment = CommentsFragment(),
            title = R.string.title_comments_tab_layout,
        )

        binding.viewPager.offscreenPageLimit = adapter.itemCount

        mViewPager.viewPager2?.let { viewPager ->
            TabLayoutMediator(
                binding.tabs, viewPager
            ) { tab, position ->
                tab.text = getString(adapter.getTitle(position))
            }.attach()
        }

    }

    private fun getMovieDetails() {
        viewModel.getMovieDetails(movieId = args.movieId).observe(viewLifecycleOwner) { stateView ->
            when (stateView) {
                is StateView.Loading -> {
                }

                is StateView.Success -> {
                   stateView.data?.let {
                        this.movie = it
                        configData()
                   }
                }

                is StateView.Error -> {
                }

                else -> {

                }
            }
        }
    }

    private fun insertMovie() {
        viewModel.insertMovie(movie).observe(viewLifecycleOwner) { stateView ->
            when (stateView) {
                is StateView.Loading -> {
                }

                is StateView.Success -> {
                    configData()
                }

                is StateView.Error -> {
                }

                else -> {

                }
            }
        }
    }

    private fun initRecyclerViewCredits() {
        castAdapter = CastAdapter()

        with(binding.recyclerCast) {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
            adapter = castAdapter
        }
    }

    private fun getCredits() {
        viewModel.getCredits(movieId = args.movieId).observe(viewLifecycleOwner) { stateView ->
            when (stateView) {
                is StateView.Loading -> {
                }

                is StateView.Success -> {
                    castAdapter.submitList(stateView.data?.cast)
                }

                is StateView.Error -> {
                }

                else -> {

                }
            }
        }
    }

    private fun configData() {
        Glide.with(requireContext())
            .load("https://image.tmdb.org/t/p/w500${movie.posterPath}")
            .error(R.drawable.bg_shadow)
            .into(binding.imageMovie)

        val originalFormat = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ROOT)
        val date = originalFormat.parse(movie.releaseDate ?: "")
        val yearFormat = SimpleDateFormat("yyyy", Locale.ROOT)
        val year = date?.let { yearFormat.format(it) }

        with(binding) {

            textMovie.text = movie.title

            textVoteAverage.text = String.format(Locale.ROOT, "%.1f", movie.voteAverage)
            textReleaseDate.text = year
            textProductionCountry.text = movie.productionCountries?.get(0)?.name ?: ""

            val genres = movie.genres?.map { it.name }?.joinToString(", ")
            textGenres.text = getString(R.string.text_all_movie_genres, genres)

            textOverview.text = movie.overview
        }

        getCredits()
    }

    private fun showDownloadingDialog() {
        val dialogBinding = DialogDownloadingBinding.inflate(LayoutInflater.from(requireContext()))
        var progress = 0
        var downloaded = 0.0
        val movieDuration = movie.runtime?.toDouble() ?: 0.0

        val handle = Handler(Looper.getMainLooper())
        val runnable = object: Runnable {
            override fun run() {
                if (progress < 100) {

                    downloaded += (movieDuration / 100)
                    dialogBinding.textDownloadingProgress.text = getString(
                        R.string.text_downloaded_size_dialog_downloading,
                        downloaded.calculateFileSize(),
                        movieDuration.calculateFileSize()
                    )

                    progress++
                    dialogBinding.progressIndicator.progress = progress
                    dialogBinding.textDownloadingPercent.text = getString(
                        R.string.text_download_progress_dialog_downloading,
                        progress
                    )
                handle.postDelayed(this, 50)
                } else {
                    dialogDownloading.dismiss()
                }
            }
        }

        handle.post(runnable)

        val builder = AlertDialog.Builder(requireContext(), R.style.CustomAlertDialog)
        builder.setView(dialogBinding.root)

        dialogBinding.btnHide.setOnClickListener { dialogDownloading.dismiss() }
        dialogBinding.ibCancel.setOnClickListener { dialogDownloading.dismiss() }

        dialogDownloading = builder.create()
        dialogDownloading.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}