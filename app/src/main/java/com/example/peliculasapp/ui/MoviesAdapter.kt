package com.example.peliculasapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.peliculasapp.R
import com.example.peliculasapp.data.model.Result
import com.example.peliculasapp.databinding.ItemMovieBinding

class MoviesAdapter(private val movies : List<Result>): RecyclerView.Adapter<MovieViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun getItemCount(): Int {
     return movies.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)


    }
}