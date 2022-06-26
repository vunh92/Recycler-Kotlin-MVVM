package com.vunh.recyclerkotlinmvvm.view

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.vunh.recyclerkotlinmvvm.BaseApp
import com.vunh.recyclerkotlinmvvm.databinding.ActivityDetailMovieBinding
import com.vunh.recyclerkotlinmvvm.model.Movie
import com.vunh.recyclerkotlinmvvm.viewmodel.detail_movie.DetailMovieViewModel
import com.vunh.recyclerkotlinmvvm.viewmodel.detail_movie.DetailMovieViewModelFactory

class DetailMovieActivity : AppCompatActivity() {
    private lateinit var detailMovieBinding: ActivityDetailMovieBinding
    lateinit var viewModel: DetailMovieViewModel
    lateinit var viewModelFactory: DetailMovieViewModelFactory
    var baseMovie = Movie(
        "",
        "High Rated",
        "https://howtodoandroid.com/images/wonder.jpg",
        "Wonder",
        "Wonder is a 2017 American drama film directed by Stephen Chbosky and written by Jack Thorne , Steve Conrad and Stephen Chbosky based on the 2012 novel of the same name by R.J. Palacio"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailMovieBinding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(detailMovieBinding.root)
        viewModelFactory = DetailMovieViewModelFactory(BaseApp.baseApp!!.recyclerRepositoryImpl)
        viewModel = ViewModelProvider(this, viewModelFactory).get(DetailMovieViewModel::class.java)

        initializeView()
        initializeViewModel()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initializeView(){
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)
        actionBar?.setHomeButtonEnabled(true)
        actionBar?.title = "Detail"

        val bundle: Bundle? = intent.getBundleExtra("bundleDetailMovie")
        bundle?.let {
            bundle.apply {
                val movie = getSerializable("movie") as Movie?
                if(movie != null) {
                    viewModel.getMovie(movie.movieId)
                }
            }
        }
    }

    private fun initializeViewModel(){
        viewModel.showResult.observe(this, Observer {
            if(it != null) {
                Snackbar.make(detailMovieBinding.root, "Success", Snackbar.LENGTH_LONG).show()
                Glide.with(this).load(it.imageUrl).into(detailMovieBinding.detailMovieThumbnail);
                detailMovieBinding.detailCategoryTv.setText(it.category)
                detailMovieBinding.detailNameTv.setText(it.name)
                detailMovieBinding.detailDescTv.setText(it.desc)
            }
        })
        viewModel.showLoading.observe(this, Observer {
            if (it)
                detailMovieBinding.detailLoadingSpinner.visibility = View.VISIBLE
            else
                detailMovieBinding.detailLoadingSpinner.visibility = View.GONE
        })
        viewModel.showError.observe(this, Observer {
            Snackbar.make(detailMovieBinding.root, it, Snackbar.LENGTH_LONG).show()
        })
    }
}