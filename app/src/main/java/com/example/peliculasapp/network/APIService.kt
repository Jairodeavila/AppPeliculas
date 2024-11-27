package com.example.peliculasapp.network

import com.example.peliculasapp.data.model.MoviesResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET("discover/movie?sort_by=popularity.desc")
    suspend fun listPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("region") region: String
    ): MoviesResponse

    object RetrofitServiceFactory {
        fun makeRetrofitService(): APIService {
            return Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(APIService::class.java)
        }
    }

}
