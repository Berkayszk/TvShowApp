package com.example.tvshowapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tvshowapp.adapter.TvShowAdapter
import com.example.tvshowapp.databinding.ActivityMainBinding
import com.example.tvshowapp.viewmodel.TvShowViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel : TvShowViewModel by viewModels()
    private lateinit var tvShowAdapter: TvShowAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        setUpRv()
    }
    private fun setUpRv(){
        tvShowAdapter = TvShowAdapter()
        binding.recyclerView.apply {
            adapter = tvShowAdapter
            layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
            setHasFixedSize(true)
        }

        binding.recyclerViewEpisode.apply {
            adapter = tvShowAdapter
            layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
            setHasFixedSize(true)
        }

        binding.recyclerViewRecently.apply {
            adapter = tvShowAdapter
            layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
            setHasFixedSize(true)
        }

        viewModel.responseTvShow.observe(this, { listTvShows ->
            tvShowAdapter.tvShows = listTvShows
        })
    }
}