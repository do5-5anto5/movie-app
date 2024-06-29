package com.do55anto5.movieapp.data.mapper

import com.do55anto5.movieapp.data.local.entity.MovieEntity
import com.do55anto5.movieapp.data.model.AuthorDetailsResponse
import com.do55anto5.movieapp.data.model.CountryResponse
import com.do55anto5.movieapp.data.model.CreditsResponse
import com.do55anto5.movieapp.data.model.GenreResponse
import com.do55anto5.movieapp.data.model.MovieResponse
import com.do55anto5.movieapp.data.model.MovieReviewResponse
import com.do55anto5.movieapp.data.model.PersonResponse
import com.do55anto5.movieapp.domain.model.AuthorDetails
import com.do55anto5.movieapp.domain.model.Country
import com.do55anto5.movieapp.domain.model.Credits
import com.do55anto5.movieapp.domain.model.Genre
import com.do55anto5.movieapp.domain.model.Movie
import com.do55anto5.movieapp.domain.model.MovieReview
import com.do55anto5.movieapp.domain.model.Person

fun GenreResponse.toDomain(): Genre {
    return Genre(
        id = id,
        name = name
    )
}

fun MovieResponse.toDomain(): Movie {
    return Movie(
        adult = adult,
        backdropPath = backdropPath,
        genres = genres?.map { it.toDomain() },
        id = id,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount,
        runtime = runtime,
        productionCountries = productionCountries?.map { it.toDomain() }
    )
}

fun CountryResponse.toDomain(): Country {
    return Country(
        name = name
    )
}

fun PersonResponse.toDomain(): Person {
    return Person(
        adult = adult,
        gender = gender,
        id = id,
        knownForDepartment = knownForDepartment,
        name = name,
        originalName = originalName,
        popularity = popularity,
        profilePath = profilePath,
        castId = castId,
        character = character,
        creditId = creditId,
        order = order
    )
}

fun CreditsResponse.toDomain(): Credits {
    return Credits(
        cast = cast?.map { it.toDomain() }
    )
}

fun AuthorDetailsResponse.toDomain(): AuthorDetails {
    return AuthorDetails(
        name = name,
        username = username,
        avatarPath = avatarPath,
        rating = rating
    )
}

fun MovieReviewResponse.toDomain(): MovieReview {
    return MovieReview(
        author = author,
        authorDetails = authorDetailsResponse?.toDomain(),
        content = content,
        createdAt = createdAt,
        id = id,
        updatedAt = updatedAt,
        url = url,
    )
}

fun Movie.toEntity(): MovieEntity {
    return MovieEntity(
        id = id,
        title = title,
        poster = posterPath,
        runtime = runtime,
        insertion = System.currentTimeMillis()
    )
}

fun MovieEntity.toDomain(): Movie {
    return Movie(
        id = id,
        title = title,
        posterPath = poster,
        runtime = runtime
    )
}