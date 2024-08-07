package com.do55anto5.movieapp.presenter.main.bottom_bar.download.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.do55anto5.movieapp.R
import com.do55anto5.movieapp.databinding.MovieDownloadItemBinding
import com.do55anto5.movieapp.domain.model.movie.Movie
import com.do55anto5.movieapp.util.calculateFileSize
import com.do55anto5.movieapp.util.calculateMovieTime

class DownloadMovieAdapter(
    private val context: Context,
    private val detailsClickListener: (Int?) -> Unit,
    private val deleteClickListener: (Movie?) -> Unit
) : ListAdapter<Movie, DownloadMovieAdapter.MyViewHolder>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(
                oldItem: Movie,
                newItem: Movie
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: Movie,
                newItem: Movie
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            MovieDownloadItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie = getItem(position)

        Glide.with(context)
            .load("https://image.tmdb.org/t/p/w200${movie.posterPath}")
            .error(R.drawable.bg_shadow)
            .into(holder.binding.ivMovie)

        with(holder.binding) {
            textMovie.text = movie.title
            textDuration.text = movie.runtime?.calculateMovieTime()
            textSize.text = movie.runtime?.toDouble()?.calculateFileSize()
            ibDelete.setOnClickListener { deleteClickListener(movie) }
        }

        holder.itemView.setOnClickListener { detailsClickListener(movie.id) }
    }

    inner class MyViewHolder(val binding: MovieDownloadItemBinding):
        RecyclerView.ViewHolder(binding.root)

    override fun submitList(list: MutableList<Movie>?) {
        super.submitList(list?.let { ArrayList(it) })
    }

}