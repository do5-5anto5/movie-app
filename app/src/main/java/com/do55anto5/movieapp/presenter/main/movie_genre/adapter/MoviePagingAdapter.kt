package com.do55anto5.movieapp.presenter.main.movie_genre.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.do55anto5.movieapp.R
import com.do55anto5.movieapp.databinding.MovieGenreItemBinding
import com.do55anto5.movieapp.domain.model.movie.Movie
import com.do55anto5.movieapp.util.circularProgressDrawable

class MoviePagingAdapter(
    private val context: Context,
    private val movieClickListener: (Int?) -> Unit
) : PagingDataAdapter<Movie, MoviePagingAdapter.MyViewHolder>(DIFF_CALLBACK) {

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
            MovieGenreItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie = getItem(position)

        Glide.with(context)
            .load("https://image.tmdb.org/t/p/w500${movie?.posterPath}")
            .placeholder(context.circularProgressDrawable())
            .error(R.drawable.bg_shadow)
            .into(holder.binding.movieImage)

        holder.itemView.setOnClickListener {
            movieClickListener(movie?.id)
        }
    }

    inner class MyViewHolder(val binding: MovieGenreItemBinding) :
        RecyclerView.ViewHolder(binding.root)

}