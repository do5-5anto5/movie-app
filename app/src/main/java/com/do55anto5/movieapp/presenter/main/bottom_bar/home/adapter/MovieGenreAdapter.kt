package com.do55anto5.movieapp.presenter.main.bottom_bar.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.do55anto5.movieapp.R
import com.do55anto5.movieapp.databinding.GenreItemBinding
import com.do55anto5.movieapp.presenter.model.MoviesByGenre

class MovieGenreAdapter(
    private val showAllListener: (Int, String) -> Unit,
    private val movieClickListener: (Int?) -> Unit
): ListAdapter<MoviesByGenre, MovieGenreAdapter.MyViewHolder>(
    DIFF_CALLBACK
) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MoviesByGenre>() {
            override fun areItemsTheSame(
                oldItem: MoviesByGenre,
                newItem: MoviesByGenre
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: MoviesByGenre,
                newItem: MoviesByGenre
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            GenreItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val genre = getItem(position)

        holder.binding.genreName.text = genre.name

        val movieAdapter = MovieAdapter(
            context = holder.binding.root.context,
            layoutInflater = R.layout.movie_item,
            movieClickListener = movieClickListener
        )

        val layoutManager = LinearLayoutManager(
            holder.binding.root.context, LinearLayoutManager.HORIZONTAL, false
        )

        holder.binding.txtShowAll.setOnClickListener {
            genre.id?.let { showAllListener(it, genre.name ?: "") }
        }

        holder.binding.rvMovies.layoutManager = layoutManager
        holder.binding.rvMovies.setHasFixedSize(true)
        holder.binding.rvMovies.adapter = movieAdapter
        movieAdapter.submitList(genre.movies)
    }

    inner class MyViewHolder(val binding: GenreItemBinding) :
        RecyclerView.ViewHolder(binding.root)

}