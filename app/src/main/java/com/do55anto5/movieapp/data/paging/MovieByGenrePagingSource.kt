package com.do55anto5.movieapp.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.do55anto5.movieapp.BuildConfig
import com.do55anto5.movieapp.data.api.ServiceApi
import com.do55anto5.movieapp.data.model.MovieResponse
import com.do55anto5.movieapp.util.Constants

class MovieByGenrePagingSource(
    private val service: ServiceApi,
    private val genreId: Int?
) : PagingSource<Int, MovieResponse>() {

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, MovieResponse> {
        return try {
            val page = params.key ?: Constants.Paging.DEFAULT_PAGE_INDEX
            val result = service.getMoviesByGenre(
                apiKey = BuildConfig.API_KEY,
                language = Constants.Movie.LANGUAGE,
                genreId = genreId,
                page = page
            ).results ?: emptyList()
            return LoadResult.Page(
                data = result,
                prevKey = if (page == Constants.Paging.DEFAULT_PAGE_INDEX) null else page - 1,
                nextKey = if (result.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}