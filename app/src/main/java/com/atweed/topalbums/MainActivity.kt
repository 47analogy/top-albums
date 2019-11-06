package com.atweed.topalbums

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() {

    private val client by lazy {
        AlbumAPI.create()
    }

    var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        displayAlbums()
    }

    // log albums (verifying)
    private fun displayAlbums() {
        disposable = client.getAlbums()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> showRecycler(result.feed.results) },
                { error -> Log.e("ERROR", error.message) }
            )
    }

    private fun showRecycler(albumList: List<Result>) {
        albums_recycler.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        albums_recycler.layoutManager = layoutManager
        albums_recycler.adapter = AlbumAdapter(albumList){
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("releaseDate", it.releaseDate)
            intent.putExtra("rating", it.contentAdvisoryRating)
            intent.putExtra("genre", it.genres.toString())
            intent.putExtra("artistLink", it.artistUrl)
            intent.putExtra("albumLink", it.url)
            startActivity(intent)
        }
    }
}