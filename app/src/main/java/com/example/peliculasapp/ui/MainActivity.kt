package com.example.peliculasapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.peliculasapp.data.model.Result
import com.example.peliculasapp.databinding.ActivityMainBinding
import com.example.peliculasapp.network.APIService
import com.example.peliculasapp.utils.Constants
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var moviesAdapter: MoviesAdapter
    private var allMovies: List<Result> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerViewMovies.layoutManager = LinearLayoutManager(this)

        binding.svMovie.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterMovies(newText)
                return true
            }
        })

        val service = APIService.RetrofitServiceFactory.makeRetrofitService()
        lifecycleScope.launch {
            val response = service.listPopularMovies(Constants.API_KEY, "US")
            allMovies = response.results
            showMovies(allMovies)
        }

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun showMovies(movies: List<Result>) {
        moviesAdapter = MoviesAdapter(movies)
        binding.recyclerViewMovies.adapter = moviesAdapter
    }

    private fun filterMovies(query: String?) {
        val filteredList = if (query.isNullOrEmpty()) {
            allMovies
        } else {
            allMovies.filter { movie ->
                movie.title?.contains(query, ignoreCase = true) == true
            }
        }
        moviesAdapter = MoviesAdapter(filteredList)
        binding.recyclerViewMovies.adapter = moviesAdapter
    }
}
