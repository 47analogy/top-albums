package com.atweed.topalbums

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    val client by lazy {
        AlbumAPI.create()
    }

    var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        displayAlbums()
    }

    // log albums (verifying)
    fun displayAlbums() {
        disposable = client.getAlbums()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(
//                { result -> Log.v("ALBUMS", "" + result.feed.results) },
//                { error -> Log.e("ERROR", error.message) }
//            )
            .subscribe(
                { result -> setupRecycler(result.feed.results) },
                { error -> Log.e("ERROR", error.message) }
            )
    }

    fun setupRecycler(albumList: List<Result>) {

        albums_recycler.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        albums_recycler.layoutManager = layoutManager
        albums_recycler.adapter = AlbumAdapter(albumList){
            Log.v("Album", it.toString())
        }

    }


    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }
}