package com.do55anto5.movieapp.domain.usecase.movie

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.do55anto5.movieapp.data.mapper.toDomain
import com.do55anto5.movieapp.domain.model.movie.Movie
import com.do55anto5.movieapp.domain.repository.movie.MovieRepository
import com.do55anto5.movieapp.util.Constants.Paging.DEFAULT_PAGE_INDEX
import com.do55anto5.movieapp.util.Constants.Paging.NETWORK_PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetMoviesByGenrePaginationUseCase @Inject constructor(
    private val repository: MovieRepository
) {

    operator fun invoke(
        genreId: Int?
    ): Flow<PagingData<Movie>> = Pager(
        config = PagingConfig(
            pageSize = NETWORK_PAGE_SIZE,
            enablePlaceholders = false,
            initialLoadSize = DEFAULT_PAGE_INDEX
        ),
        pagingSourceFactory =
        {
            repository.getMoviesByGenrePagination(
                genreId = genreId
            )
        }
    ).flow.map { pagingData ->
        pagingData.map { movieResponse ->
            movieResponse.toDomain()
        }
    }

}