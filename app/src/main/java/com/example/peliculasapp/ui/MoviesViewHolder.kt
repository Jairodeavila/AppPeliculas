package com.example.peliculasapp.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.peliculasapp.data.model.Result
import com.example.peliculasapp.databinding.ItemMovieBinding
import com.squareup.picasso.Picasso

class MovieViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(movie: Result) {
        binding.textViewTitle.text = movie.title
        binding.textViewOverview.text = movie.releaseDate
        binding.tvVoteAverage.text = movie.voteAverage.toString()


        Picasso.get()
            .load("https://image.tmdb.org/t/p/w500" + movie.posterPath)
            .into(binding.imageViewPoster)
        }
}